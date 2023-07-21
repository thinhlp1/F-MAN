var app = angular.module("myApp", ['ngRoute']);
app.config(function ($httpProvider) {
    // Set the default Content-Type to application/json
    $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
})
app.run(["$rootScope", "$anchorScroll" , function ($rootScope, $anchorScroll) {
    $rootScope.$on("$locationChangeSuccess", function() {
        $anchorScroll();
    });
}]);