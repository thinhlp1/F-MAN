<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html class="no-js" lang="zxx">

        <head>
            <!-- Mobile Specific Meta -->
            <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
            <!-- Author Meta -->
            <meta content="CodePixar" name="author">
            <!-- Meta Description -->
            <meta content="" name="description">
            <!-- Meta Keyword -->
            <meta content="" name="keywords">
            <!-- meta character set -->
            <meta charset="UTF-8">
            <!-- Site Title -->
            <title>Đơn Hàng</title>

            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />

            <style>
                .order_details_table .table tbody td p {
                    color: #222222;
                }

                .body {
                    color: black;
                }

                tr td {
                        height: 130px !important;
                    }

                    .td_p{
                        margin-top: 35px;
                    }
            </style>
        </head>

        <body>

            <!-- Start Header Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
            <!-- End Header Area -->

            <!-- Start Banner Area -->
            <!--<section class="banner-area organic-breadcrumb">-->
            <!--    <div class="container">-->
            <!--        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">-->
            <!--            <div class="col-first">-->
            <!--                <h1 style="font-family: 'Microsoft Sans Serif'">Xác nhận</h1>-->
            <!--                <nav class="d-flex align-items-center">-->
            <!--                    <a href="index.html">Trang chủ<span class="lnr lnr-arrow-right"></span></a>-->
            <!--                    <a href="category.html">Tiếp tục mua hàng</a>-->
            <!--                </nav>-->
            <!--            </div>-->
            <!--        </div>-->
            <!--    </div>-->
            <!--</section>-->
            <!-- End Banner Area -->

            <!--================Order Details Area =================-->
            <section class="order_details section_gap">
                <div class="container">
                    <h3 class="title_confirmation">Thanh toán thành công.</h3>
                    <div class="row order_d_inner d-flex justify-content-center align-content-center">
                        
                        <div class="col-lg-4">
                            <div class="details_item">
                                <h4 class="text-center"> Thông tin giao dịch</h4>
                                <ul class="list">
                                    <li><a href="#"><span>Mã giao dịch</span> : ${transaction.transNo}</a></li>
                                    <li><a href="#"><span>Ngân hàng </span> :  ${transaction.bank.name}</a></li>
                                    <li><a href="#"><span>Ngày giao dịch </span> : ${transaction.payDateString}</a></li>
                                    <li><a href="#"><span>Số tiền giao dịch </span> : ${transaction.order.totalStringVND}</a></li>
                                    <li><a href="#"><span>Người thanh toán </span> : ${transaction.order.user.name}</a></li>
                                </ul>
                            </div>
                        </div>
                        
                        
                    </div>
                    <div class="d-flex align-content-center justify-content-center">
                          <a href="/user/orders/${transaction.order.id}" class="title_confirmation text-center">Kiểm tra đơn hàng.</a>
                    </div>
                
                </div>
            </section>
            <!--================End Order Details Area =================-->

            <!-- Start Footer Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
            <!-- End Footer Area -->

        
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                crossorigin="anonymous"></script>
            <!--gmaps Js-->
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
        </body>

        </html>