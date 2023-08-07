package com.poly.fman.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.poly.fman.dto.cart.CartItemResponseDTO;
import com.poly.fman.dto.cart.CartRequestDTO;
import com.poly.fman.dto.cart.CartResponseDTO;
import com.poly.fman.dto.model.CartItemDTO;
import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.OrderItemDTO;
import com.poly.fman.dto.model.PaymentMethodDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.model.TransactionDTO;
import com.poly.fman.dto.order.CheckoutOrderResponseDTO;
import com.poly.fman.dto.order.CheckoutRequestDTO;
import com.poly.fman.dto.order.ReCheckoutReponseDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.Cart;
import com.poly.fman.entity.CartItem;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.entity.Transaction;
import com.poly.fman.entity.User;
import com.poly.fman.entity.Voucher;
import com.poly.fman.repository.AddressRepository;
import com.poly.fman.repository.CartItemRepository;
import com.poly.fman.repository.CartRepository;
import com.poly.fman.repository.OrderItemRepository;
import com.poly.fman.repository.OrderRepository;
import com.poly.fman.repository.OrderStateRepository;
import com.poly.fman.repository.PaymentMethodRepository;
import com.poly.fman.repository.ProductRepository;
import com.poly.fman.repository.ProductSizeRepository;
import com.poly.fman.repository.TransactionRepository;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.repository.VoucherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final OrderStateRepository orderStateRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final VoucherRepository voucherRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final CartItemRepository cartItemRepository;
    private final PlatformTransactionManager transactionManager;
    private final CartService cartService;

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderDTO convertDTO(Order order) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDTO orderDTO = new OrderDTO();
        List<OrderItem> listOderItem = order.getOrderItems();
        List<OrderItemDTO> listOrderItemDTOs = new ArrayList();
        listOrderItemDTOs = listOderItem.stream()
                .map(item -> modelMapper.map(item, OrderItemDTO.class))
                .collect(Collectors.toList());

        Long tempTotal = (long) 0;
        Long discount = (long) 0;

        for (OrderItem orderItem : listOderItem) {
            tempTotal += orderItem.getProduct().getPrice().intValue() * orderItem.getQuantity();
        }

        if (order.getVoucher() != null) {
            discount = (long) (tempTotal * order.getVoucher().getSalePercent() / 100);
        }

        orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setTempTotal(tempTotal);
        orderDTO.setDiscount(discount);
        orderDTO.setListOrderItemDTO(listOrderItemDTOs);

        return orderDTO;
    }

    public OrderDTO getOrderDTO(Integer id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Order order = orderRepository.findById(id).orElse(null);
        OrderDTO orderDTO = new OrderDTO();

        List<OrderItem> listOderItem = order.getOrderItems();
        List<OrderItemDTO> listOrderItemDTOs = new ArrayList();
        listOrderItemDTOs = listOderItem.stream()
                .map(item -> modelMapper.map(item, OrderItemDTO.class))
                .collect(Collectors.toList());

        Long tempTotal = (long) 0;
        Long discount = (long) 0;

        Transaction transaction = transactionRepository.findByOrderId(order.getId());
        if (transaction != null) {
            TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
            orderDTO.setTransactionDTO(transactionDTO);

        }

        for (OrderItem orderItem : listOderItem) {
            tempTotal += orderItem.getProduct().getPrice().intValue() * orderItem.getQuantity();
        }

        if (order.getVoucher() != null) {
            discount = (long) (tempTotal * order.getVoucher().getSalePercent() / 100);
        }

        orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setTempTotal(tempTotal);
        orderDTO.setDiscount(discount);
        orderDTO.setListOrderItemDTO(listOrderItemDTOs);

        return orderDTO;
    }

    public TransactionDTO getTransactionDTO(Integer orderId) {
        Transaction transaction = transactionRepository.findByOrderId(orderId);
        if (transaction == null) {
            return null;
        }
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

    public List<OrderDTO> getAllOders() {
        List<Order> listOrder = orderRepository.findAll();
        List<OrderDTO> listOrderDTO = listOrder.stream()
                .map(item -> convertDTO(item))
                .collect(Collectors.toList());
        Collections.sort(listOrderDTO, (o1, o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));

        return listOrderDTO;
    }

    public List<OrderDTO> getApproveOrder() {
        List<Order> listOrder = orderRepository.findAllByOrderStateId("PENDING_APPROVAL");
        List<OrderDTO> listOrderDTO = listOrder.stream()
                .map(item -> convertDTO(item))
                .collect(Collectors.toList());
        Collections.sort(listOrderDTO, (o1, o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));

        return listOrderDTO;
    }

    public Page<Order> getOrdersByUser(Integer userId, String orderStateId, String sortBy, String sortOrder, int page,
            int size) {
        Sort sort = Sort.by(sortBy);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);

        if (orderStateId != null) {
            OrderState orderState = orderStateRepository.findById(orderStateId).orElse(null);
            if (orderState != null) {
                return orderRepository.findByUserIdAndOrderStateId(userId, orderStateId, pageable);
            }
        }

        return orderRepository.findByUserId(userId, pageable);

    }

    public ReCheckoutReponseDTO getRecheckout(Integer orderId) {

        OrderDTO order = getOrderDTO(orderId);

        ReCheckoutReponseDTO reCheckoutReponseDTO = new ReCheckoutReponseDTO();
        reCheckoutReponseDTO.setOrder(order);

        return reCheckoutReponseDTO;
    }

    public ResponseDTO create(CheckoutRequestDTO checkoutDTO) {

        if (checkoutDTO.getListCheckoutItem().isEmpty()) {
            return new SimpleReponseDTO("500", "Không có danh sách sản phẩm");
        }

        Order order = new Order();

        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            CheckoutOrderResponseDTO checkoutOrderResponse = new CheckoutOrderResponseDTO();
            OrderDTO orderDTO = new OrderDTO();
            List<OrderItem> listOderItem = new ArrayList<>();
            List<OrderItemDTO> listOrderItemDTOs = new ArrayList();

            if (checkoutDTO.getBankCode().equals("")) {
                System.out.println(checkoutDTO.getBankCode());
                order.setOrderState(orderStateRepository.findById("PENDING_APPROVAL").orElse(null));
            } else {
                order.setOrderState(orderStateRepository.findById("WAIT_PAYMENT").orElse(null));
            }

            long total = 0;
            long discount = 0;

            CartRequestDTO cartRequestDTO = new CartRequestDTO(); // user for progess cart to calculate data
            cartRequestDTO.setListCartItem(checkoutDTO.getListCheckoutItem());
            cartRequestDTO.setUserId(cartRequestDTO.getUserId());

            CartResponseDTO cartResponseDTO = cartService.processCartRequest(cartRequestDTO);

            List<CartItemResponseDTO> listCartItem = cartResponseDTO.getListCartItems();
            total = cartResponseDTO.getTotal();

            PaymentMethod paymentMethod = paymentMethodRepository.findById(checkoutDTO.getPaymentMethodId())
                    .orElse(null);

            if (!checkoutDTO.getVoucherCode().equals("")) {
                Voucher voucher = voucherRepository.findByNameAndActiveIsTrue(checkoutDTO.getVoucherCode())
                        .orElse(null);
                if (voucher != null) {
                    discount = (long) (total * (voucher.getSalePercent() / 100));
                    total = total - discount;
                    order.setVoucher(voucher);
                }
            }
            User user = userRepository.findByIdAndActiveIsTrue(checkoutDTO.getUserId()).orElse(null);
            Address address = addressRepository.findByIdAndActiveIsTrue(checkoutDTO.getAddressId()).orElse(null);

            order.setAddress(address);
            order.setCreateAt(new Date());
            order.setPaymentMethod(paymentMethod);
            order.setTotal(BigInteger.valueOf(total));

            order.setUser(user);

            // System.out.println(order.toString());
            // order = orderRepository.findById(1).orElse(order);
            // System.out.println(order.getAddress().getReceiverName());
            order = orderRepository.save(order);

            for (CartItemResponseDTO item : listCartItem) {
                OrderItem orderItem = new OrderItem();
                Product product = productRepository.findById(item.getProduct().getId()).orElse(null);
                ProductSize productSize = productSizeRepository.findById(item.getProductSize().getId()).orElse(null);
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setProductSize(productSize);
                orderItem.setProductPrice(item.getProduct().getPrice());
                orderItem.setQuantity(item.getQuantity());
                listOderItem.add(orderItem);

            }

            List<OrderItem> listSaved = orderItemRepository.saveAll(listOderItem);
            listOrderItemDTOs = listSaved.stream()
                    .map(item -> modelMapper.map(item, OrderItemDTO.class))
                    .collect(Collectors.toList());

            orderDTO = modelMapper.map(order, OrderDTO.class);
            orderDTO.setListOrderItemDTO(listOrderItemDTOs);
            orderDTO.setTempTotal(cartResponseDTO.getTotal());
            orderDTO.setTotal(total);
            orderDTO.setDiscount(discount);

            checkoutOrderResponse.setOrder(orderDTO);
            transactionManager.commit(transactionStatus);

            return checkoutOrderResponse;
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);

            return new SimpleReponseDTO("500", "Có lỗi xảy ra ");
        }

    }

    public void updateRecheckout(Integer orderId) {

        Order order = orderRepository.findById(orderId).orElse(null);
        order.setUpdateAt(new Date());

    }

    public boolean cancelOrder(Integer orderId, String note) {
        Order order = orderRepository.findById(orderId).orElse(null);
        OrderState orderState = orderStateRepository.findById("ORDER_CANCEL").orElse(null);
        order.setOrderState(orderState);
        order.setNote(note);
        order.setUpdateAt(new Date());
        if (orderRepository.save(order) != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public boolean approveOrder(Integer orderId) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order != null) {
                OrderState orderState = orderStateRepository.findById("ORDER_IS_SHIPPING").orElse(null);
                order.setOrderState(orderState);
                order.setUpdateAt(new Date());
                if (orderRepository.save(order) != null) {
                    // update quantity in stock
                    List<OrderItem> lOrderItems = order.getOrderItems();
                    for (OrderItem orderItem : lOrderItems) {
                        ProductSize productSize = productSizeRepository.findById(orderItem.getProductSize().getId())
                                .orElse(null);
                        if (productSize != null) {
                            int quantityAfter = productSize.getQuantity() - orderItem.getQuantity();
                            quantityAfter = quantityAfter < 0 ? 0 : quantityAfter;
                            productSize.setQuantity(quantityAfter);
                            productSize.setUpdateAt(new Date());
                            productSizeRepository.save(productSize);

                            List<CartItem> lCartItems = cartItemRepository
                                    .findByProductSizeId(orderItem.getProductSize().getId()).orElse(new ArrayList<>());

                            for (CartItem cartItem : lCartItems) {
                                if (quantityAfter > 0) {
                                    cartItem.setQuantity(quantityAfter);
                                    cartItemRepository.save(cartItem);
                                } else {
                                    Cart cart = cartItem.getCart();
                                    cart.setTotal(cart.getTotal() - 1);
                                    cartItemRepository.delete(cartItem);
                                    cartRepository.save(cart);
                                }
                            }

                        } else {
                            transactionManager.rollback(transactionStatus);
                            return false;
                        }
                    }
                    transactionManager.commit(transactionStatus);
                    return true;
                } else {
                    transactionManager.rollback(transactionStatus);
                    return false;

                }

            } else {
                transactionManager.rollback(transactionStatus);
                return false;
            }

        } catch (

        Exception e) {
            transactionManager.rollback(transactionStatus);
            return false;
        }
    }

    public int getOrderApproveQuantity() {

        int quantity = orderRepository.findByOrderStateId("PENDING_APPROVAL").orElse(new ArrayList<>()).size();
        return quantity;
    }

}
