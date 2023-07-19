
function changeQuantity(cartId, itemId, index, action) {
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

    $.ajax({
        type: "GET",
        url: "/user/carts/changeQuantity/" + cartId + "?cartItemId="+ itemId + "&quantity=" + currentQuantity,
        success: function (response) {
            // Xử lý phản hồi từ Controller (nếu có)
            console.log(response);
            if (action === "increase") {
                quantityField.value = currentQuantity;

            } else if (action === "decrease") {

                console.log("QUANTIT " +currentQuantity - 1 );
                quantityField.value = currentQuantity;
            }
            document.getElementById("subTotal_" + itemId).innerHTML = response.subTotal;
            document.getElementById("total").innerHTML = "Tổng tiền: " + response.total;
            // subTotal(itemId, quantityField.value);
            // calculateTotal();
        },
        error: function (error) {
            // Xử lý lỗi (nếu có)
            console.log(error);
            var popover = document.getElementById("quantityPopup_" +itemId );
            popover.setAttribute('data-bs-trigger', 'manual');
            popover.setAttribute('data-content',"Sản phẩm đã hết hàng");
            var bsPopover = new bootstrap.Popover(popover);
            bsPopover.show();
            var listText = document.getElementsByClassName("popover");
            var arrayText = Array.from(listText); 

            arrayText.forEach(function (item){
                popover.addEventListener('blur',function(event){
                    item.classList.remove("show");
                })
            })
            // if (reps)
        }
    });
}



function removeProduct(cartId, itemId) {
    var itemList = document.querySelectorAll('tr[id^="item_"]');

    itemList.forEach(function (item) {
        var id = item.id;

        if (id.split('_')[1] == itemId) {

            $.ajax({
                type: "GET",
                url: "/user/carts/product-remove/"+ cartId   +"?cartItemId=" + itemId,
                success: function (response) {
                    // Xử lý phản hồi từ Controller (nếu có)
                    console.log(response);
                    item.parentNode.removeChild(item);
                    document.getElementById("total").innerHTML = "Tổng tiền: " + response.total;
                    var cart = document.getElementById('cartQuantity');
                    var cartQuantity = parseFloat(cart.innerHTML)
                    cart.innerHTML = cartQuantity-1;
                    // calculateTotal();
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi (nếu có)
                    console.log(error);
                }
            });


        }
    });


}



// function calculateTotal() {

//     var total = 0;
//     var subTotalList = document.querySelectorAll('h5[id^="subTotal_"]');

//     subTotalList.forEach(function (item) {

//         var value = parseFloat(item.innerHTML);

//         total += value;
//     });
//     console.log(total);
//     document.getElementById("total").innerHTML = "Tổng tiền: " + total;

// }



// function subTotal(itemId, quantity) {


//     var subTotal = document.getElementById("subTotal_" + itemId);
//     var price = document.getElementById("price_" + itemId).getAttribute("value");
//     console.log("SUBTOTAL: " + "subTotal_" + itemId + " --- " + document.getElementById("subTotal_" + itemId));
//     console.log("PRICE: " + price);
//     console.log("QTY: " + quantity);
//     document.getElementById("subTotal_" + itemId).innerHTML = parseFloat(price) * quantity;

// }



function checkout(cartId) {
    var selectedItems = [];

    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');

    if (checkboxes.length == 0) {
        alert("Vui lòng chọn ít nhất 1 sản phẩm");
        return;
    }

    var checkboxesArray = Array.from(checkboxes);
    if (checkboxesArray[0].id === "checkAllProducts"){
        checkboxesArray.shift();
    }
   
    checkboxesArray.forEach(function (checkbox) {
        var itemName = checkbox.value;

        selectedItems.push(itemName);
    });

    console.log("SELECTED ITEMS: ", selectedItems);
    console.log("/user/carts/checkout/" + cartId + "?item=" + selectedItems);

    window.location.href = "/user/carts/checkout/" + cartId + "?itemList=" + selectedItems;

    // $.ajax({
    //     type: "POST",
    //     url: "/user/carts/checkout/" + cartId,
    //     data: JSON.stringify(selectedItems),
    //     contentType: "application/json",
    // });
}
