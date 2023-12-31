app.controller("AnalysisOrderController", function ($http, $scope, $routeParams) {

    $scope.listData = [];
    $scope.listYear = [];
    var listData = [];
    var listDataFilt = [];
    var listDataSort = [];
    var isSortOrder = "default";
    var isFiltQuartar = "all";

    var ctx;
    var isInitChart = false;
    var chart;


    $scope.loadData = function (year) {
        $http
            .get("/admin/analysis/orders?year=" + year)
            .then((resp) => {
                console.log(resp);
                //Lấy dữ liệu từ server và gán vào DataService
                //Gán dữ liệu vào $scope.data trên biến toàn cục
                $scope.listData = resp.data.listdataRevenueByMonths;
                $scope.listYear = resp.data.listYear;

                listData = $scope.listData;

                listDataFilt = listData.slice();
                listDataSort = listData.slice();

                ctx = document.getElementById('myChart');


                if (isInitChart) {
                    chart.destroy();
                }

                chart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
                        datasets: [{
                            label: 'Đơn hàng',
                            data: listData,
                            borderWidth: 1,
                            borderColor: '#f54e42',
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
                isInitChart = true;
                document.getElementById("btn-renvueYear").innerHTML = year;
                $scope.setValuesortOrder("default", "Sắp xếp")
                $scope.filtDataQuarter("ALL", "Cả năm")

                // chart.destroy();

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

    $scope.setValuesortOrder = function (order, label) {
        document.getElementById("btn-sortOrder").innerHTML = label;

        isSortOrder = order; // Giá trị sắp xếp hiện tại (asc: tăng dần, desc: giảm dần)
        var sortedData = [];
        var listNeedSort = [];

        if (isFiltQuartar === "all") {
            listNeedSort = listDataFilt;
        } else {
            listDataSort = listData;
        }

        if (isSortOrder !== "default") {
            sortedData = listDataFilt;
            sortedData.sort((a, b) => {
                if (order === 'asc') {
                    return a - b; // Sắp xếp tăng dần
                } else {
                    return b - a; // Sắp xếp giảm dần
                }
            });
        } else {
            sortedData = listData;
        }


        chart.data.datasets[0].data = sortedData; // Cập nhật dữ liệu sắp xếp trong biểu đồ
        chart.update(); // Cập nhật biểu đồ
    }

    $scope.filtDataQuarter = function (quarter, label) {
        let startIndex, endIndex;
        document.getElementById("btn-filtQuarter").innerHTML = label;
        if (quarter === 'quy1') {
            startIndex = 0;
            endIndex = 3;
            chart.data.labels = ["Tháng 1", "Tháng 2", "Tháng 3"];
        } else if (quarter === 'quy2') {
            startIndex = 3;
            endIndex = 6;
            chart.data.labels = ["Tháng 4", "Tháng 5", "Tháng 6"];
        } else if (quarter === 'quy3') {
            startIndex = 6;
            endIndex = 9;
            chart.data.labels = ["Tháng 7", "Tháng 8", "Tháng 9"];
        } else if (quarter === 'quy4') {
            startIndex = 9;
            endIndex = 12;
            chart.data.labels = ["Tháng 10", "Tháng 11", "Tháng 12"];
        } else if (quarter === "all") {
            startIndex = 0;
            endIndex = 12;
            chart.data.labels = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
        }

        const quarterData = listData.slice(startIndex, endIndex);
        // listDataFilt = quarterData;

        chart.data.datasets[0].data = quarterData;
        chart.update();
    }

    $scope.loadData(2023);
});
