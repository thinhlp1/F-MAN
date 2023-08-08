app.controller('ProductDetailsController', function ($scope, $http, $location) {
    var quantityInput = document.getElementById("qty");
    var quantitySize = -1;
    var maxQuantity = 0;
    var sizeId = '';


    $scope.updateSize = function (size, id, max) {
        maxQuantity = max;
        quantitySize = size;
        sizeId = id;
        quantityInput.value = 1;
    }

    $scope.increaseQuantity = function () {

        if (quantitySize == -1) {
            Swal.fire({
                icon: 'info',
                title: 'Vui lòng chọn size ',
            })
            return;
        }

        var currentValue = parseInt(quantityInput.value);
        if (currentValue < maxQuantity) {

            quantityInput.value = currentValue + 1;
        }
    }

    $scope.decreaseQuantity = function () {
        if (quantitySize == -1) {
            Swal.fire({
                icon: 'info',
                title: 'Vui lòng chọn size ',
            })
            return;
        }
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    }

    $scope.addToCart = function () {

        if (getCookie("token") === undefined || getCookie("token") === "") {
            Swal.fire({
                title: 'Chưa đăng nhập',
                text: "Vui lòng đăng nhập để thêm sản phẩm",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Đăng nhập'
            }).then((result) => {
                window.location.href = "/auth/account#!login";

            });
            return;
        }

        var quantity = quantityInput.value;

        if (sizeId === '' || sizeId == -1) {
            Swal.fire({
                icon: 'info',
                title: 'Vui lòng chọn size ',
            })
        }
        let productId = document.getElementById("productId").getAttribute("value");
        var item = {
            "productId": productId,
            "productSizeId": sizeId,
            "quantity": quantity
        }

        console.log(item);

        const cartJSON = sessionStorage.getItem('cart');
        let cart;
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }

        const existingItem = cart.listCartItem.find((cartItem) => cartItem.productSizeId === item.productSizeId);

        if (existingItem) {
            // Nếu đã tồn tại mục với cùng id, tăng số lượng lên 1
            existingItem.quantity += 1;
        } else {
            // Nếu chưa tồn tại mục với cùng id, thêm mục mới vào danh sách listItem
            cart.listCartItem.push(item);
        }
        const updatedCartJSON = JSON.stringify(cart);

        sessionStorage.setItem('cart', updatedCartJSON);
        Swal.fire({
            icon: 'success',
            title: 'OK',
            text: 'Đã thêm vào giỏ hàng',
        })
        showCartQuantity();

    }


    $scope.buyNow = function () {

        if (getCookie("token") === undefined || getCookie("token") === "") {
            Swal.fire({
                title: 'Chưa đăng nhập',
                text: "Vui lòng đăng nhập để thêm sản phẩm",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Đăng nhập'
            }).then((result) => {
                window.location.href = "/auth/account#!login";

            });
            return;
        }

        var quantity = quantityInput.value;

        if (sizeId === '' || sizeId == -1) {
            Swal.fire({
                icon: 'info',
                title: 'Vui lòng chọn size ',
            })
            return;
        }
        let productId = document.getElementById("productId").getAttribute("value");
        var item = {
            "productId": productId,
            "productSizeId": sizeId,
            "quantity": quantity
        }

        console.log(item);

        const cartJSON = sessionStorage.getItem('cart');
        let cart;
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }

        const existingItem = cart.listCartItem.find((cartItem) => cartItem.productSizeId === item.productSizeId);

        if (existingItem) {
            // Nếu đã tồn tại mục với cùng id, tăng số lượng lên 1
            existingItem.quantity += 1;
        } else {
            // Nếu chưa tồn tại mục với cùng id, thêm mục mới vào danh sách listItem
            cart.listCartItem.push(item);
        }
        const updatedCartJSON = JSON.stringify(cart);

        sessionStorage.setItem('cart', updatedCartJSON);
        showCartQuantity();

        window.location = "/user/carts/#!/checkout/" + sizeId;


    }

});