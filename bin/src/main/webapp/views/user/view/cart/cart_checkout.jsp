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

        <div class="container-fluid my-5">
            <div class="row">
                <div class="col-lg-8">
                    <!--================Cart Area =================-->
                    <section class="cart_area">
                        <div class="container">
                            <div class="cart_inner">
                                <div class="table-responsive">
                                    <table class="table table-sm">
                                        <thead>
                                            <tr>
                                                <th scope="col">Sản phẩm</th>
                                                <th scope="col">Giá</th>
                                                <th scope="col">Số lượng</th>
                                                <th scope="col">Tổng tiền</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="d-flex">
                                                            <img alt="" class="w-75"
                                                                src="${pageContext.request.contextPath}/views/user/img/cart.jpg">
                                                        </div>
                                                        <div class="media-body">
                                                            <p>Minimalistic shop for multipurpose use</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$360.00</h5>
                                                </td>
                                                <td>
                                                    <div class="product_count_checkout">
                                                        <input class="input-text qty text-center" id="sst" maxlength="1"
                                                            name="qty" title="Quantity:" type="text" value="1">
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$720.000</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="d-flex">
                                                            <img alt="" class="w-75"
                                                                src="${pageContext.request.contextPath}/views/user/img/cart.jpg">
                                                        </div>
                                                        <div class="media-body">
                                                            <p>Minimalistic shop for multipurpose use</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$360.00</h5>
                                                </td>
                                                <td>
                                                    <div class="product_count_checkout">
                                                        <input class="input-text qty text-center" id="sst" maxlength="1"
                                                            name="qty" title="Quantity:" type="text" value="1">
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$720.000</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <div class="d-flex">
                                                            <img alt="" class="w-75"
                                                                src="${pageContext.request.contextPath}/views/user/img/cart.jpg">
                                                        </div>
                                                        <div class="media-body">
                                                            <p>Minimalistic shop for multipurpose use</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$360.00</h5>
                                                </td>
                                                <td>
                                                    <div class="product_count_checkout">
                                                        <input class="input-text qty text-center" id="sst" maxlength="1"
                                                            name="qty" title="Quantity:" type="text" value="1">
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5>$720.000</h5>
                                                </td>
                                            </tr>
                                            <tr class="bottom_button">
                                                <td>
                                                    <a class="gray_btn" href="#">Cập nhật giỏ hàng</a>
                                                </td>
                                                <td>

                                                </td>
                                                <td>

                                                </td>
                                                <td>

                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--================End Cart Area =================-->

                </div>
                <div class="col-lg-4">
                    <!--================Cart Area =================-->
                    <section class="cart_payment" style="margin-top: 100px">
                        <div class="container">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title font-weight-bold">Thanh toán</h3>
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <h6 style="font-family: sans-serif">Thông tin người nhận</h6>
                                        </div>
                                        <div><a href="#">
                                                <h6 class="font-weight-bold" style="font-family: sans-serif"><i
                                                        aria-hidden="true" class="fa fa-angle-double-right"></i>
                                                    Thay
                                                    đổi
                                                    thông tin</h6>
                                            </a></div>
                                    </div>
                                    <form action="" class="my-2">
                                        <div class="form-group">
                                            <label for="name">Họ tên</label>
                                            <input class="form-control" id="name" placeholder="Nhập họ tên..."
                                                style="font-size: 0.9rem" type="text">
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Số điện thoại</label>
                                            <input class="form-control" id="phone" placeholder="Nhập số điện thoại..."
                                                style="font-size: 0.9rem" type="text">
                                        </div>
                                        <div class="form-group">
                                            <label for="address">Địa chỉ</label>
                                            <input class="form-control" id="address"
                                                placeholder="Nhập địa chỉ người nhận..." style="font-size: 0.9rem"
                                                type="text">
                                        </div>
                                        <div class="form-group">
                                            <label for="voucher">Mã giảm giá</label>
                                            <input class="form-control" id="voucher" placeholder="Nhập mã giảm giá..."
                                                style="font-size: 0.9rem" type="text">
                                        </div>
                                        <div class="form-group">
                                            <p>Phương thức thanh toán</p>
                                            <div class="cc-selector-2">
                                                <input id="momo" name="creditcard" type="radio" value="momo" />
                                                <label class="drinkcard-cc momo" for="momo"></label>
                                                <input checked="checked" id="zalo" name="creditcard" type="radio"
                                                    value="zalo" />
                                                <label class="drinkcard-cc zalo-pay" for="zalo"></label>
                                                <input checked="checked" id="m-banking" name="creditcard" type="radio"
                                                    value="mobile-banking" />
                                                <label class="drinkcard-cc mobile-banking" for="m-banking"></label>
                                                <input checked="checked" id="cod" name="creditcard" type="radio"
                                                    value="cod" />
                                                <label class="drinkcard-cc cod-payment" for="cod"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="cart_payment_info my-3">
                                                <h4 class="card-title font-weight-bold"
                                                    style="font-family: 'Microsoft Sans Serif'">
                                                    Thông tin đơn hàng</h4>
                                                <h5 class="font-weight-bold"
                                                    style="font-family: 'Microsoft Sans Serif'">
                                                    Tạm tính: <span class="text-danger">$1000</span></h5>
                                                <h5 class="font-weight-bold"
                                                    style="font-family: 'Microsoft Sans Serif'">
                                                    Tổng tiền: <span class="text-danger">$1000</span></h5>
                                            </div>
                                        </div>


                                        <button class="genric-btn primary radius btn-block"><span
                                                style="font-size: 1rem">Thanh toán</span>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--================End Cart Area =================-->
                </div>
            </div>

        </div>

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