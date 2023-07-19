package com.poly.fman.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.fman.dto.analysis.DashBoardOverviewDTO;
import com.poly.fman.dto.analysis.DataDTO;
import com.poly.fman.dto.analysis.DataSellProductDTO;
import com.poly.fman.entity.OrderState;
import com.poly.fman.entity.Product;
import com.poly.fman.repository.OrderItemRepository;
import com.poly.fman.repository.OrderRepository;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalysisService {
        private final UserRepository userRepository;
        private final UserService userService;
        private final ProductService productService;
        private final OrderService orderService;
        private final CartService cartService;
        private final OrderItemRepository orderItemRepository;
        private final OrderRepository orderRepository;

        public DashBoardOverviewDTO analysisDashBoardOverViewData() {
                // Calendar calendar = Calendar.getInstance();
                // calendar.set(Calendar.HOUR_OF_DAY, 0);
                // calendar.set(Calendar.MINUTE, 0);

                Date today = new Date();
                Date startDate = startDate(today);
                Date startDateOfWeek = getStartOfWeek(today);
                Date startDateOfMonth = getStartOfMonth(today);
                List<String> state = new ArrayList<>();
                state.add("ORDER_IS_SHIPPING");
                state.add("ORDER_RECEIVED");
               
                // Số lượng sản phẩm bán được trong hôm nay
                Integer soldProductsToday = orderItemRepository.countSoldProductsByDateRangeAndState(startDate, today,
                                state);

                // Số lượng sản phẩm bán được trong tuần này
                Integer soldProductsThisWeek = orderItemRepository.countSoldProductsByDateRangeAndState(startDateOfWeek,
                                today,
                                state);

                // Số lượng sản phẩm bán được trong tháng này
                Integer soldProductsThisMonth = orderItemRepository.countSoldProductsByDateRangeAndState(
                                startDateOfMonth,
                                today, state);

                // Doanh thu trong hôm nay với các trạng thái A và B
                BigInteger revenueToday = orderRepository.calculateRevenueByDateRangeAndStates(startDate, today,
                                state);

                // Doanh thu trong tuần này với các trạng thái A và B
                BigInteger revenueThisWeek = orderRepository.calculateRevenueByDateRangeAndStates(startDateOfWeek,
                                today,
                                state);

                // Doanh thu trong tháng này với các trạng thái A và B
                BigInteger revenueThisMonth = orderRepository.calculateRevenueByDateRangeAndStates(startDateOfMonth,
                                today,
                                state);

                // Số đơn hàng trong hôm nay với các trạng thái A và B
                Long ordersToday = orderRepository.countOrdersByDateRangeAndStates(startDate, today, state);

                // Số đơn hàng trong tuần này với các trạng thái A và B
                Long ordersThisWeek = orderRepository.countOrdersByDateRangeAndStates(startDateOfWeek, today, state);

                // Số đơn hàng trong tháng này với các trạng thái A và B
                Long ordersThisMonth = orderRepository.countOrdersByDateRangeAndStates(startDateOfMonth, today, state);

                Pageable pageable = PageRequest.of(0, 3, Sort.by("id").descending());
                Page<Product> page1 = (Page<Product>) productService.getTopProductSelling(pageable);
                List<Product> listProduct = page1.getContent();

                if (soldProductsToday == null) {
                        soldProductsToday = Integer.valueOf(0);
                }
                if (soldProductsThisWeek == null) {
                        soldProductsThisWeek = Integer.valueOf(0);
                }
                if (soldProductsThisMonth == null) {
                        soldProductsThisMonth = Integer.valueOf(0);
                }

                if (revenueToday == null) {
                        revenueToday = BigInteger.valueOf(0);
                }
                if (revenueThisWeek == null) {
                        revenueThisWeek = BigInteger.valueOf(0);
                }
                if (revenueThisMonth == null) {
                        revenueThisMonth = BigInteger.valueOf(0);
                }

                if (ordersToday == null) {
                        ordersToday = Long.valueOf(0);
                }
                if (ordersThisWeek == null) {
                        ordersThisWeek = Long.valueOf(0);
                }
                if (ordersThisMonth == null) {
                        ordersThisMonth = Long.valueOf(0);
                }

                DashBoardOverviewDTO dashBoardOverviewDTO = new DashBoardOverviewDTO();
                dashBoardOverviewDTO.setListOrders(new DataDTO(ordersToday, ordersThisWeek, ordersThisMonth));
                dashBoardOverviewDTO
                                .setListRevenue((new DataDTO(CommonUtils.convertToCurrencyString(revenueToday, " VNĐ"),
                                                CommonUtils.convertToCurrencyString(revenueThisWeek, " VNĐ"),
                                                CommonUtils.convertToCurrencyString(revenueThisMonth, " VNĐ"))));
                dashBoardOverviewDTO
                                .setListSoldProducts(new DataDTO(soldProductsToday, soldProductsThisWeek,
                                                soldProductsThisMonth));
                dashBoardOverviewDTO.setListTopProduct(listProduct);

                return dashBoardOverviewDTO;

        }

        public List<DataDTO> calculateRevenueByMonths(Integer year) {

                Calendar calendar = Calendar.getInstance();
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);
                if (year != null) {
                        currentYear = year;
                }

                List<String> state = new ArrayList<>();
                state.add("ORDER_IS_SHIPPING");
                state.add("ORDER_RECEIVED");
                DataDTO dataMonths = new DataDTO();
                DataDTO dataMonthsStr = new DataDTO();
                for (int month = 0; month < 12; month++) {
                        calendar.set(currentYear, month, 1);
                        Date startDate = calendar.getTime();
                        calendar.set(currentYear, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        Date endDate = calendar.getTime();

                        BigInteger revenue = orderRepository.calculateRevenueByDateRangeAndStates(startDate, endDate,
                                        state);
                        revenue = revenue == null ? BigInteger.valueOf(0) : revenue;

                        dataMonths.setDataMonth(month + 1, revenue);
                        dataMonthsStr.setDataMonth(month + 1, CommonUtils.convertToCurrencyString(revenue, " VNĐ"));

                }
                List<DataDTO> listData = new ArrayList<>();
                listData.add(dataMonths);
                listData.add(dataMonthsStr);
                return listData;
        }

        public List<DataDTO> countOrdersByMonths(Integer year) {
                Calendar calendar = Calendar.getInstance();
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                if (year != null) {
                        currentYear = year;
                }

                List<String> state = new ArrayList<>();
                state.add("ORDER_IS_SHIPPING");
                state.add("ORDER_RECEIVED");
                DataDTO dataMonths = new DataDTO();
                for (int month = 0; month < 12; month++) {
                        calendar.set(currentYear, month, 1);
                        Date startDate = calendar.getTime();
                        calendar.set(currentYear, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        Date endDate = calendar.getTime();

                        Long orders = orderRepository.countOrdersByDateRangeAndStates(startDate, endDate,
                                        state);

                        orders = orders == null ? Long.valueOf(0) : orders;

                     
                        dataMonths.setDataMonth(month + 1, orders);


                }
                List<DataDTO> listData = new ArrayList<>();
                listData.add(dataMonths);

                return listData;
        }

        public List<DataSellProductDTO> findTop10BestSellingProducts(Integer year) {
                Date today = new Date();
                Date startDate = startDate(today);
                Date startOfYear = getStartOfYear(today);

                if (year != null) {
                        startOfYear = getStartOfYear(year);
                        today = getEndOfYear(year);
                }

                List<String> state = new ArrayList<>();
                state.add("ORDER_IS_SHIPPING");
                state.add("ORDER_RECEIVED");
                System.out.println(DateUtils.toString(startDate, "dd/MM/yyyy HH:mm"));
                System.out.println(DateUtils.toString(startOfYear, "dd/MM/yyyy HH:mm"));
                List<Object[]> topProducts = orderItemRepository
                                .findTop10BestSellingProductsByRangeAndStates(startOfYear, today, state);
                List<DataSellProductDTO> listDataSellProductDTOs = new ArrayList<>();

                System.out.println("Top 10 sản phẩm bán chạy:");
                for (int i = 0; i < topProducts.size(); i++) {
                        Object[] result = topProducts.get(i);

                        Product product = (Product) result[0];
                        Long quantity = (Long) result[1];
                        DataSellProductDTO dataSellProductDTO = new DataSellProductDTO(product, quantity);
                        listDataSellProductDTOs.add(dataSellProductDTO);
                }
                return listDataSellProductDTOs;
        }

        private Date truncateDate(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return calendar.getTime();
        }

        private Date getStartOfWeek(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(truncateDate(date));
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                return calendar.getTime();
        }

        private Date getStartOfMonth(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(truncateDate(date));
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                return calendar.getTime();
        }

        private Date getStartOfYear(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(truncateDate(date));
                calendar.set(Calendar.MONTH, Calendar.JANUARY);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                return calendar.getTime();
        }

        public Date startDate(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(truncateDate(date));
                return calendar.getTime();
        }

        private static int getCurrentYear() {
                Calendar calendar = Calendar.getInstance();
                return calendar.get(Calendar.YEAR);
        }

        public List<Integer> getYearRange() {
                int startYear = 2018;
                int endYear = getCurrentYear();
                List<Integer> yearRange = new ArrayList<>();
                for (int year = startYear; year <= endYear; year++) {
                        yearRange.add(year);
                       
                }
                Collections.reverse(yearRange);
                return yearRange;
        }

        private static Date getStartOfYear(int year) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return calendar.getTime();
        }

        private static Date getEndOfYear(int year) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                return calendar.getTime();
        }

}
