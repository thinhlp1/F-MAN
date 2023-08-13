app.controller("ComponentController", function ($http, $scope,$rootScope, $location, $routeParams) {
    $scope.loadTaskbar = function () {
        $http
            .get("/admin/componet-data")
            .then((resp) => {
                $scope.orderApproveQuantity = resp.data.orderApprove
            })
            .catch((err) => {
                notification(
                    "ERROR " + err.status + ": Lỗi tải dữ liệu",
                    3000,
                    "right",
                    "top",
                    "error",
                );
            });
    }

    // Lắng nghe sự kiện thay đổi route
    $rootScope.$on('$routeChangeSuccess', function() {
        // Lấy phần sau cùng của URI
        var uri = $location.url().split('/').pop();

        // Gán giá trị cho biến trong $scope để sử dụng trong template
        $scope.pageTitle = uri.toUpperCase();
        // console.log( $scope.pageTitle)
    });

    $scope.loadNav = function () {
        $http
            .get("/admin/componet-data")
            .then((resp) => {
                $scope.username = resp.data.username
                $scope.avatar = resp.data.image

            })
            .catch((err) => {
                console.log(err);
                notification(
                    "ERROR " + err.status + ": Lỗi tải dữ liệu",
                    3000,
                    "right",
                    "top",
                    "error",
                );
            });
    }
});