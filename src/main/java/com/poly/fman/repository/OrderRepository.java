package com.poly.fman.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderState;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Optional<List<Order>> findByUserId(Integer userId);

    public Optional<List<Order>> findByTotalBetween(Integer min, Integer max);

    public Optional<List<Order>> findByTotalLessThan(Integer total);

    public Optional<List<Order>> findByTotalGreaterThan(Integer total);

    public Optional<List<Order>> findByOrderStateId(String stateId);

    public  Page<Order> findByUserId(Integer userId, Pageable pageable);

    public Page<Order> findByUserIdAndOrderStateId(Integer userId, String orderStateId, Pageable pageable);

     public  Page<Order> findAll(Pageable pageable);

    public Page<Order> findByOrderStateId( String orderStateId, Pageable pageable);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.updateAt >= :startDate AND o.updateAt <= :endDate AND o.orderState.id IN :orderStateIds")
    Long countOrdersByDateRangeAndStates(Date startDate, Date endDate, List<String> orderStateIds);

    @Query("SELECT SUM( o.total ) FROM Order o WHERE o.updateAt >= :startDate AND o.updateAt <= :endDate AND o.orderState.id IN :orderStateIds")
    BigInteger calculateRevenueByDateRangeAndStates(Date startDate, Date endDate, List<String> orderStateIds);



    
}
