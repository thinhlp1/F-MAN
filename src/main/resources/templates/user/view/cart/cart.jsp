<!DOCTYPE html>
<html class="no-js" lang="zxx" ng-app="cartApp">

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
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
  
  
    <link rel="shortcut icon" th:href="@{/admin/plugins/images/logo-icon1.png}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/bootstrap.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/owl.carousel.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/nice-select.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/nouislider.min.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/ion.rangeSlider.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/main.css}">
    <link rel="stylesheet" th:href="@{/user-resouce/css/validform.css}">
</head>

<body>

  <!-- Start Header Area -->
  <div th:replace="~{/user/view/component/nav.html::nav}"></div>
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
                                        <input class="form-check-input" id="checkAllProducts" type="checkbox" ng-model="allChecked" ng-click="toggleAllProducts()">
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
                            <tr ng-repeat="item in listCartItem track by $index" id="item_{{item.id}}">
                                <td>
                                    <div class="form-check">
                                        <input class="form-check-input" id="checkProduct_{{$index}}" type="checkbox" ng-model="item.checked">
                                        <label class="form-check-label" for="checkProduct">
                                        </label>
                                    </div>
                                </td>
                                <td>
                                    <a class="media" style="color: rgb(119, 119, 119);" ng-href="/product/{{item.product.id}}">
                                        <div class="d-flex">
                                            <img alt="" style="width: 90px;" ng-src="{{item.product.image}}">
                                        </div>
                                        <div class="media-body">
                                            <p>{{item.product.name}} - SIZE  {{item.productSize.size.size}}</p>
                                        </div>
                                    </a>
                                </td>
                                <td>
                                    <h5 id="price_{{item.id}}" ng-value="item.product.price">{{item.product.priceStringVND}}</h5>
                                </td>
                                <td>
                                    <div class="product_count">
                                        <input class="input-text qty" id="qty_{{$index}}" maxlength="12" ng-disabled="item.quantity <= 0" ng-model="item.quantity" ng-change="updateSubTotal(item)">
                                        <button class="increase items-count" id="quantityPopup_{{item.id}}" data-toggle="popover" data-content="" ng-click="changeQuantity(cart.id, item.id, $index, 'increase')" type="button">
                                            <i class="lnr lnr-chevron-up"></i>
                                        </button>
                                        <button class="reduced items-count" ng-click="changeQuantity(cart.id, item.id, $index, 'decrease')" type="button">
                                            <i class="lnr lnr-chevron-down"></i>
                                        </button>
                                    </div>
                                </td>
                                <td>
                                    <h5 id="subTotal_{{item.id}}">{{item.subTotalStringVND}}</h5>
                                </td>
                                <td>
                                    <a class="btn btn-danger" style="color: white;" ng-click="removeProduct(cart.id, item.id)" role="button">
                                        <i aria-hidden="true" class="fa fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="d-flex align-items-end justify-content-end py-3 border-top">
                        <div>
                            <h5 id="total" class="ml-auto">Tổng tiền: {{cart.totalPrice}}</h5>
                        </div>
                    </div>
                    <div class="checkout_btn_inner d-flex align-items-center justify-content-end py-3 border-top">
                        <a class="gray_btn" href="/category/">Tiếp tục mua sắm</a>
                        <a class="primary-btn rounded-0" ng-click="checkout(cart.id)" style="line-height: 40px; color: white;">Thanh toán giỏ hàng</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->
  <!-- Start Footer Area -->
  <div th:replace="~{/user/view/component/footer.html::footer}"></div>
  <!-- End Footer Area -->

  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
  <script th:src="@{/user-resouce/js/vendor/jquery-2.2.4.min.js}"></script>
  <script th:src="@{/user-resouce/js/vendor/bootstrap.min.js}"></script>
  <script th:src="@{/user-resouce/js/jquery.ajaxchimp.min.js}"></script>
  <script th:src="@{/user-resouce/js/jquery.nice-select.min.js}"></script>
  <script th:src="@{/user-resouce/js/jquery.sticky.js}"></script>
  <script th:src="@{/user-resouce/js/nouislider.min.js}"></script>
  <script th:src="@{/user-resouce/js/countdown.js}"></script>
  <script th:src="@{/user-resouce/js/jquery.magnific-popup.min.js}"></script>
  <script th:src="@{/user-resouce/js/owl.carousel.min.js}"></script>
  <!--gmaps Js-->
  <script th:src="@{/user-resouce/js/gmaps.min.js}"></script>
  <script th:src="@{/user-resouce/js/main.js}"></script>

  <script th:src="@{/common/commonFunction.js}"></script>
  
  <script th:src="@{/user-resouce/angularjs/controller/app.js}"></script>
  <script th:src="@{/user-resouce/angularjs/controller/cartController.js}"></script>

</body>

</html>
