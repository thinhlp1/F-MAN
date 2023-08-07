app.controller("DashboardController", function ($http, $scope) {

    $scope.ordersData ;
    $scope.revenueData ;
    $scope.soldProductData;
    $scope.listTopSellProduct = [];

    $scope.loadData = function () {
        $http
            .get("/admin/dashboard-data")
            .then((resp) => {
                console.log(resp);
                $scope.ordersData = resp.data.listOrders ;
                $scope.revenueData = resp.data.listRevenue ;
                $scope.soldProductData= resp.data.listSoldProducts ;
                $scope.listTopSellProduct = resp.data.listTopProduct ;
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
    $scope.loadData();
});