package com.poly.fman.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.poly.fman.entity.Cart;
import com.poly.fman.entity.CartItem;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.dto.cart.CartItemRequestDTO;
import com.poly.fman.dto.cart.CartItemResponseDTO;
import com.poly.fman.dto.cart.CartResponseDTO;
import com.poly.fman.dto.cart.CheckoutReponseDTO;
import com.poly.fman.dto.cart.CartRequestDTO;
import com.poly.fman.dto.model.AddressDTO;
import com.poly.fman.dto.model.CartDTO;
import com.poly.fman.dto.model.CartItemDTO;
import com.poly.fman.dto.model.CartItemDTO2;
import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.PaymentMethodDTO;
import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductSizeDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.order.CheckoutRequestDTO;
import com.poly.fman.dto.order.ReCheckoutReponseDTO;
import com.poly.fman.dto.payment.PaymentReponse;
import com.poly.fman.dto.reponse.ApplyVoucherDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.entity.User;
import com.poly.fman.entity.Voucher;
import com.poly.fman.repository.AddressRepository;
import com.poly.fman.repository.CartItemRepository;
import com.poly.fman.repository.CartRepository;
import com.poly.fman.repository.PaymentMethodRepository;
import com.poly.fman.repository.ProductRepository;
import com.poly.fman.repository.ProductSizeRepository;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.repository.VoucherRepository;
import com.poly.fman.service.common.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final AddressService addressService;
    private final PaymentMethodRepository paymentMethodRepository;
    private final VoucherRepository voucherRepository;
    private final ModelMapper modelMapper;

    public CartResponseDTO processCartRequest(CartRequestDTO cartRequest) {
        CartResponseDTO cartResponse = new CartResponseDTO();

        List<CartItemRequestDTO> cartItems = cartRequest.getListCartItem();
        List<CartItemResponseDTO> cartItemResponses = new ArrayList<>();
        long totalPrice = 0;
        for (CartItemRequestDTO cartItemRequest : cartItems) {
            Product product = productRepository.findById(cartItemRequest.getProductId()).orElse(null);
            ProductSize productSize = productSizeRepository.findById(cartItemRequest.getProductSizeId()).orElse(null);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            ProductSizeDTO productSizeDTO = modelMapper.map(productSize, ProductSizeDTO.class);

            CartItemResponseDTO cartItemResponse = new CartItemResponseDTO();
            cartItemResponse.setProduct(productDTO);
            cartItemResponse.setProductSize(productSizeDTO);

            int quantity = cartItemRequest.getQuantity();
            if (productSize.getAvailableQuantity() < quantity) {
                quantity = productSize.getAvailableQuantity();
            }

            long subTotal = product.getPrice().longValue() * quantity;
            totalPrice += subTotal;

            cartItemResponse.setSubTotal(subTotal);
            cartItemResponse.setQuantity(quantity);

            cartItemResponses.add(cartItemResponse);
        }
        ;
        cartResponse.setTotal(totalPrice);
        cartResponse.setListCartItems(cartItemResponses);

        return cartResponse;
    }

    public CartDTO getCartByUserId(Integer userId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Cart cart;
        try {
            cart = cartRepository.findByUserId(userId).orElseThrow();
        } catch (Exception e) {
            cart = create(userId);
        }
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        List<CartItemDTO> listCartItemDTOs = getListCartItem(cart.getId());
        cartDTO.setCartItemsDTO(listCartItemDTOs);
        // cartDTO.setCartItemsDTO(new ArrayList<>());

        long totalPrice = 0;
        for (CartItemDTO item : listCartItemDTOs) {
            // totalPrice += item.getProduct().getPrice().intValue() * (int)
            // item.getQuantity();
        }
        cartDTO.setTotalPrice(CommonUtils.convertToCurrencyString(totalPrice, " VNĐ"));

        return cartDTO;
    }

    public CheckoutReponseDTO checkout(CartRequestDTO checkoutRequestDTO) {
        CartResponseDTO cartResponseDTO = processCartRequest(checkoutRequestDTO);
        CheckoutReponseDTO checkoutReponseDTO = new CheckoutReponseDTO();

        checkoutReponseDTO.setListCartItems(cartResponseDTO.getListCartItems());

        List<AddressDTO> lisAddressDTOs = addressService.getByUserId(checkoutRequestDTO.getUserId());
        List<PaymentMethod> listPaymentMethods = paymentMethodRepository.findAllByActiveIsTrue();
        List<PaymentMethodDTO> listPaymentMethodDTOs = listPaymentMethods.stream()
                .map(payment -> modelMapper.map(payment, PaymentMethodDTO.class))
                .collect(Collectors.toList());
        checkoutReponseDTO.setListAddress(lisAddressDTOs);
        checkoutReponseDTO.setListPaymentMethods(listPaymentMethodDTOs);
        checkoutReponseDTO.setTotal(cartResponseDTO.getTotal());
        checkoutReponseDTO.setDiscount((long) 0);
        checkoutReponseDTO.setTempTotal(cartResponseDTO.getTotal());

        return checkoutReponseDTO;
    }

  
    public Cart getCartByUserId2(Integer userId) {
        try {
            return cartRepository.findByUserId(userId).orElseThrow();
        } catch (Exception e) {
            return create(userId);
        }
    }

    public CartItem changeQuantity(Integer cartItemId, int quantity) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
            cartItem.setQuantity(quantity);
            cartItem.setUpdateAt(new Date());
            return cartItemRepository.save(cartItem);
        } catch (Exception e) {
            return null;
        }
    }

    public BigInteger getTotalPrice(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        List<CartItem> lCartItems = cartItemRepository.findByCartIdAndProductSizeActiveIsTrue(cartId)
                .orElse(new ArrayList<>());
        long total = 0;
        for (CartItem cartItem : lCartItems) {
            total += cartItem.getProduct().getPrice().intValue() * cartItem.getQuantity();
        }
        return BigInteger.valueOf(total);
    }

    public BigInteger getSubTotal(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        long total = cartItem.getProduct().getPrice().intValue() * cartItem.getQuantity();
        return BigInteger.valueOf(total);
    }

    public Cart create(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = new Cart();
        cart.setCreateAt(new Date());
        cart.setUser(user);
        cart.setTotal(0);
        return cartRepository.save(cart);
    }

    public boolean removeProduct(Integer cartItemId) {
        try {
            cartItemRepository.deleteById(cartItemId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addToCart(Integer cartId, String productId, Integer productSizeId, Integer quantity) {
        try {

            Optional<CartItem> item = cartItemRepository.findByProductIdAndAndProductSizeIdAndCartId(productId,
                    productSizeId, cartId);
            if (item.isPresent()) {
                CartItem cartItem = item.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setUpdateAt(new Date());
                cartItemRepository.save(cartItem);
            } else {
                CartItem cartItem = new CartItem();
                Product product = productRepository.findById(productId).orElseThrow();
                ProductSize productSize = productSizeRepository.findById(productSizeId).orElseThrow();
                Cart cart = cartRepository.findById(cartId).orElseThrow();
                cartItem.setQuantity(quantity);
                cartItem.setCreateAt(new Date());
                cartItem.setProduct(product);
                cartItem.setProductSize(productSize);
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<CartItemDTO2> getListCartItem(List<Integer> selectedItem) {
        List<CartItem> lCartItems = cartItemRepository.findAllById(selectedItem);
        List<CartItemDTO2> listCartItemDTOs = lCartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDTO2.class))
                .collect(Collectors.toList());
        return listCartItemDTOs;
    }

    public List<CartItemDTO2> getListCartItem2(Integer cartId) {
        List<CartItem> lCartItems = cartItemRepository.findByCartIdAndProductSizeActiveIsTrue(cartId)
                .orElse(new ArrayList<>());
        List<CartItemDTO2> listCartItemDTOs = lCartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDTO2.class))
                .collect(Collectors.toList());
        return listCartItemDTOs;
    }

    public List<CartItemDTO> getListCartItem(Integer cartId) {
        List<CartItem> lCartItems = cartItemRepository.findByCartIdAndProductSizeActiveIsTrue(cartId)
                .orElse(new ArrayList<>());
        List<CartItemDTO> listCartItemDTOs = lCartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDTO.class))
                .collect(Collectors.toList());
        return listCartItemDTOs;
    }

    public ResponseDTO applyVoucher(String voucherName, Long total) {
        try {
            Voucher voucher = voucherRepository.findByNameAndActiveIsTrue(voucherName).orElseThrow();
            System.out.println(voucher.getStartAt());
            System.out.println(voucher.getEndAt());
            System.out.println(voucher.getMinPrice());

            Date currentDate = new Date();
            Date startAt = voucher.getStartAt();
            Date endAt = voucher.getEndAt();

            if (currentDate.getTime() >= startAt.getTime() && currentDate.getTime() <= endAt.getTime()) {
                // Hôm nay nằm giữa startAt và endAt
                System.out.println("Hôm nay nằm trong khoảng thời gian của voucher.");
            } else {
                // Hôm nay không nằm giữa startAt và endAt
                System.out.println("Hôm nay không nằm trong khoảng thời gian của voucher.");
            }

            if (currentDate.after(startAt) && currentDate.before(endAt)) {
                // Hôm nay nằm giữa startAt và endAt
                System.out.println("Voucher thời gian hợp lệ vl");
                if (voucher.getMinPrice().intValue() <= total) {
                    long subTotal = total;
                    long discount = (long) (total * (voucher.getSalePercent() / 100));
                    long finalTotal = total - discount;
                    return new ApplyVoucherDTO(subTotal, discount, finalTotal);
                } else {
                    return new SimpleReponseDTO("404", "Voucher chưa đủ điều kiện áp dụng");
                }

            } else if (currentDate.after(endAt)) {
                // Hôm nay không nằm giữa startAt và endAt
                System.out.println("Voucher đã hết hạn");
                return new SimpleReponseDTO("404", "Voucher đã hết hạn");

            } else if (currentDate.before(startAt)) {
                // Hôm nay không nằm giữa startAt và endAt
                System.out.println("Voucher chưa bắt đầu áp dụng");
                return new SimpleReponseDTO("404", "Voucher chưa bắt đầu áp dụng");
            }

            // kiểm tra xem hôm nay có nằm giữa start at và end at không
        } catch (Exception e) {
            return new SimpleReponseDTO("404", "Voucher không hợp lệ");
        }
        return new SimpleReponseDTO("404", "Voucher không hợp lệ");
    }

    public boolean checkInStock(Integer productSizeId, int quantity) {
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElse(null);
        if (productSize != null) {
            int quantityInStock = productSize.getAvailableQuantity();
            if (quantity > quantityInStock) {
                return true;
            }
        }
        return false;
    }

    public int getCartQuantity(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        int quantity = cartItemRepository.countDistinctProductSizesByCartId(cart.getId());
        return quantity;
    }

}
