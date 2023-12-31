app.controller("AnalysisRevenueController", function ($http, $scope, $routeParams) {

    $scope.listData = [];
    $scope.listYear = [];
    var listData = [];
    var listDataFilt = [];
    var listDataSort = [];
    var isSortOrder = "default";
    var isFiltQuartar = "all";

    var yearSelected;

    var ctx;
    var isInitChart = false;
    var chart;


    $scope.loadData = function (year) {
        $http
            .get("/admin/analysis/revenue?year=" + year)
            .then((resp) => {
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


                    type: 'bar',
                    data: {
                        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
                        datasets: [{
                            label: 'Doanh thu ( VNĐ )',
                            data: $scope.listData,
                            borderWidth: 1
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
                yearSelected = year;
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


    $scope.exportExcel = function () {
        var createXLSLFormatObj = [];

        /* XLS Head Columns */
        var xlsHeader = ["Tháng", "Doanh thu"];


        let listData = [];
        let i = 1;
        $scope.listData.forEach(element => {
            listData.push({
                "Tháng" : "Tháng " + i,
                "Doanh thu" : element
            });
            i++;
        });

        /* XLS Rows Data */
        var xlsRows = listData;


        createXLSLFormatObj.push(xlsHeader);
        xlsRows.forEach(function (value) {
            var innerRowData = [];

            Object.keys(value).forEach(function (key) {
                innerRowData.push(value[key]);
            });

            createXLSLFormatObj.push(innerRowData);
        });


        /* File Name */
        var filename = "Thong ke doanh thu nam "+ yearSelected +".xlsx";

        /* Sheet Name */
        var ws_name = "Doanh thu nam " + yearSelected;

        var wb = XLSX.utils.book_new(),
            ws = XLSX.utils.aoa_to_sheet(createXLSLFormatObj);

        // Tạo đối tượng tô màu cho phần tiêu đề
        var headerStyle = {
            fill: {
                fgColor: { rgb: 'FFFF00' }, // Mã màu HEX cho màu vàng
            },
        };

        // Áp dụng phong cách tô màu cho phần tiêu đề (dòng 1)
        XLSX.utils.sheet_add_aoa(ws, [xlsHeader], { origin: 'A1' });
        xlsHeader.forEach(function (value, index) {
            var cellAddress = XLSX.utils.encode_cell({ r: 0, c: index });
            ws[cellAddress] = { ...ws[cellAddress], ...headerStyle };
        });

        /* Add worksheet to workbook */
        XLSX.utils.book_append_sheet(wb, ws, ws_name);

        

        /* Write workbook and Download */
        XLSX.writeFile(wb, filename);
    }

    $scope.loadData(2023);
});
