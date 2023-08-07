app.config(function ($routeProvider) {
    $routeProvider
        // // DASHBOARD
        .when("/", {
            templateUrl: "/admin/dashboard",
        })
        .when("/index", {
            templateUrl: "/admin/dashboard",
        })


    // PRODUCT
    .when("/product", {
      templateUrl: "/admin/products/",
    })
    .when("/product-add", {
      templateUrl: "/admin/products/create",
    })
    .when("/product-update/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/products/update-form/" + $routeParams.id;
      },
    })
    .when("/product-detail/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/products/details/" + $routeParams.id;
      },
    })

    // BRAND
    .when("/brand", {
      templateUrl: "/admin/brands/",
    })
    .when("/brand-add", {
      templateUrl: "/admin/brands/create",
    })
    .when("/brand-update/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/brands/update-form/" + $routeParams.id;
      },
    })
    .when("/brand-detail/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/brands/details/" + $routeParams.id;
      },
    })
    


        // CATEGORY
        .when("/category", {
            templateUrl: "/admin/categorys/",
            controller: "CategoryController",
        })
        .when("/category-add", {
            templateUrl: "/admin/categorys/create",
            controller: "CategoryController",
        })
        .when("/category-update", {
            templateUrl: "/admin/categorys/update-form",
            controller: "CategoryController",
        })

    // SIZE
    .when("/size", {
      templateUrl: "/admin/sizes/",
    })
    .when("/size-add", {
      templateUrl: "/admin/sizes/create",
    })
    .when("/size-update/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/sizes/update-form/" + $routeParams.id;
      },
    })

    // DISCOUNT
    .when("/discount", {
      templateUrl: "/admin/discounts/",
      controller: "DiscountController"
    })
    .when("/discount-add", {
      templateUrl: "/admin/discounts/create",
      controller: "DiscountController"
    })
    .when("/discount-update", {
      templateUrl: "/admin/discounts/update-form",
      controller: "DiscountController"
    })

        // ORDER
        .when("/order", {
            templateUrl: "/admin/orders/",
        })
        .when("/order-detail/:approve?/:id", {
            templateUrl: ($routeParams) => {
                return "/admin/orders/view/details/" + $routeParams.id;
            },
        })
        .when("/order-approve", {
            templateUrl: "/admin/orders/approve",
        })

        // USER
        .when("/user", {
            templateUrl: "/admin/users/",

            controller: "userController"
        })
        .when("/user-add", {
            templateUrl: "/admin/users/create",
            controller: "userController"
        })
        .when("/user-update", {
            templateUrl: "/admin/users/update-form" ,
            controller: "userController"
        })
       


        // PAYMENT
        .when("/payment", {
            templateUrl: "/admin/payments/",
            controller: "PaymentMethodController"
        })
        .when("/payment-add", {
            templateUrl: "/admin/payments/create",
            controller: "PaymentMethodController"
        })
        .when("/payment-update", {
            templateUrl: "/admin/payments/update-form",
            controller: "PaymentMethodController"
        })

        // ANALYSIS
        .when("/analysis-date", {
            templateUrl: "/admin/analysis/date",
        })
        .when("/analysis-revenue", {
            templateUrl: "/admin/analysis/view/revenue",
        })
        .when("/analysis-orders", {
            templateUrl: "/admin/analysis/view/order",
        })
        .when("/analysis-sellProducts", {
            templateUrl: "/admin/analysis/view/sellProducts",
        });

});
