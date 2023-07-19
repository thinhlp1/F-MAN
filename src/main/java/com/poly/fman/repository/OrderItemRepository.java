package com.poly.fman.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.fman.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    public Optional<List<OrderItem>> findByProductIdAndOrderId(String productId, Integer orderId);


    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.order.updateAt >= :startDate AND oi.order.updateAt <= :endDate AND oi.order.orderState.id IN :orderStateId")
    Integer countSoldProductsByDateRangeAndState(Date startDate, Date endDate, List<String> orderStateId);

    @Query("SELECT SUM(oi.productPrice * oi.quantity) FROM OrderItem oi WHERE oi.order.updateAt >= :startDate AND oi.order.updateAt <= :endDate AND oi.order.orderState.id IN :orderStateIds")
    BigInteger calculateRevenueByDateRangeAndStates(Date startDate, Date endDate, List<String> orderStateIds);

        @Query("SELECT oi.product, SUM(oi.quantity) FROM OrderItem oi JOIN oi.order o WHERE oi.order.updateAt >= :startDate AND oi.order.updateAt <= :endDate AND o.orderState.id IN :orderStateIds GROUP BY oi.product ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findTop10BestSellingProductsByRangeAndStates(Date startDate, Date endDate, List<String> orderStateIds);

}
