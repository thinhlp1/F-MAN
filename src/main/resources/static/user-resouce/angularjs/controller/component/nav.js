function cartQuantity() {
    let cartQuantity;
    let cartQuantityElm = document.getElementById("cartQuantity")
    const cartJSON = localStorage.getItem('cart');
    if (!cartJSON) {
        cartQuantity = 0;
    } else {
        let cart = JSON.parse(cartJSON);
        if (cart.listCartItem) {
            cartQuantity = cart.listCartItem.length;
        } else {
            cartQuantity = 0;

        }
    }
    cartQuantityElm.innerHTML = cartQuantity;

}