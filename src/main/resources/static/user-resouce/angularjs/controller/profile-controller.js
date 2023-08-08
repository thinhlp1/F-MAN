app.controller("ProfileController", function ($scope, $http, $location) {
    $scope.logout = function () {
        let request = {
            method: 'GET',
            url: "/auth/logout"
        };
        $http(request).then(
            function (response) {
                console.log(response.data);
                sessionStorage.clear();
                window.location = "/auth/account"

            }
        ).catch(function (response) { 
            alert("Đăng xuất ko thành công rồi ní")
        });
    }
});