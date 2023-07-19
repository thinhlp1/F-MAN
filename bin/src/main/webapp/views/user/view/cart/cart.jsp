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


        <!--================Cart Area =================-->
        <section class="cart_area mt-5">
            <div class="container mt-5">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">
                                        <div class="form-check">
                                            <input class="form-check-input" id="checkAllProducts" type="checkbox"
                                                value="">
                                            <label class="form-check-label" for="checkAllProducts">
                                                Chọn
                                            </label>
                                        </div>
                                    </th>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="form-check">
                                            <input class="form-check-input" id="checkProduct" type="checkbox" value="">
                                            <label class="form-check-label" for="checkProduct">
                                            </label>
                                        </div>
                                    </td>
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
                                        <div class="product_count">
                                            <input class="input-text qty" id="sst" maxlength="12" name="qty"
                                                title="Quantity:" type="text" value="1">
                                            <button class="increase items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
                                                type="button"><i class="lnr lnr-chevron-up"></i>
                                            </button>
                                            <button class="reduced items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
                                                type="button"><i class="lnr lnr-chevron-down"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>
                                        <h5>$720.00</h5>
                                    </td>
                                    <td>
                                        <a class="btn btn-danger" href="#" role="button"><i aria-hidden="true"
                                                class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr class="py-0">
                                    <td class="py-0">
                                        <div class="form-check">
                                            <input class="form-check-input" id="checkProduct" type="checkbox" value="">
                                            <label class="form-check-label" for="checkProduct">
                                            </label>
                                        </div>
                                    </td>
                                    <td class="py-0">
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
                                        <div class="product_count">
                                            <input class="input-text qty" id="sst" maxlength="12" name="qty"
                                                title="Quantity:" type="text" value="1">
                                            <button class="increase items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
                                                type="button"><i class="lnr lnr-chevron-up"></i>
                                            </button>
                                            <button class="reduced items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
                                                type="button"><i class="lnr lnr-chevron-down"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>
                                        <h5>$720.00</h5>
                                    </td>
                                    <td>
                                        <a class="btn btn-danger" href="#" role="button"><i aria-hidden="true"
                                                class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr class="py-0">
                                    <td class="py-0">
                                        <div class="form-check">
                                            <input class="form-check-input" id="checkProduct" type="checkbox" value="">
                                            <label class="form-check-label" for="checkProduct">
                                            </label>
                                        </div>
                                    </td>
                                    <td class="py-0">
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
                                        <div class="product_count">
                                            <input class="input-text qty" id="sst" maxlength="12" name="qty"
                                                title="Quantity:" type="text" value="1">
                                            <button class="increase items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
                                                type="button"><i class="lnr lnr-chevron-up"></i>
                                            </button>
                                            <button class="reduced items-count"
                                                onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
                                                type="button"><i class="lnr lnr-chevron-down"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>
                                        <h5>$720.00</h5>
                                    </td>
                                    <td>
                                        <a class="btn btn-danger" href="#" role="button"><i aria-hidden="true"
                                                class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="d-flex align-items-center justify-content-between py-3 border-top">
                            <div>
                                <a class="gray_btn" href="#">Cập nhật giỏ hàng</a>
                            </div>
                            <div>
                                <h5>Tổng tiền: $2160.00</h5>
                            </div>
                        </div>
                        <div class="checkout_btn_inner d-flex align-items-center justify-content-end py-3 border-top">
                            <a class="gray_btn" href="../page/category.html">Tiếp tục mua sắm</a>
                            <a class="primary-btn rounded-0" href="cart_checkout.html" style="line-height: 40px">Thanh
                                toán giỏ
                                hàng</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->

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