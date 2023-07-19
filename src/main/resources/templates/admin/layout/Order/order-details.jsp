<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% Object id=request.getSession().getAttribute("cartId"); int cartId=0; if (id !=null) { cartId=(int) id; }%>
            <!DOCTYPE html>
            <html dir="ltr" lang="en">

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <!-- Tell the browser to be responsive to screen width -->
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>FMan | Order Details</title>
                <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp" />
                <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
                <style>
                    ul {
                        list-style: none;
                    }

                    ul span {
                        font-size: 15px;
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
                <div class="preloader">
                    <div class="lds-ripple">
                        <div class="lds-pos"></div>
                        <div class="lds-pos"></div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- Main wrapper - style you can find in pages.scss -->
                <!-- ============================================================== -->
                <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
                    data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
                    <!-- ============================================================== -->
                    <!-- Header -->
                    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/header.jsp" />
                    <!-- Header -->
                    <!-- Sidebar -->
                    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/sidebar.jsp" />
                    <!-- Sidebar -->
                    <div id="container" class="page-wrapper">
                        <!-- Taskbar -->
                        <jsp:include
                            page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp" />
                        <!-- Taskbar -->
                        <div class="container-fluid">
                            <!--      CODE TRONG DDAAY          -->
                            <!--BACK-->
                            <div class="back mb-3">
                                <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/orders/">
                                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                    Quay Lại
                                </a>
                            </div>
                            <section class="order_details ">
                                <div class="container">

                                    <div class="row order_d_inner">
                                        <div class="col-lg-4">
                                            <div class="details_item">
                                                <h4 style="font-family: Roboto">Thông tin hóa đơn</h4>
                                                <ul class="list">
                                                    <li><span>Mã hóa đơn</span> :${order.id}</li>
                                                    <li><span>Ngày tạo</span> : ${order.createAtString}</li>
                                                    <li><span>Tổng tiền</span> : ${order.totalStringVND}</li>

                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="details_item">
                                                <h4 style="font-family: Roboto">Thông tin hóa đơn</h4>
                                                <ul class="list">
                                                    <li><span>Thanh toán</span> :${order.paymentMethod.name}</li>
                                                    <li><span>Voucher</span> : ${ empty order.voucher.name ? 'Không có'
                                                        : order.voucher.name}</li>
                                                    <li><span>Trạng thái :</span><span
                                                            id="span_state">${order.orderState.name}</span>

                                                    <li id="orderNoteLi" class="${empty order.note ? 'd-none' : no}">
                                                        <span>Lý do :</span><span id="orderNote">${order.note}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="details_item">
                                                <h4 style="font-family: Roboto">Địa chỉ nhận hàng</h4>
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
                                            <div class="row mx-0 container-fluid">
                                                <div class="col-10">
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
                                                            <c:forEach var="item" items="${order.orderItems}"
                                                                varStatus="index">
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
                                                                        <p class="td_p"  id="itemQuantity_${item.id}"
                                                                            value="${item.quantity}">${item.quantity}
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="td_p" >${item.totalStringVND}</p>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>



                                                        </tbody>

                                                    </table>
                                                </div>
                                                <div class="col-2">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Tồn kho</th>

                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="item" items="${order.orderItems}"
                                                                varStatus="index">
                                                                <tr>
                                                                    <td>
                                                                        <p  class="td_p" id="stockQuantity_${item.id}"
                                                                            value="${item.productSize.quantity}">
                                                                            ${item.productSize.quantity}</p>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>



                                                        </tbody>

                                                    </table>
                                                </div>
                                            </div>
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

                                                    <c:if test="${order.orderState.id eq 'PENDING_APPROVAL'}">
                                                        <a id="btn-cancel" data-bs-toggle="modal"
                                                            data-bs-target="#cancelModel"
                                                            class="btn btn-danger ms-3 text-white mr-3"> Hủy
                                                        </a>

                                                        <a id="btn-approve" onclick="approve()"
                                                            class="btn btn-blue-primary ms-3 text-white">
                                                            Duyệt
                                                        </a>
                                                    </c:if>


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>

                        </div>



                        <div class="modal fade" id="cancelModel" tabindex="-1" role="dialog"
                            aria-labelledby="editModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel">Hủy đơn</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="">
                                            <p class="text-danger"> Điền lý do hủy đơn </p>
                                            <textarea style="width: 100%;" name="note" id="note" rows="5"
                                                placeholder="Ghi chú"></textarea>
                                        </div>
                                        <div class=" d-flex justify-content-end my-2 mx-2">
                                            <a id="btn-cancel" onclick="cancel()" type="button"
                                                class="btn btn-danger ms-3 text-white mr-3" style="display: block;"> Xác
                                                nhận hủy đơn
                                            </a>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>


                        <!-- Footer -->
                        <jsp:include
                            page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                        <script>


                            function checkQuantity() {
                                var listStockQuantity = document.querySelectorAll('p[id^="stockQuantity_"]');
                                var rs;
                                try {
                                    listStockQuantity.forEach(function (item) {

                                        id = item.id.split('_')[1]
                                        var stockQuantiy = parseInt(item.getAttribute("value"));
                                        var itemQuantity = parseInt(document.getElementById("itemQuantity_" + id).getAttribute("value"));
                                        console.log(stockQuantiy);
                                        console.log(itemQuantity);
                                        if (stockQuantiy < itemQuantity) {
                                            rs = (confirm("Số lượng tồn kho không đủ cho đơn hàng này. Chắc chắn duyệt"))
                                            throw new Error("Break the loop.")
                                        }
                                    })
                                } catch (error) {

                                }
                                if (rs === undefined) {
                                    return true;
                                } else {
                                    return rs;
                                }
                            }

                            function cancel() {
                                var note = document.getElementById("note").value;
                                if (note === "") {
                                    note = "Không có lý do";
                                }
                                $.ajax({
                                    type: "POST",
                                    url: "/admin/orders/cancel/${order.id}",
                                    data: note,
                                    dataType: "text",
                                    contentType: "text/plain",
                                    success: function (response) {
                                        // Xử lý phản hồi từ Controller (nếu có)
                                        console.log(response);
                                        document.getElementById("btn-approve").classList.add("d-none");
                                        document.getElementById("btn-cancel").classList.add("d-none");
                                        document.getElementById("span_state").innerHTML = "Đã hủy";
                                        document.getElementById("quantityOrderApprove").innerHTML = parseInt( document.getElementById("quantityOrderApprove").innerHTML) - 1;
                                        $('#cancelModel').modal('hide');
                                        document.getElementById("orderNoteLi").classList.remove("d-none");
                                        document.getElementById("orderNote").innerHTML = note;


                                    },
                                    error: function (xhr, status, error) {
                                        // Xử lý lỗi (nếu có)
                                        console.log(error);
                                        alert("Hủy đơn không thành công");
                                    }
                                });
                            }

                            function approve() {
                                if (checkQuantity() == false) {

                                    return;
                                }

                                $.ajax({
                                    type: "POST",
                                    url: "/admin/orders/approve/${order.id}",
                                    success: function (response) {
                                        // Xử lý phản hồi từ Controller (nếu có)
                                        console.log(response);
                                        document.getElementById("btn-approve").classList.add("d-none");
                                        document.getElementById("btn-cancel").classList.add("d-none");
                                        document.getElementById("span_state").innerHTML = "Đang vận chuyển";
                                        document.getElementById("quantityOrderApprove").innerHTML = parseInt( document.getElementById("quantityOrderApprove").innerHTML) - 1;
                                        var listStockQuantity = document.querySelectorAll('p[id^="stockQuantity_"]');
                                        listStockQuantity.forEach(function (item) {
                                            id = item.id.split('_')[1]
                                            var stockQuantiy = parseInt(item.getAttribute("value"));
                                            var itemQuantity = parseInt(document.getElementById("itemQuantity_" + id).getAttribute("value"));
                                            if (stockQuantiy - itemQuantity < 0) {
                                                item.innerHTML = "0";
                                            } else {
                                                item.innerHTML = stockQuantiy - itemQuantity;
                                            }

                                        })
                                    },
                                    error: function (xhr, status, error) {
                                        // Xử lý lỗi (nếu có)
                                        console.log(error);
                                        alert("Duyệt đơn không thành công");
                                    }
                                });
                            }
                        </script>
                        <!-- Footer -->
                    </div>
                </div>

                <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
            </body>

            </html>