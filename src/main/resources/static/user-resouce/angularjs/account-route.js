app.config(function ($routeProvider) {
    $routeProvider
        .when("/login", {
            templateUrl: "login",
        })
        .when("/register", {
            templateUrl: "register",
        })
        .when("/confirmOTP/:action/:email", {
            templateUrl: "confirmOTP",
        })
        .when("/forget/:action/:email?", {
            templateUrl: "forget",
        })
        .when("/change-password", {
            templateUrl: "change-password",
        })
        .when("/change-email", {
            templateUrl: "change-email",
        })
        .when("/sendConfirmOtpChangeEmail", {
            templateUrl: "confirmOtpEmail",
        })
        .otherwise({
            redirectTo: "/login"
        });
})