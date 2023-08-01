app.config(function($routeProvider){
    $routeProvider
    .when("/cart",{
        templateUrl: "cart",
    })
    .when("/checkout/:listCartItem",{
        templateUrl: "checkout",
    })
    .when("/checkout-bynow/:productId/:productSizeId/:quantity",{
        templateUrl: "checkout",
    })
    .when("/re-checkout/:orderId",{
        templateUrl: "checkout",
    })
    .otherwise({
        redirectTo: "/cart"
    });
})