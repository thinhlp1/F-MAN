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
            controller: "productController",


        })
        .when("/product-add", {
            templateUrl: "/admin/products/create",
            controller: "productController",
    })
    .when("/product-update", {
      templateUrl: "/admin/products/update-form",
      controller: "productController",
    })
    .when("/product-detail/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/products/detail-form/" + $routeParams.id;
    },
      controller: "productController",
    })


        // BRAND
        .when("/brand", {
            templateUrl: "/admin/brands/",
            controller: "brandController",

        })
        .when("/brand-add", {
            templateUrl: "/admin/brands/create",
            controller: "brandController",

        })
        .when("/brand-update", {
            templateUrl: "/admin/brands/update-form",
            controller: "brandController"

        })
        .when("/brand-detail/:id", {
            templateUrl: ($routeParams) => {
                return "/admin/brands/details/" + $routeParams.id;
            },
            controller: "brandController",

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
            controller: "SizeController",

        })
        .when("/size-add", {
            templateUrl: "/admin/sizes/create",
            controller: "SizeController",

        })
        .when("/size-update", {
            templateUrl: "/admin/sizes/update-form",
            controller: "SizeController",

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
            controller: "OrderController",

        })
        .when("/order-detail/:approve?/:id", {
            templateUrl: ($routeParams) => {
                return "/admin/orders/view/details/" + $routeParams.id;
            },
            controller: "OrderController",

        })
        .when("/order-approve", {
            templateUrl: "/admin/orders/approve",
            controller: "OrderController",

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
            templateUrl: "/admin/users/update-form",
            controller: "userController"
        })
        .when("/user-detail/:id", {
          templateUrl: ($routeParams) => {
            return "/admin/users/detail-form/" + $routeParams.id;
        },
          controller: "userController",
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

        .when("/analysis-revenue", {
            templateUrl: "/admin/analysis/view/revenue",
            // controller: "AnalysisRevenueController",

        })
        .when("/analysis-orders", {
            templateUrl: "/admin/analysis/view/order",
            // controller: "AnalysisOrderController",

        })
        .when("/analysis-sellProducts", {
            templateUrl: "/admin/analysis/view/sellProducts",
            // controller: "AnalysisProductController",

        });

});
