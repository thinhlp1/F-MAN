app.controller("ComponentController", function ($http, $scope, $routeParams) {
    $scope.loadTaskbar = function(){
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

    $scope.loadNav = function(){
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