<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html class="no-js" lang="zxx">

    <head>
        <!-- Mobile Specific Meta -->
        <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
        <!-- Favicon-->
        <link href="${pageContext.request.contextPath}/views/user/img/fav.png" rel="shortcut icon">
        <!-- Author Meta -->
        <meta content="CodePixar" name="author">
        <!-- Meta Description -->
        <meta content="" name="description">
        <!-- Meta Keyword -->
        <meta content="" name="keywords">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <!-- Site Title -->
        <title>Karma Shop</title>

        <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
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
                <h3 class="title_confirmation" style="font-family: Roboto">Xin cảm ơn bạn đã đặt hàng. Đơn hàng đang
                    chờ
                    xác
                    nhận.</h3>
                <div class="row order_d_inner">
                    <div class="col-lg-6">
                        <div class="details_item">
                            <h4 style="font-family: Roboto">Thông tin hóa đơn</h4>
                            <ul class="list">
                                <li><a href="#"><span>Mã hóa đơn</span> : 60235</a></li>
                                <li><a href="#"><span>Ngày</span> : 1/1/2023</a></li>
                                <li><a href="#"><span>Tổng tiền</span> : USD 2210</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="details_item">
                            <h4 style="font-family: Roboto">Địa chỉ nhận hàng</h4>
                            <ul class="list">
                                <li><a href="#"><span>Tên</span> : Bốc đầu tổ lái</a></li>
                                <li><a href="#"><span>Số điện thoại</span> : 00111xxx</a></li>
                                <li><a href="#"><span>Địa chỉ</span> : Đam mê trong máu city</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="order_details_table">
                    <h2 style="font-family: Roboto">Thông tin hóa đơn chi tiết</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <p>Pixelstore fresh Blackberry</p>
                                    </td>
                                    <td>
                                        <h5>x 02</h5>
                                    </td>
                                    <td>
                                        <p>$720.00</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>Pixelstore fresh Blackberry</p>
                                    </td>
                                    <td>
                                        <h5>x 02</h5>
                                    </td>
                                    <td>
                                        <p>$720.00</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>Pixelstore fresh Blackberry</p>
                                    </td>
                                    <td>
                                        <h5>x 02</h5>
                                    </td>
                                    <td>
                                        <p>$720.00</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4 style="font-family: Roboto">Tạm tính</h4>
                                    </td>
                                    <td>
                                        <h5></h5>
                                    </td>
                                    <td>
                                        <p>$2160.00</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4 style="font-family: Roboto">Tổng tiền</h4>
                                    </td>
                                    <td>
                                        <h5></h5>
                                    </td>
                                    <td>
                                        <p>$2210.00</p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
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