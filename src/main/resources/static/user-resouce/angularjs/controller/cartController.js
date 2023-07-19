// Define your AngularJS module and controller
app.controller('CartController', function ($scope, $http) {
    // Your other controller logic here...

    $scope.listCartItem = []; // Initialize listCartItem with data from the server or any other source

    // Your other functions and logic for the controller here

    $scope.toggleAllProducts = function () {
        angular.forEach($scope.listCartItem, function (item) {
            item.checked = $scope.allChecked;
        });
    };

    $scope.updateSubTotal = function (item) {
        item.subTotal = item.product.price * item.quantity;
        // You can format the subtotal to a string with the desired format here
        item.subTotalStringVND = item.subTotal + ' VND';
        $scope.calculateTotalPrice();
    };

    $scope.calculateTotalPrice = function () {
        $scope.cart.totalPrice = 0;
        angular.forEach($scope.listCartItem, function (item) {
            if (item.checked) {
                $scope.cart.totalPrice += item.subTotal;
            }
        });
        // You can format the total price to a string with the desired format here
        $scope.cart.totalPriceStringVND = $scope.cart.totalPrice + ' VND';
    };

    $scope.changeQuantity = function (cartId, itemId, index, action) {
        var quantityField = document.getElementById("qty_" + index);
        var currentQuantity = parseInt(quantityField.value);

        if (action === "increase") {
            if (quantityField.value >= 99) {
                return;
            }
            currentQuantity++;
        } else if (action === "decrease") {
            if (quantityField.value <= 1) {
                return;
            }
            currentQuantity--;
        }

        // Your AJAX request using $http instead of $.ajax
        $http({
            method: 'GET',
            url: "/user/carts/changeQuantity/" + cartId + "?cartItemId=" + itemId + "&quantity=" + currentQuantity
        }).then(function successCallback(response) {
            // Xử lý phản hồi từ Controller (nếu có)
            console.log(response.data);
            if (action === "increase") {
                quantityField.value = currentQuantity;
            } else if (action === "decrease") {
                quantityField.value = currentQuantity;
            }
            document.getElementById("subTotal_" + itemId).innerHTML = response.data.subTotal;
            document.getElementById("total").innerHTML = "Tổng tiền: " + response.data.total;
        }, function errorCallback(error) {
            // Xử lý lỗi (nếu có)
            console.log(error);
            var popover = document.getElementById("quantityPopup_" + itemId);
            popover.setAttribute('data-bs-trigger', 'manual');
            popover.setAttribute('data-content', "Sản phẩm đã hết hàng");
            var bsPopover = new bootstrap.Popover(popover);
            bsPopover.show();
            var listText = document.getElementsByClassName("popover");
            var arrayText = Array.from(listText);

            arrayText.forEach(function (item) {
                popover.addEventListener('blur', function (event) {
                    item.classList.remove("show");
                });
            });
        });
    };

    $scope.removeProduct = function (cartId, itemId) {
        var itemList = document.querySelectorAll('tr[id^="item_"]');

        itemList.forEach(function (item) {
            var id = item.id;

            if (id.split('_')[1] == itemId) {
                $http({
                    method: 'GET',
                    url: "/user/carts/product-remove/" + cartId + "?cartItemId=" + itemId
                }).then(function successCallback(response) {
                    // Xử lý phản hồi từ Controller (nếu có)
                    console.log(response.data);
                    item.parentNode.removeChild(item);
                    document.getElementById("total").innerHTML = "Tổng tiền: " + response.data.total;
                    var cart = document.getElementById('cartQuantity');
                    var cartQuantity = parseFloat(cart.innerHTML);
                    cart.innerHTML = cartQuantity - 1;
                }, function errorCallback(error) {
                    // Xử lý lỗi (nếu có)
                    console.log(error);
                });
            }
        });
    };

    $scope.checkout = function (cartId) {
        var selectedItems = [];

        var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');

        if (checkboxes.length == 0) {
            alert("Vui lòng chọn ít nhất 1 sản phẩm");
            return;
        }

        var checkboxesArray = Array.from(checkboxes);
        if (checkboxesArray[0].id === "checkAllProducts") {
            checkboxesArray.shift();
        }

        checkboxesArray.forEach(function (checkbox) {
            var itemName = checkbox.value;
            selectedItems.push(itemName);
        });

        console.log("SELECTED ITEMS: ", selectedItems);
        console.log("/user/carts/checkout/" + cartId + "?itemList=" + selectedItems);

        // You can use AngularJS's $http service to make the AJAX request instead of using window.location.href
        $http({
            method: 'GET',
            url: "/user/carts/checkout/" + cartId + "?itemList=" + selectedItems
        }).then(function successCallback(response) {
            // Handle the response from the server (if needed)
            console.log(response.data);
            // Handle the redirection to the checkout page (if needed)
            // window.location.href = "/user/carts/checkout/" + cartId + "?itemList=" + selectedItems;
        }, function errorCallback(error) {
            // Handle errors (if needed)
            console.log(error);
        });
    };

    // Other functions and logic for the controller...

});
