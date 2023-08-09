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

        var quantity = parseInt(quantityInput.value);

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
            "quantity": parseInt(quantity)
        }

        console.log(item);

        const cartJSON = localStorage.getItem('cart');
        let cart;
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }

        const existingItem = cart.listCartItem.find((cartItem) => cartItem.productSizeId === item.productSizeId);
        var cartItemIndex = cart.listCartItem.findIndex((cartItem) => cartItem.productSizeId === item.productSizeId);

        if (existingItem) {
            console.log(existingItem.quantity + quantity >= maxQuantity);
            // Nếu đã tồn tại mục với cùng id, tăng số lượng lên 1
            if (existingItem.quantity >= maxQuantity) {
                Swal.fire({
                    icon: 'info',
                    title: 'Xin lỗi bạn',
                    text: 'Giỏ hàng của bạn đã tối đa sản phẩm này',
                })
                return;
            } else if (existingItem.quantity + quantity >= maxQuantity) {
                console.log(maxQuantity - existingItem.quantity);
                console.log('Đã thêm ' + (maxQuantity - existingItem.quantity) + " sản phẩm nữa vào giỏ");

                cart.listCartItem[cartItemIndex].quantity = parseInt(cart.listCartItem[cartItemIndex].quantity) + (maxQuantity - existingItem.quantity);
                Swal.fire({
                    icon: 'info',
                    title: 'Nhắc nhỏ',
                    text: "Đã thêm " + (maxQuantity - existingItem.quantity) + " sản phẩm nữa vào giỏ",
                })

            }
            else {
                cart.listCartItem[cartItemIndex].quantity = parseInt(cart.listCartItem[cartItemIndex].quantity) + quantity;
            }
        }
        else {
            // Nếu chưa tồn tại mục với cùng id, thêm mục mới vào danh sách listItem
            cart.listCartItem.push(item);
        }
        const updatedCartJSON = JSON.stringify(cart);

        localStorage.setItem('cart', updatedCartJSON);
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

        const cartJSON = localStorage.getItem('cart');
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
            existingItem.quantity = parseInt(existingItem.quantity) + 1;
        } else {
            // Nếu chưa tồn tại mục với cùng id, thêm mục mới vào danh sách listItem
            cart.listCartItem.push(item);
        }
        const updatedCartJSON = JSON.stringify(cart);

        localStorage.setItem('cart', updatedCartJSON);
        showCartQuantity();

        window.location = "/user/carts/#!/checkout/" + sizeId;


    }

});