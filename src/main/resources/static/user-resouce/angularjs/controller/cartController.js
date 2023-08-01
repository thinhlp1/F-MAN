// Define your AngularJS module and controller
app.controller('CartController', function ($scope, $http, $location) {
    // Your other controller logic here...

    $scope.listCartItem = []; // Initialize listCartItem with data from the server or any other source
    $scope.cart;
    
    function initCheckBox() {

        var popoverList = document.querySelectorAll("button[id^='quantityPopup_']");
        console.log(popoverList);
        // alert("có " + popoverList.length);
        popoverList.forEach(function (popover) {
            // console.log(document.getElementById(popover.id))
            $('#' + popover.id).click(function (event) {
                event.preventDefault();
            });
        });

        // Lấy tham chiếu đến checkbox chọn tất cả
        var checkAll = document.getElementById("checkAllProducts");

        // Lấy tất cả các checkbox khác
        var checkboxes = document.querySelectorAll("input[id^='checkProduct_']");

        // Xử lý sự kiện khi checkbox chọn tất cả thay đổi
        checkAll.addEventListener("change", function () {
            var isChecked = checkAll.checked;
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = isChecked;
            });
        });

        // Xử lý sự kiện khi các checkbox khác thay đổi
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener("change", function () {
                var allChecked = checkboxes.length === document.querySelectorAll("input[id^='checkProduct_']:checked").length;
                checkAll.checked = allChecked;
            });
        });


    }

    function showPopover(id){
        var popover = document.getElementById("quantityPopup_" + id);
        popover.setAttribute('data-bs-trigger', 'manual');
        popover.setAttribute('data-content', "Sản phẩm đã hết hàng");
        var bsPopover = new bootstrap.Popover(popover);
        console.log(bsPopover)
        bsPopover.show();
        setTimeout(function(){
            bsPopover.hide()
        },2000)
    }

    function addItemToCart(item) {
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
    }

    $scope.decreaseItemQuantityById = function (id) {
        const cartJSON = sessionStorage.getItem('cart');
        if (!cartJSON) {
            return;
        }
        const cart = JSON.parse(cartJSON);

        const cartItemIndex = cart.listCartItem.findIndex((item) => item.productSizeId === id);

        if (cartItemIndex !== -1) {
            if (cart.listCartItem[cartItemIndex].quantity > 1) {
                cart.listCartItem[cartItemIndex].quantity -= 1;
                const updatedCartJSON = JSON.stringify(cart);
                sessionStorage.setItem('cart', updatedCartJSON);
                $scope.loadCart();
            }
        }
    }

    $scope.increaseItemQuantityById = function (id) {

        const cartJSON = sessionStorage.getItem('cart');
        if (!cartJSON) {
            return;
        }
        const cart = JSON.parse(cartJSON);
        const cartItemIndex = cart.listCartItem.findIndex((item) => item.productSizeId === id);
        if (cartItemIndex !== -1) {
            let carItem = cart.listCartItem[cartItemIndex];
            if (carItem.quantity < 99) {
                let request = {
                    method: 'GET',
                    url: "/user/carts/check-in-stock?productSizeId=" + carItem.productSizeId + "&quantity=" + (carItem.quantity + 1),
                    data: JSON.stringify(cart)
                };
                $http(request).then(
                    function (response) {
                        cart.listCartItem[cartItemIndex].quantity += 1;

                        const updatedCartJSON = JSON.stringify(cart);
                        sessionStorage.setItem('cart', updatedCartJSON);
                        $scope.loadCart();
                    }
                ).catch(function (error) {
                    console.log(error);
                    if (error.status == 500) {
                        showPopover(id);
                    }
                });
            }
        }
    }


    $scope.removeItem = function (id) {
        const cartJSON = sessionStorage.getItem('cart');
        if (!cartJSON) {
            return;
        }
        const cart = JSON.parse(cartJSON);

        const cartItemIndex = cart.listCartItem.findIndex((item) => item.productSizeId === id);

        if (cartItemIndex !== -1) {
            cart.listCartItem.splice(cartItemIndex, 1);
            const updatedCartJSON = JSON.stringify(cart);
            sessionStorage.setItem('cart', updatedCartJSON);
            $scope.loadCart();

        }
    }


    // Đối tượng mục mới bạn muốn thêm vào danh sách (ví dụ)
    // const newItem = { productId: "AQ4134-400", productSizeId: 14, quantity: 3 };
    // const newItem2 = { productId: "DV4022-004", productSizeId: 3, quantity: 3 };
    // addItemToCart(newItem2);
    // addItemToCart(newItem);
    // decreaseItemQuantityById(4);
    // increaseItemQuantityById(4);


    $scope.checkout = function (){
        var selectedItems = [];

        var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    
        if (checkboxes.length == 0) {
            Swal.fire({
                icon: 'info',
                title: 'Bạn chưa chọn sản phẩm nè',
            })
            return;
        }
    
        var checkboxesArray = Array.from(checkboxes);
        if (checkboxesArray[0].id === "checkAllProducts"){
            checkboxesArray.shift();
        }

        const cartJSON = sessionStorage.getItem('cart');
        let cart;
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }
       
        checkboxesArray.forEach(function (checkbox) {
            let itemId = checkbox.value;
            selectedItems.push(itemId);
        });

        console.log("checkout?listCartItem=" + selectedItems);
        $location.path("checkout/" + selectedItems);
      
    }

    $scope.loadCart = function () {
        const cartJSON = sessionStorage.getItem('cart');
        let cart;
        let userId;
        if (cartJSON) {
            cart = JSON.parse(cartJSON);
        } else {
            cart = {
                listCartItem: [],
            };
        }

        userId = getCookie("userId");
        cart.userId = parseInt(userId);


        let request = {
            method: 'POST',
            url: "/user/carts/get-cart-items/" + userId,
            data: JSON.stringify(cart)
        };

        $http(request).then(
            function (response) {
                $scope.listCartItem = response.data.listCartItems;
                $scope.totalPrice = response.data.totalStringVND;
                setTimeout(function name(params) {
                    initCheckBox();
                },100)
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
    $scope.loadCart();
});
