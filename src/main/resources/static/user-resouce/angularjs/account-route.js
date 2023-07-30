app.config(function($routeProvider){
    $routeProvider
    .when("/login",{
        templateUrl: "login",
    })
    .when("/register",{
        templateUrl: "register",
    })
    .when("/confirmOTP/:action/:email",{
        templateUrl: "confirmOTP",
    })
    .when("/forget/:action/:email?",{
        templateUrl: "forget",
    })
    .when("/change-password",{
        templateUrl: "change-password",
    })
    .otherwise({
        redirectTo: "/login"
    });
})