app.controller("AnalysisProductController", function ($http, $scope, $routeParams) {

    $scope.listData = [];
    $scope.listYear = [];
    var listData = [];
    var sortedData = [];

    var listDataSort = [];
    var isSortOrder = "default";
    var isFiltQuartar = "all";

    var ctx;
    var isInitChart = false;
    var chart;


    $scope.loadData = function (year) {
        $http
            .get("/admin/analysis/sellProducts?year=" + year)
            .then((resp) => {
                console.log(resp)
                //Lấy dữ liệu từ server và gán vào DataService
                //Gán dữ liệu vào $scope.data trên biến toàn cục
                let listSellProduct = resp.data.listSellProduct;
                $scope.listYear = resp.data.listYear;

                listSellProduct.forEach(element => {
                    listData.push({ label: element.product.name, value: element.quantity });
                });
                sortedData = listData.slice();

                ctx = document.getElementById('myChart');

                if (isInitChart) {
                    chart.destroy();
                }

                const backgroundColors = [
                    '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF',
                    '#FF9F40', '#8D4CDB', '#FF6384', '#36A2EB', '#FFCE56'
                ];

                // Tạo mảng chứa các nhãn cho từng hàng
                const labels = listData.map(item => item.label);

                // Tạo mảng chứa số lượng sản phẩm bán được
                const values = listData.map(item => item.value);

                console.log(labels);
                console.log(values);

                chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Số lượng sản phẩm bán được',
                            data: values,
                            backgroundColor: backgroundColors
                        }]
                    },
                    options: {
                        indexAxis: 'y',
                        scales: {
                            x: {
                                beginAtZero: true,

                            },
                            y: {
                                position: 'right'
                            }
                        }
                    }
                });

                isInitChart = true;
                document.getElementById("btn-renvueYear").innerHTML = year;
                // $scope.setValuesortOrder("default", "Sắp xếp")
                // $scope.filtDataQuarter("ALL", "Cả năm")

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
        sortOrder = order; // Cập nhật giá trị sắp xếp hiện tại
        sortedData.sort((a, b) => {
            if (order === 'asc') {
                return a.value - b.value; // Sắp xếp tăng dần
            } else {
                return b.value - a.value; // Sắp xếp giảm dần
            }
        });
        const labels = sortedData.map(item => item.label);

        const values = sortedData.map(item => item.value);

        chart.data.datasets[0].data = values; // Cập nhật dữ liệu sắp xếp trong biểu đồ
        chart.data.labels = labels;
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
