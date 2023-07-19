package com.poly.fman.controller.user;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.fman.dto.AddressDTO;
import com.poly.fman.dto.CartDTO;
import com.poly.fman.dto.CartItemDTO;
import com.poly.fman.dto.CartItemDTO2;
import com.poly.fman.dto.ResponseDTO;
import com.poly.fman.dto.reponse.ChangeQuantityDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.dto.request.CheckoutDTO;
import com.poly.fman.entity.Cart;
import com.poly.fman.entity.CartItem;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.repository.ProductSizeRepository;
import com.poly.fman.service.AddressService;
import com.poly.fman.service.CartService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.common.CommonUtils;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/user/carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final ProductSizeRepository productSizeRepository;
    private final ModelMapper modelMapper;
    private final AddressService addressService;
    private final OrderService orderService;
    private final HttpSession httpSession;

    @GetMapping("/{userId}")
    public String cart(@PathVariable("userId") Integer userId, Model model) {
        return "user/view/cart/cart";
    }

    @GetMapping("/test/{userId}")
    @ResponseBody
    public  ResponseEntity<ResponseDTO> cart2(@PathVariable("userId") Integer userId, Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Cart cart = cartService.getCartByUserId2(userId);
        List<CartItemDTO> listCartItemDTOs = cartService.getListCartItem(cart.getId());
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        long totalPrice = 0;
        for (CartItemDTO item : listCartItemDTOs) {
            // totalPrice += item.getProduct().getPrice().intValue() * (int) item.getQuantity();
        }
        cartDTO.setTotalPrice(CommonUtils.convertToCurrencyString(totalPrice, " VNĐ"));
        // cartDTO.setCartItemsDTO(listCartItemDTOs);
        // model.addAttribute("cart", cartDTO);
        // model.addAttribute("listCartItem", listCartItemDTOs);
        return ResponseEntity.ok(cartDTO);
//    return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Sai tên đăng nhập hoặc mật khẩu"));
    }

    @GetMapping("/get-cart/{userId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getCart(@PathVariable("userId") Integer userId) {
        try {
            CartDTO cartDTO = cartService.getCartByUserId(userId);
            System.out.println("GET CART DTO");
            return ResponseEntity.ok(cartDTO);
        
        } catch (Exception e) {
                   return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Sai tên đăng nhập hoặc mật khẩu"));

        }
    }


     @GetMapping("/get-cart-items/{cartId}")
    @ResponseBody
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable("cartId") Integer cartId) {
        try {

            List<CartItemDTO> listCartItemDTOs = cartService.getListCartItem(cartId);
            System.out.println("GET CART DTO");
            return ResponseEntity.ok(listCartItemDTOs);
        
        } catch (Exception e) {
              return ResponseEntity.ok(new ArrayList<>());
                //    return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Sai tên đăng nhập hoặc mật khẩu"));

        }
    }

    @GetMapping("/changeQuantity/{cartId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> increase(@PathVariable("cartId") Integer cartId,
            @RequestParam("cartItemId") Integer id,
            @RequestParam("quantity") int quantity) {

        // check product have enougth in inventory
        boolean rs = cartService.checkInStock(id, quantity);

        if (rs) {
            SimpleReponseDTO simpleReponseDTO = new SimpleReponseDTO("500", "Số lượng trong kho đã tối đa");
            return ResponseEntity.status(500).body(simpleReponseDTO);
        }

        CartItem cartItem = cartService.changeQuantity(id, quantity);
        BigInteger subTotal = cartService.getSubTotal(id);
        BigInteger total = cartService.getTotalPrice(cartId);

        ChangeQuantityDTO changeQuantityDTO = new ChangeQuantityDTO("200", "Success",
                CommonUtils.convertToCurrencyString(total, " VNĐ"),
                CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        return ResponseEntity.ok(changeQuantityDTO);
    }

    @GetMapping("/product-remove/{cartId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> removeProduct(@PathVariable("cartId") Integer cartId,
            @RequestParam("cartItemId") Integer id) {
        boolean rs = cartService.removeProduct(id);

        if (rs == true) {

            BigInteger total = cartService.getTotalPrice(cartId);
            ChangeQuantityDTO changeQuantityDTO = new ChangeQuantityDTO("200", "Success",
                    CommonUtils.convertToCurrencyString(total, " VNĐ"),
                    CommonUtils.convertToCurrencyString(0, " VNĐ"));
            return ResponseEntity.ok(changeQuantityDTO);
        } else {
            SimpleReponseDTO simpleReponseDTO = new SimpleReponseDTO("500", "Failed");
            return ResponseEntity.ok(simpleReponseDTO);
        }
    }

    @GetMapping("/product-add/{cartId}")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> addToCart(@PathVariable("cartId") Integer id,
            @RequestParam(defaultValue = "1") String quantity,
            @RequestParam("productId") String productId,
            @RequestParam("productSizeId") Integer productSizeId) {
        boolean rs = cartService.addToCart(id, productId, productSizeId, Integer.parseInt(quantity));
        if (rs == true) {
            SimpleReponseDTO simpleReponseDTO = new SimpleReponseDTO("200", "Success");
            return ResponseEntity.ok(simpleReponseDTO);
        } else {
            SimpleReponseDTO simpleReponseDTO = new SimpleReponseDTO("500", "Failed");
            return ResponseEntity.ok(simpleReponseDTO);
        }
    }

    // @PostMapping("/checkout/{cartId}")
    // public String checkoutForm(@PathVariable("cartId") Integer cartId,
    // @RequestBody List<String> listCartItem) {
    // System.out.println("HEIIIIIII");
    // System.out.println(listCartItem.size());
    // return "user/view/cart/cart_checkout";
    // }

    @GetMapping("/checkout-bynow")
    public String buyNowForm(@RequestParam("productSizeId") Integer productSizeId,
            @RequestParam("quantity") Integer quantity,
            Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // check user address
        List<AddressDTO> listAddressDTOs = addressService.getByUserId((int) httpSession.getAttribute("userId"));
        System.out.println(listAddressDTOs.size());
        if (listAddressDTOs.isEmpty()) {
            model.addAttribute("hasAddress", false);
            return "user/view/cart/cart_checkout";
            // return "redirect:/user/address/all?user-id=" +
            // httpSession.getAttribute("userId");
        }

        List<CartItemDTO2> lCartItemDTOs = new ArrayList<>();
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElse(null);
        Product product = productSize.getProduct();
        CartItemDTO2 cartItemDTO = new CartItemDTO2();
        cartItemDTO.setId(productSizeId);
        // cartItemDTO.setProduct(product);
        // cartItemDTO.setProductSize(productSize);
        cartItemDTO.setQuantity(quantity);
        lCartItemDTOs.add(cartItemDTO);

        int subTotal = 0;

        subTotal = product.getPrice().intValue() * quantity;

        model.addAttribute("listItem", lCartItemDTOs);
        model.addAttribute("listAddress", listAddressDTOs);
        model.addAttribute("addressDefault", listAddressDTOs.get(0));
        model.addAttribute("hasAddress", true);
        model.addAttribute("subTotal", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("totalStr", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("total", subTotal);
        model.addAttribute("isBuyNow", true);
        model.addAttribute("quantity", quantity);
        model.addAttribute("productSizeId", productSizeId);
        return "user/view/cart/cart_checkout";
    }

    @GetMapping("/checkout/{cartId}")
    public String checkoutForm(@PathVariable("cartId") Integer cartId, @RequestParam("itemList") String itemList,
            Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // check user address
        List<AddressDTO> listAddressDTOs = addressService.getByUserId((int) httpSession.getAttribute("userId"));
        System.out.println(listAddressDTOs.size());
        if (listAddressDTOs.isEmpty()) {
            model.addAttribute("hasAddress", false);
            return "user/view/cart/cart_checkout";
            // return "redirect:/user/address/all?user-id=" +
            // httpSession.getAttribute("userId");
        }

        List<Integer> selectedItem = Arrays.stream(itemList.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<CartItemDTO2> lCartItemDTOs = cartService.getListCartItem(selectedItem);
        CartDTO cartDTO = cartService.getCartByUserId((int) httpSession.getAttribute("userId"));

        int subTotal = 0;
        for (CartItemDTO2 cartItemDTO : lCartItemDTOs) {
            subTotal += cartItemDTO.getProduct().getPrice().intValue() * cartItemDTO.getQuantity();
        }

        model.addAttribute("listItem", lCartItemDTOs);
        model.addAttribute("listAddress", listAddressDTOs);
        model.addAttribute("addressDefault", listAddressDTOs.get(0));
        model.addAttribute("cart", cartDTO);
        model.addAttribute("hasAddress", true);
        model.addAttribute("subTotal", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("totalStr", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("total", subTotal);
        model.addAttribute("isBuyNow", false);
        return "user/view/cart/cart_checkout";
    }

    @GetMapping("/recheckout/{orderId}")
    public String recheckoutForm(@PathVariable("orderId") Integer orderId,
            Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Order order = orderService.getOrder(orderId);
        // check user address
        List<AddressDTO> listAddressDTOs = addressService.getByUserId((int) httpSession.getAttribute("userId"));
        System.out.println(listAddressDTOs.size());
        if (listAddressDTOs.isEmpty()) {
            model.addAttribute("hasAddress", false);
            return "user/view/cart/cart_checkout";
            // return "redirect:/user/address/all?user-id=" +
            // httpSession.getAttribute("userId");
        }
        List<CartItemDTO2> lCartItemDTOs = new ArrayList<>();
        List<OrderItem> lOrderItems = order.getOrderItems();
        for (OrderItem orderItem : lOrderItems) {
            CartItemDTO2 cartItemDTO = new CartItemDTO2();

            // cartItemDTO.setProduct(orderItem.getProduct());
            // cartItemDTO.setProductSize(orderItem.getProductSize());
            cartItemDTO.setQuantity(orderItem.getQuantity());
            lCartItemDTOs.add(cartItemDTO);
        }

        int subTotal = 0;
        for (CartItemDTO2 cartItemDTO : lCartItemDTOs) {
            subTotal += cartItemDTO.getProduct().getPrice().intValue() * cartItemDTO.getQuantity();
        }

        model.addAttribute("listItem", lCartItemDTOs);
        model.addAttribute("listAddress", listAddressDTOs);
        model.addAttribute("addressDefault", listAddressDTOs.get(0));
        model.addAttribute("hasAddress", true);
        model.addAttribute("subTotal", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("totalStr", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("total", subTotal);
        model.addAttribute("isBuyNow", false);
        model.addAttribute("recheckout", true);
        model.addAttribute("orderId", orderId);
        return "user/view/cart/cart_checkout";
    }

    @GetMapping("/apply-voucher")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> applyVoucher(
            @RequestParam("voucher") String voucherName, @RequestParam("total") Long total) {

        SimpleReponseDTO simpleReponseDTO = cartService.applyVoucher(voucherName, total);
        if (!simpleReponseDTO.getStatusCode().equals("200")) {
            return ResponseEntity.status(500).body(simpleReponseDTO);
        } else {
            return ResponseEntity.ok(simpleReponseDTO);
        }
    }

}
