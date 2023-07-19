<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <% Object id=request.getSession().getAttribute("cartId"); int cartId=0; if (id !=null) { cartId=(int) id; }%>
        <% Cookie[] cookies=null; // Get an array of Cookies associated with the this domain
        cookies=request.getCookies(); String token=null; if( cookies !=null ) { for (Cookie cookie : cookies) { if
        (cookie.getName().equals("token")) { token=cookie.getValue(); request.setAttribute("token", token); break; }
        } } %>
            <!DOCTYPE html>
            <html lang="zxx" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Author Meta -->
    <meta name="author" content="CodePixar">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>4Man | ${productDetails.name.toUpperCase()}</title>
    <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp"/>
    <style>
        .size-filter li {
            border: 1px solid #ccc;
            padding: 3px 5px;
            margin: 5px 5px;
            padding-left: 5px !important;
        }
    </style>
</head>

            <body>

                <!-- Start Header Area -->
                <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
                <!-- End Header Area -->

                <!-- Start Banner Area -->
                <section class="section_gap">

                </section>
                <!-- End Banner Area -->

                <!--================Single Product Area =================-->
                <div class="product_image_area">
                    <div class="container">
                        <div class="row s_product_inner">
                            <div class="col-lg-6">
                                <div class="single-prd-item">
                                    <img class="img-fluid w-100"
                                        src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${productDetails.image}"
                                        alt="${productDetails.name}">
                                </div>
                            </div>
                            <div class="col-lg-5 offset-lg-1">
                                <div class="s_product_text">
                                    <h3>${productDetails.name}</h3>
                                    <h2>${productDetails.getPriceStringVND()}</h2>
                                    <ul class="list">
                                        <li><a class="active" href="#"><span>Loại giày</span> :
                                                ${productDetails.productType.name}</a>
                                        </li>
                                        <li><a href="#"><span>Thương hiệu</span> : ${productDetails.brand.name}</a></li>
                                    </ul>
                                    <p>${productDetails.desc}</p>

                                    <div class="common-filter size-filter mb-3">
                                        <div class="head">Size giày</div>
                                        <form action="#">
                                            <ul class="list-unstyled d-flex flex-wrap">
                                                <c:forEach var="size" items="${productDetails.productSizes}">
                                                    <li class="filter-list">
                                                        <input class="pixel-radio" type="radio" id="${size.id}"  ${size.quantity == 0 ? "disabled" : "" }
                                                            onclick="updateSize(${size.quantity},${size.id})"
                                                            name="color">
                                                        <label for="${size.id}">${size.size.size.intValue()}
                                                            <sub value="${size.quantity}">(${size.getAvailableQuantity()}
                                                                SP)</sub></label>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </form>
                                    </div>

                                    <div class="product_count">
                                        <label for="qty">Quantity:</label>
                                        <input type="text" name="qty" id="qty" maxlength="12" value="0" readonly
                                            title="Quantity:" class="input-text qty">
                                        <button class="increase items-count" type="button"><i
                                                onclick="increaseQuantity()" class="lnr lnr-chevron-up"></i></button>
                                        <button class="reduced items-count" type="button"><i
                                                onclick="decreaseQuantity()" class="lnr lnr-chevron-down"></i></button>
                                    </div>

                                    <div class="card_area d-flex align-items-center">
                                        <a class="primary-btn" style="color: white;" onclick="addToCart()"
                                            type="button">Thêm vào giỏ hàng</a>
                                        <a class="primary-btn" style="color: white;" onclick="buyNow()" type="button">Mua luôn</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--================End Single Product Area =================-->

                <!--================Product Description Area =================-->
                <section class="product_description_area">
                    <div class="container">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item ">
                                <a class="nav-link active	" id="home-tab" data-toggle="tab" href="#home" role="tab"
                                    aria-controls="home" aria-selected="true">Mô tả</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                                    aria-controls="profile" aria-selected="false">Thông số</a>
                            </li>


                        </ul>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                ${productDetails.desc}
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div class="table-responsive">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <h5>Width</h5>
                                                </td>
                                                <td>
                                                    <h5>128mm</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Height</h5>
                                                </td>
                                                <td>
                                                    <h5>508mm</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Depth</h5>
                                                </td>
                                                <td>
                                                    <h5>85mm</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Weight</h5>
                                                </td>
                                                <td>
                                                    <h5>52gm</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Quality checking</h5>
                                                </td>
                                                <td>
                                                    <h5>yes</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Freshness Duration</h5>
                                                </td>
                                                <td>
                                                    <h5>03days</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>When packeting</h5>
                                                </td>
                                                <td>
                                                    <h5>Without touch of hand</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h5>Each Box contains</h5>
                                                </td>
                                                <td>
                                                    <h5>60pcs</h5>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </section>
                <!--================End Product Description Area =================-->

                <!-- Start Product List -->
                <section>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-6 text-center">
                                <div class="section-title">
                                    <h1>Sản phẩm</h1>
                                    <p>Những sản phẩm cùng loại</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach var="item" items="${listProducts}">
                                <jsp:include
                                    page="${pageContext.request.contextPath}/views/user/view/component/single-product.jsp">
                                    <jsp:param name="id" value="${item.id}" />
                                    <jsp:param name="name" value="${item.name}" />
                                    <jsp:param name="image" value="${item.image}" />
                                    <jsp:param name="price" value="${item.getPriceStringVND()}" />
                                    <jsp:param name="desc" value="${item.desc}" />
                                </jsp:include>
                            </c:forEach>
                        </div>
                    </div>
                </section>
                <!-- End Product List -->

                <!-- Start Footer Area -->
                <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp" />


                <!-- End Footer Area -->
                <script>
                    var quantityInput = document.getElementById("qty");
                    var quantitySize = -1;
                    var sizeId = '';

                    function updateSize(size, id) {

                        quantitySize = size;
                        sizeId = id;
                        quantityInput.value = 1;
                    }

                    function increaseQuantity() {

                        if (quantitySize == -1) {
                            alert("Vui lòng chọn size")
                            return;
                        }

                        var currentValue = parseInt(quantityInput.value);
                        if (currentValue < quantitySize) {

                            quantityInput.value = currentValue + 1;
                        }
                    }

                    function decreaseQuantity() {
                        if (quantitySize == -1) {
                            alert("Vui lòng chọn size")
                            return;
                        }
                        var currentValue = parseInt(quantityInput.value);
                        if (currentValue > 1) {
                            quantityInput.value = currentValue - 1;
                        }
                    }


                    function addToCart() {

                        if ('${not empty token}' === 'false'){
                            alert("Vui lòng đăng nhập để mua hàng");
                            window.location.href = "${pageContext.request.contextPath}/auth/login";
                            return;
                        }

                        var quantity = quantityInput.value;
                        var productSizeId = sizeId;
                        if (productSizeId === '' || quantitySize == -1) {
                            alert("Vui lòng chọn size sản phẩm");
                            return;
                        }
                        var cartId = parseInt('${cartId}');
                        var productId = '${productDetails.id}';

                        var url = "${pageContext.request.contextPath}/user/carts/product-add/" + cartId + "?productId=" + productId + "&productSizeId=" + productSizeId + "&quantity=" + quantity;
                        console.log(url);
                        $.ajax({
                            type: "GET",
                            url: url,
                            contentType: "application/json",
                            success: function (response) {
                                console.log(response);
                                
                                    alert("Đã thêm")
                                
                                var cart = document.getElementById('cartQuantity');
                                var cartQuantity = parseFloat(cart.innerHTML)
                                cart.innerHTML = cartQuantity+1;
                            },
                            error: function (xhr, status, error) {
                                console.log(error);
                                alert("Thêm không thành công")
                            }
                        });

                    }

                    function buyNow(){
                        if ('${not empty token}' === 'false'){
                            alert("Vui lòng đăng nhập để mua hàng");
                            window.location.href = "${pageContext.request.contextPath}/auth/login"
                            return;;
                        }
                        var quantity = quantityInput.value;
                        var productSizeId = sizeId;
                        if (productSizeId === '' || quantitySize == -1) {
                            alert("Vui lòng chọn size sản phẩm");
                            return;
                        }
                        var cartId = parseInt('${cartId}');
                        var productId = '${productDetails.id}';

                        var url = "${pageContext.request.contextPath}/user/carts/checkout-bynow"+ "?productSizeId=" + productSizeId + "&quantity=" + quantity;
                        console.log(url);
                        window.location.href = url;

                    }
                </script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                    integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                    crossorigin="anonymous"></script>
                <!--gmaps Js-->
                <script
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
                <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />

            </body>

            </html>