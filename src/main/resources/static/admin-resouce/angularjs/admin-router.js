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
      templateUrl: "/admin/orders/all",
    })
    .when("/order-detail/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/orders/details/" + $routeParams.id;
      },
    })
    .when("/order-approve", {
      templateUrl: "/admin/orders/approve",
    })

    // USER
    .when("/user", {
      templateUrl: "/admin/users/",
    })
    .when("/user-add", {
      templateUrl: "/admin/users/create",
    })
    .when("/user-update/:username", {
      templateUrl: ($routeParams) => {
        return "/admin/users/update-form/" + $routeParams.username;
      },
    })
    .when("/user-detail/:username", {
      templateUrl: ($routeParams) => {
        return "/admin/users/details/" + $routeParams.username;
      },
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
    .when("/payment-update/:id", {
      templateUrl: ($routeParams) => {
        return "/admin/payments/update-form/" + $routeParams.id;
      },
    })
    // ANALYSIS
    .when("/analysis-date", {
      templateUrl: "/admin/analysis/date",
    })
    .when("/analysis-revenue", {
      templateUrl: "/admin/analysis/revenue",
    })
    .when("/analysis-orders", {
      templateUrl: "/admin/analysis/orders",
    })
    .when("/analysis-sellProducts", {
      templateUrl: "/admin/analysis/sellProducts",
    });
});
