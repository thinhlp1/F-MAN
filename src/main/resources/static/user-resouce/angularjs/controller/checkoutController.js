app.controller('CheckoutController', function ($scope, $http, $location, $routeParams) {


    let checkoutResponse;
    let isBynow = false;


    $scope.loadReCheckout = function () {

    }

    $scope.loadCheckout = function () {
        let listId = $routeParams.listCartItem;
        if (listId === undefined) {
            isBynow = true;
        }


        const cartJSON = sessionStorage.getItem('cart');
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }

        let listCartItem = [];

        if (isBynow) {
            listCartItem = [
                {
                    "productId": $routeParams.productId,
                    "productSizeId": $routeParams.productSizeId,
                    "quantity": $routeParams.quantity,
                }
            ]
        } else {

            let listItemId = [];

            listItemId = listId.split(",");

            for (let i = 0; i < cart.listCartItem.length; i++) {
                if (listItemId.includes(cart.listCartItem[i].productSizeId + "")) {
                    listCartItem.push(cart.listCartItem[i])
                }
            }
        }

        console.log(listCartItem);


        userId = getCookie("userId");
        cart.userId = parseInt(userId);
        cart.listCartItem = listCartItem;

        let request = {
            method: 'POST',
            url: "/user/carts/checkout",
            data: JSON.stringify(cart)
        };

        $http(request).then(
            function (response) {
                checkoutResponse = response.data;
                $scope.listCartItem = response.data.listCartItems;
                $scope.listAddress = response.data.listAddress;
                $scope.listPaymentMethods = response.data.listPaymentMethods;
                $scope.selectedPaymentMethod = "CASH";

                $scope.address = $scope.listAddress.find(function (item) {
                    return item.isDefault === 1;
                });

                $scope.tempTotalStringVND = response.data.tempTotalStringVND;
                $scope.totalStringVND = response.data.totalStringVND;
                $scope.discountStringVND = response.data.discountStringVND;

                // console.log($scope.address);
            }
        ).catch(function (error) {
            console.log(error);
            if (error.status == 500) {
                Swal.fire({

                    icon: 'error',
                    title: 'Oops...',
                    text: 'Có lỗi xảy với giỏ hàng của bạn. Xin lỗi vị sự bất tiện này',
                })
            }

        });

    }

    $scope.load = function () {

        let path = $location.path();
        if (path.includes("re-checkout")) {
            console.log("re");

        } else {
            console.log("norrmal");
        }

    }

    $scope.selectPayment = function (selectPaymentId) {
        $scope.selectedPaymentMethod = selectPaymentId;

        if (selectPaymentId === "VISA") {
            document.getElementById("bank-select").style.display = "block";
        } else {
            document.getElementById("bank-select").style.display = "none";
        }


    }

    $scope.load();



    $scope.changeAddress = function (addressId) {

        $scope.address = $scope.listAddress.find(function (item) {
            return item.id === addressId;
        });

        console.log($scope.address);
        $('#addressModel').modal('hide');
    }


    var isVoucherValid;
    $scope.applyVoucher = function () {
        var voucherCode = $scope.voucher;
        console.log(voucherCode);
        if (voucherCode === "" || voucherCode === undefined) {
            $scope.voucher_message = "Vui lòng nhập voucher";
        } else {
            let total = checkoutResponse.total;
            let request = {
                method: 'GET',
                url: "/user/carts/apply-voucher" + "?voucher=" + voucherCode + "&total=" + total,
            };

            console.log(request);

            $http(request).then(
                function (response) {
                    console.log(response);

                    $scope.tempTotalStringVND = response.data.tempTotalStringVND;
                    $scope.totalStringVND = response.data.totalStringVND;
                    $scope.discountStringVND = response.data.discountStringVND;
                    $scope.voucher_message = "";
                    isVoucherValid = true;
                }
            ).catch(function (response) {
                console.log(response);
                $scope.voucher_message = response.data.message;

                $scope.discountStringVND = "0 VNĐ";
                $scope.totalStringVND = $scope.tempTotalStringVND
                isVoucherValid = false;

            });

        }
    }





    $scope.checkout = function () {
        let voucherCode = "";

        if (isVoucherValid) {
            voucherCode = $scope.voucher;
        }

        let addressId = $scope.address.id;
        let paymentMethodId = $scope.selectedPaymentMethod;
        let listCheckoutItem = [];

        let bankCode = paymentMethodId === "VISA" ? "NCB" : "";
        let userId = getCookie("userId");

        if (isBynow) {
            listCheckoutItem = [
                {
                    "productId": $routeParams.productId,
                    "productSizeId": $routeParams.productSizeId,
                    "quantity": $routeParams.quantity,
                }
            ]
        } else {
            let listId = $routeParams.listCartItem;
            let listItemId = [];
            listItemId = listId.split(",");

            for (let i = 0; i < $scope.listCartItem.length; i++) {
                let item = $scope.listCartItem[i];
                if (listItemId.includes(item.productSize.id + "")) {
                    listCheckoutItem.push({
                        productId: item.product.id,
                        productSizeId: item.productSize.id,
                        quantity: item.quantity
                    })
                }
            }
        }




        let data = {
            listCheckoutItem: listCheckoutItem,
            voucherCode: voucherCode,
            addressId: addressId,
            bankCode: bankCode,
            paymentMethodId, paymentMethodId,
            userId: 3
        }

        if (bankCode === "") {
            let request = {
                method: 'POST',
                url: "/user/orders/checkout",
                data: data
            };

            $http(request).then(
                function (response) {
                    console.log(response);
                    window.location.href = "/user/orders/" + response.data.order.id;

                }
            ).catch(function (response) {
                console.log(response);
                if (response.status == 500) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Không thể thanh toán đơn hàng này',
                        text: response.data.message,
                    })
                }

            });
        } else {

            let request = {
                method: 'POST',
                url: "/user/orders/checkout-payment",
                data: data
            };

            $http(request).then(
                function (response) {
                    console.log(response);
                    window.location.href = response.data.paymentReponse.url;


                }
            ).catch(function (response) {
                console.log(response);
                if (response.status == 500) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Không thể thanh toán đơn hàng này',
                        text: response.data.message,
                    })
                }

            });
        }

    }

});

