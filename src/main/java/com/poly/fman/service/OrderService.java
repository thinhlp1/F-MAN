package com.poly.fman.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.poly.fman.dto.request.CheckoutDTO;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.Cart;
import com.poly.fman.entity.CartItem;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.PaymentMethod;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
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
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final VoucherRepository voucherRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final CartItemRepository cartItemRepository;
    private final PlatformTransactionManager transactionManager;

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElse(null);
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

    public Order create(CheckoutDTO checkoutDTO) {
        Order order = new Order();

        if (checkoutDTO.getPaymentRequestDTO() == null) {
            order.setOrderState(orderStateRepository.findById("PENDING_APPROVAL").orElse(null));
        } else {
            order.setOrderState(orderStateRepository.findById("WAIT_PAYMENT").orElse(null));
        }

        long total = 0;
        String isByNow = checkoutDTO.getIsBuyNow();

        List<CartItem> listCartItem = cartItemRepository.findAllById(checkoutDTO.getListItemId());
        System.out.println(checkoutDTO.toString());
        if (isByNow.equals("true")) {
            int productSizeId = checkoutDTO.getProductSizeId();
            ProductSize productSize = productSizeRepository.findById(productSizeId).orElse(null);
            Product product = productSize.getProduct();
            total = product.getPrice().intValue() * checkoutDTO.getQuantity();
        } else {
            for (CartItem item : listCartItem) {
                total += item.getProduct().getPrice().intValue() * item.getQuantity();
            }
        }

        List<OrderItem> listOderItem = new ArrayList<>();

        PaymentMethod paymentMethod = paymentMethodRepository.findById(checkoutDTO.getPaymentMethod()).orElse(null);

        if (!checkoutDTO.getVoucher().equals("")) {
            Voucher voucher = voucherRepository.findByNameAndActiveIsTrue(checkoutDTO.getVoucher()).orElse(null);
            if (voucher != null) {
                long discount = (long) (total * (voucher.getSalePercent() / 100));
                total = total - discount;
                order.setVoucher(voucher);
            }
        }
        User user = userRepository.findById(checkoutDTO.getUserId()).orElse(null);
        Address address = addressRepository.findById(checkoutDTO.getAddressId()).orElse(null);

        order.setAddress(address);
        order.setCreateAt(new Date());
        order.setPaymentMethod(paymentMethod);
        order.setTotal(BigInteger.valueOf(total));

        order.setUser(user);

        // System.out.println(order.toString());
        // order = orderRepository.findById(1).orElse(order);
        // System.out.println(order.getAddress().getReceiverName());
        order = orderRepository.save(order);

        if (!isByNow.equals("true")) {
            for (CartItem item : listCartItem) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(item.getProduct());
                orderItem.setProductSize(item.getProductSize());
                orderItem.setProductPrice(item.getProduct().getPrice());
                orderItem.setQuantity(item.getQuantity());
                listOderItem.add(orderItem);

            }

            List<OrderItem> listSaved = orderItemRepository.saveAll(listOderItem);
            cartItemRepository.deleteAll(listCartItem);
        } else {
            int productSizeId = checkoutDTO.getProductSizeId();
            OrderItem orderItem = new OrderItem();
            ProductSize productSize = productSizeRepository.findById(productSizeId).orElse(null);
            Product product = productSize.getProduct();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setProductSize(productSize);
            orderItem.setProductPrice(product.getPrice());
            orderItem.setQuantity(checkoutDTO.getQuantity());
            orderItemRepository.save(orderItem);
        }
        return order;

    }

    public Order reCreate(CheckoutDTO checkoutDTO) {

       
        Order order = orderRepository.findById(checkoutDTO.getOrderId()).orElse(null);
        if (checkoutDTO.getPaymentRequestDTO() == null) {
            order.setOrderState(orderStateRepository.findById("PENDING_APPROVAL").orElse(null));
        } else {
            order.setOrderState(orderStateRepository.findById("WAIT_PAYMENT").orElse(null));
        }

        List<OrderItem> listOderItem = order.getOrderItems();
         long total = order.getTotal().longValue();
        PaymentMethod paymentMethod = paymentMethodRepository.findById(checkoutDTO.getPaymentMethod()).orElse(null);

        if (!checkoutDTO.getVoucher().equals("")) {
            Voucher voucher = voucherRepository.findByNameAndActiveIsTrue(checkoutDTO.getVoucher()).orElse(null);
            if (voucher != null) {
                long discount = (long) (total * (voucher.getSalePercent() / 100));
                total = total - discount;
                order.setVoucher(voucher);
            }
        }
        User user = userRepository.findById(checkoutDTO.getUserId()).orElse(null);
        Address address = addressRepository.findById(checkoutDTO.getAddressId()).orElse(null);

        order.setAddress(address);
        order.setUpdateAt(new Date());
        order.setPaymentMethod(paymentMethod);
        order.setTotal(BigInteger.valueOf(total));

        order.setUser(user);

        // System.out.println(order.toString());
        // order = orderRepository.findById(1).orElse(order);
        // System.out.println(order.getAddress().getReceiverName());
        order = orderRepository.save(order);
        return order;

    }

    public Page<Order> getOders(String orderStateId, String sortBy, String sortOrder, int page, int size) {
        Sort sort = Sort.by(sortBy);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);

        if (orderStateId != null) {
            OrderState orderState = orderStateRepository.findById(orderStateId).orElse(null);
            if (orderState != null) {
                return orderRepository.findByOrderStateId(orderStateId, pageable);
            }
        }

        return orderRepository.findAll(pageable);
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
                            int quantityAfter = productSize.getAvailableQuantity() - orderItem.getQuantity();
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
