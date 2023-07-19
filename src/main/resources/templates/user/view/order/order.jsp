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
                    <h3 class="title_confirmation" style="font-family: Roboto">Xin cảm ơn bạn đã đặt hàng. Đơn hàng đang
                        chờ
                        xác
                        nhận.</h3>
                    <div class="row order_d_inner">
                        <div class="col">
                            <div class="details_item">
                                <h4 style="font-family: Roboto">Thông tin hóa đơn</h4>
                                <ul class="list">
                                    <li><span>Mã hóa đơn</span> :${order.id}</li>
                                    <li><span>Ngày tạo</span> : ${order.createAtString}</li>
                                    <li><span>Tổng tiền</span> : ${order.totalStringVND}</li>

                                </ul>
                            </div>
                        </div>
                        <div class="col">
                            <div class="details_item">
                                <h4 style="font-family: Roboto">Thông tin hóa đơn</h4>
                                <ul class="list">
                                    <li><span>Thanh toán</span> :${order.paymentMethod.name}</li>
                                    <li><span>Voucher</span> : ${ empty order.voucher.name ? 'Không có'
                                        : order.voucher.name}</li>
                                    <li><span>Trạng thái :</span>  <span id="span_state">${order.orderState.name}</span></li>
                                    <li id="orderNoteLi" class="${empty order.note ? 'd-none' : no}"><span>Lý do
                                            :</span><span id="orderNote">${order.note}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col ${order.paymentMethod.id != 'VISA'  ? 'd-none' : no}">
                            <div class="details_item">
                                <h4 style="font-family: Roboto">Thông tin thanh toán</h4>
                                <ul class="list">
                                    <li><span>Mã giao dịch</span> : ${transaction.transNo}</li>
                                    <li><span>Ngân hàng</span> : ${transaction.bank.name}</li>
                                    <li><span>Ngày thanh toán</span> : ${transaction.payDateString}</li>
                                </ul>
                            </div>
                        </div>
                        <div class="col">
                            <div class="details_item">
                                <h4 style="font-family: Roboto">Địa chỉ nhận hàng </h4>
                                <ul class="list">
                                    <li><span>Tên</span> : ${order.address.receiverName}</li>
                                    <li><span>Số điện thoại</span> : ${order.address.numberPhone}</li>
                                    <li><span>Địa chỉ</span> : ${order.address.address}</li>
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
                                        <th scope="col">Size</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Thành tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${order.orderItems}" varStatus="index">
                                        <tr>
                                            <td>
                                                <div class="d-flex justify-content-start align-items-center">
                                                    <img alt="" style="width: 90px; height: 100px; border-radius: 30px;"
                                                    src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${item.product.image}" >
                                                    <p class="ml-3">${item.product.name}</p>
                                                   </div>
                                            </td>
                                            <td>
                                                <p class="td_p" >${item.productSize.size.size}</p>
                                            </td>
                                            <td>
                                                <p class="td_p" >${item.quantity}</p>
                                            </td>
                                            <td>
                                                <p class="td_p">${item.totalStringVND}</p>
                                            </td>
                                        </tr>
                                    </c:forEach>



                                </tbody>

                            </table>
                            <div class="row mx-0 mt-5">
                                <div class=" col ">
                                    <div>
                                        <h4 style="font-family: Roboto">Tạm tính: ${tempTotal}</h4>
                                    </div>
                                    <!-- <c:if test="${tempTotal != ''}"> -->
                                    <div>
                                        <h4 style="font-family: Roboto">Giảm giá: ${discount}</h4>
                                    </div>
                                    <!-- </c:if> -->
                                    <div>
                                        <h4 style="font-family: Roboto">Tổng tiền:
                                            ${order.totalStringVND}
                                        </h4>
                                    </div>
                                </div>
                                <div class=" col d-flex align-items-end justify-content-end">

                                    <c:if test="${order.orderState.id eq 'PENDING_APPROVAL' or order.orderState.id eq 'WAIT_PAYMENT'}">
                                        <a id="btn-cancel" onclick="cancelOrder()"
                                            class="btn btn-danger ms-3 text-white mr-3"> Hủy
                                        </a>
                                    </c:if>
                                    <c:if test="${ order.orderState.id eq 'WAIT_PAYMENT'}">
                                        <a id="btn-cancel"  href="/user/carts/recheckout/${orderId}"
                                            class="btn ms-3 text-white mr-3" style="background: rgb(255, 162, 0); color: white;"> Tiếp tục thanh toán
                                        </a>
                                    </c:if>



                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--================End Order Details Area =================-->

            <!-- Start Footer Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
            <!-- End Footer Area -->

            <script>
                function cancelOrder() {  
                    
                    if (!confirm("Chắc chăn hủy")){
                        return;
                    }
                    
                    $.ajax({
                        type: "POST",
                        url: "/user/orders/cancel/${order.id}",
                        data: "Người dùng hủy đơn",
                        dataType: "text",
                        contentType: "text/plain",
                        success: function (response) {
                            // Xử lý phản hồi từ Controller (nếu có)
                            console.log(response);
                            document.getElementById("btn-cancel").classList.add("d-none");
                            document.getElementById("span_state").innerHTML = "Đã hủy";
                            document.getElementById("orderNoteLi").classList.remove("d-none");
                            document.getElementById("orderNote").innerHTML = "Người dùng hủy đơn";


                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi (nếu có)
                            console.log(error);
                            alert("Hủy đơn không thành công");
                        }
                    });
                }
            </script>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                crossorigin="anonymous"></script>
            <!--gmaps Js-->
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
        </body>

        </html>