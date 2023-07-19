<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% Object id=request.getSession().getAttribute("userId"); int userId=0; if (id !=null) { userId=(int) id; }%>
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
                <title>Thanh toán</title>

                <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />

                <style>
                    .modal-dialog {
                        max-width: none !important;
                        width: 70%;
                    }
                </style>
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
                                                    <c:forEach var="item" items="${listItem}" varStatus="index">
                                                        <tr id="item_${item.id}">
                                                            <td>
                                                                <div class="media">
                                                                    <div class="d-flex">
                                                                        <img alt="" style="width: 90px;"
                                                                            src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${item.product.image}">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>${item.product.name} - SIZE
                                                                            ${item.productSize.size.size}</p>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <h5>${item.product.priceStringVND}</h5>
                                                            </td>
                                                            <td>
                                                                <div class="product_count_checkout">
                                                                    <input class="input-text qty text-center" id="sst"
                                                                        maxlength="2" name="qty" title="Quantity:"
                                                                        type="text" value="${item.quantity}">
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <h5>${item.subTotalStringVND}</h5>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

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
                                                        <h6 data-toggle="modal" data-target="#addressModel"
                                                            class="font-weight-bold" style="font-family: sans-serif"><i
                                                                aria-hidden="true" class="fa fa-angle-double-right"></i>
                                                            Thay đổi thông tin</h6>
                                                    </a></div>
                                            </div>
                                            <form id="userAddress" value="${addressDefault.id}" class="my-2">
                                                <div class="form-group">
                                                    <label for="name">Họ tên</label>
                                                    <input class="form-control" id="receiverName"
                                                        placeholder="Nhập họ tên..."
                                                        value="${addressDefault.receiverName}" style="font-size: 0.9rem"
                                                        type="text" readonly="true">
                                                </div>
                                                <div class="form-group">
                                                    <label for="phone">Số điện thoại</label>
                                                    <input class="form-control" id="numberPhone"
                                                        placeholder="Nhập số điện thoại..."
                                                        value="${addressDefault.numberPhone}" style="font-size: 0.9rem"
                                                        type="text" readonly="true">
                                                </div>
                                                <div class="form-group">
                                                    <label for="address">Địa chỉ</label>
                                                    <textarea class="form-control" id="address" rows="3"
                                                        placeholder="Nhập địa chỉ người nhận..."
                                                        style="font-size: 0.9rem" value="${addressDefault.address}"
                                                        type="text" readonly="true">${addressDefault.address}</textarea>
                                                </div>
                                                <div class="form-group mb-5">
                                                    <label for="voucher">Mã giảm giá</label>
                                                    <div class="row" style="height: 20px;">
                                                        <div class="col-9">
                                                            <input class="form-control" id="voucher"
                                                                onblur="applyVoucher()"
                                                                placeholder="Nhập mã giảm giá..."
                                                                style="font-size: 0.9rem" type="text">
                                                            <p id="voucher_message" class="text-danger"></p>
                                                        </div>
                                                        <div class="col-2">
                                                            <a type="button" onclick="applyVoucher()"
                                                                class="btn btn-primary" style="color: white;">Áp
                                                                dụng</a>
                                                        </div>
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <p>Phương thức thanh toán</p>
                                                    <div class="cc-selector-2">
                                                        <input id="MOMO" name="payment" type="radio" value="MOMO" />
                                                        <label class="drinkcard-cc momo" for="MOMO"></label>
                                                        <input checked="checked" id="ZALO_PAY" name="payment"
                                                            type="radio" value="ZALO_PAY" />
                                                        <label class="drinkcard-cc zalo-pay" for="ZALO_PAY"></label>
                                                        <input checked="checked" id="VISA" name="payment" type="radio"
                                                            value="VISA" />
                                                        <label class="drinkcard-cc mobile-banking" for="VISA"></label>
                                                        <input checked="checked" id="CASH" name="payment" type="radio"
                                                            value="CASH" />
                                                        <label class="drinkcard-cc cod-payment" for="CASH"></label>
                                                    </div>
                                                    <div id="bank-select" style="display: none;">

                                                        <input checked="" id="NCB" name="bank" type="radio"
                                                            value="NCB" />

                                                        <img style="height: 70px ;" class="my-3"
                                                            src="${pageContext.request.contextPath}/views/user/img/vnpay.png"
                                                            alt="">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="cart_payment_info my-3">
                                                        <h4 class="card-title font-weight-bold"
                                                            style="font-family: 'Microsoft Sans Serif'">
                                                            Thông tin đơn hàng</h4>
                                                        <h5 class="font-weight-bold"
                                                            style="font-family: 'Microsoft Sans Serif'">
                                                            Tạm tính: <span class="text-danger" id="subTotal"
                                                                value="${subTotal}">${subTotal}</span></h5>
                                                        <h5 class="font-weight-bold"
                                                            style="font-family: 'Microsoft Sans Serif'">
                                                            Giảm giá: <span class="text-danger" id="discount"
                                                                value="${discount}">${discount}</span></h5>
                                                        <h5 class="font-weight-bold"
                                                            style="font-family: 'Microsoft Sans Serif'">
                                                            Tổng tiền: <span class="text-danger" id="total"
                                                                value="${total}">${totalStr}</span></h5>
                                                    </div>
                                                </div>


                                                <button type="button" class="genric-btn primary radius btn-block"
                                                    style="font-size: 1rem;" onclick="checkout()">Thanh toán
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

                <div class="modal fade" id="addressModel" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editModalLabel">Chọn địa chỉ</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="table-responsive text-center">
                                    <table class="table table-striped table-hover">
                                        <thead class="" style="background: rgb(255, 162, 0)">
                                            <tr class="text-white">
                                                <th scope="col"><span>Họ tên</span></th>
                                                <th scope="col"><span>Số điện thoại</span></th>
                                                <th scope="col"><span>Địa chỉ</span></th>

                                                <th scope="col"><span></span></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="address" items="${listAddress}" varStatus="index">
                                                <tr id="address_${address.id}">
                                                    <td>
                                                        ${address.receiverName}
                                                    </td>
                                                    <td>
                                                        ${address.numberPhone}
                                                    </td>

                                                    <td>
                                                        ${address.address}
                                                    </td>

                                                    <td>
                                                        <button class="btn"
                                                            style="color: white; background-color: rgb(207, 136, 13);"
                                                            onclick="changeAddress(${address.id})">Chọn</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="row d-flex justify-content-end my-2 mx-2">
                                    <a class="btn" type="button"
                                        href="http://localhost:8080/user/address/all?user-id=${userId}"
                                        style="color: white; background-color: rgb(238, 159, 21);">Thêm địa chỉ</a>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
                </div>

                <script>
                    if (!${ hasAddress }) {
                        window.onload = function () {
                            alert("Vui lòng tạo địa chỉ nhận hàng.");
                            window.location.href = "/user/address/all?user-id=" + "${userId}";
                        };
                    }
                    const paymentRadios = document.getElementsByName("payment");

                    // Lặp qua từng radio input trong danh sách
                    paymentRadios.forEach(function (radio) {
                        // Gán sự kiện "click" cho từng radio input
                        radio.addEventListener("click", function () {
                            // Kiểm tra nếu radio input có value là "VISA" được chọn
                            if (this.value === "VISA") {
                                // Hiển thị bank-select
                                document.getElementById("bank-select").style.display = "block";
                            } else {
                                // Ẩn bank-select
                                document.getElementById("bank-select").style.display = "none";
                            }
                        });
                    });
                </script>

                <script>
                    function changeAddress(addressId) {
                        var tr = document.getElementById('address_' + addressId);
                        if (tr) {
                            var tds = tr.getElementsByTagName('td');
                            if (tds.length >= 3) {
                                var receiverName = tds[0].innerHTML.trim();
                                var numberPhone = tds[1].innerHTML.trim();
                                var address = tds[2].innerHTML.trim();

                                document.getElementById("receiverName").value = receiverName;
                                document.getElementById("numberPhone").value = numberPhone;
                                document.getElementById("address").innerHTML = address;
                            }
                        }
                        $('#addressModel').modal('hide');
                    }

                    

                </script>


                <script>
                    var isVoucherValid;
                    function applyVoucher() {
                        var voucherCode = document.getElementById("voucher").value;
                        if (voucherCode == "") {
                            document.getElementById("voucher_message").innerHTML = "Vui lòng nhập voucher";
                        } else {

                            var total = convertCurrencyToNumber("${subTotal}");
                            $.ajax({
                                type: "GET",
                                url: "${pageContext.request.contextPath}/user/carts/apply-voucher" + "?voucher=" + voucherCode + "&total=" + total,
                                // url: "http://localhost:8080/user/carts/apply-voucher/2?voucher=BBBB&total=12000000",
                                success: function (response) {
                                    // Xử lý phản hồi từ Controller (nếu có)
                                    console.log(response);
                                    document.getElementById("subTotal").innerHTML = response.subTotal;
                                    document.getElementById("discount").innerHTML = response.discount;
                                    document.getElementById("total").innerHTML = response.total;

                                    document.getElementById("subTotal").setAttribute("value", convertCurrencyToNumber(response.subTotal));
                                    document.getElementById("discount").setAttribute("value", convertCurrencyToNumber(response.discount));
                                    document.getElementById("total").setAttribute("value", convertCurrencyToNumber(response.total));
                                    isVoucherValid = true;
                                },
                                error: function (response) {
                                    // Xử lý lỗi (nếu có)
                                    console.log(response);
                                    document.getElementById("voucher_message").innerHTML = response.responseJSON.message;

                                    document.getElementById("discount").innerHTML = "0 VNĐ";
                                    document.getElementById("total").innerHTML = document.getElementById("subTotal").innerHTML;
                                    isVoucherValid = false;
                                }
                            });


                            document.getElementById("voucher_message").innerHTML = "";
                        }
                    }
                </script>

                <script>


                    function checkout() {
                        var listItem = document.querySelectorAll('tr[id^="item_"]');
                        var paymentRadios = document.getElementsByName("payment");

                        var listItemId = [];
                        listItem.forEach(function (item) {
                            listItemId.push(item.id.split('_')[1]);
                        })

                        var selectedValue = document.querySelector('input[name="payment"]:checked').value;
                        console.log(selectedValue);
                        var bankCode = "";

                        var addressId = document.getElementById("userAddress").getAttribute("value")
                        console.log("addressID: " + addressId);

                        var voucher = document.getElementById("voucher").value;
                        if (isVoucherValid == false){
                            voucher = "";
                        }
                        // Tạo đối tượng chứa dữ liệu cần gửi
                        var data = {
                            listItemId: listItemId,
                            paymentMethod: selectedValue,
                            addressId: addressId,
                            voucher: voucher,
                            isBuyNow: '${isBuyNow}',
                            isReCheckout: '${ not empty recheckout ? true : false  }',
                            orderId: '${ not empty recheckout ? orderId : -1  }'
                        };

                        if (selectedValue === "VISA") {
                            bankCode = "NCB";

                            var amount = document.getElementById("total").getAttribute("value");
                            var paymentData = {
                                amount: amount,
                                bankCode: bankCode
                            }
                            data.paymentRequestDTO = paymentData;

                        }

                        <c:if test="${isBuyNow == true}">

                            data.quantity = ${quantity};
                            data.productSizeId = ${productSizeId}
                        </c:if>
                        console.log(data)
                        // Gửi AJAX request

                        if (selectedValue === "VISA") {

                            console.log(JSON.stringify({ checkoutDTO: data, paymentRequestDTO: paymentData }))
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/orders/checkout-payment",
                                type: "POST",
                                contentType: "application/json",
                                data: JSON.stringify(data),
                                success: function (response) {
                                    // Xử lý phản hồi thành công
                                    console.log(response)
                                    console.log("Checkout successful");
                                    window.location.href = response.paymentReponse.url;
                                },
                                error: function (error) {
                                    // Xử lý phản hồi lỗi
                                    console.log("Checkout failed");
                                    alert("Đặt hàng không thành công. Vui lòng thử lại")
                                    console.log(error)
                                }
                            });
                        } else {
                            console.log(data)
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/orders/checkout",
                                type: "POST",
                                contentType: "application/json",
                                data: JSON.stringify(data),
                                success: function (response) {
                                    // Xử lý phản hồi thành công    
                                    console.log(response)
                                    console.log("Checkout successful");
                                    window.location.href = "${pageContext.request.contextPath}/user/orders/" + response.orderId;
                                },
                                error: function (error) {
                                    // Xử lý phản hồi lỗi
                                    console.log("Checkout failed");
                                    alert("Đặt hàng không thành công. Vui lòng thử lại")
                                    console.log(error)
                                }
                            });
                        }



                    }

                    function convertCurrencyToNumber(currencyString) {
                        // Xóa dấu phân cách hàng nghìn (.)
                        let numberString = currencyString.replace(/\./g, '');

                        // Xóa ký tự VNĐ và khoảng trắng
                        numberString = numberString.replace(/VNĐ/g, '').trim();

                        // Chuyển chuỗi thành số
                        let number = parseInt(numberString, 10);

                        return number;
                    }

                </script>


                <!-- Start Footer Area -->
                <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp" />
                <!-- End Footer Area -->

                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                    integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                    crossorigin="anonymous"></script>
                <!--gmaps Js-->
                <script
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
                <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
            </body>

            </html>