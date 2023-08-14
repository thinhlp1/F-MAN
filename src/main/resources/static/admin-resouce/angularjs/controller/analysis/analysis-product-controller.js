app.controller("AnalysisProductController", function ($http, $scope, $routeParams) {

    $scope.listData = [];
    $scope.listYear = [];
    var listData = [];
    var sortedData = [];

    var listDataSort = [];
    var isSortOrder = "default";
    var isFiltQuartar = "all";

    var yearSelected;


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
                $scope.listData =  resp.data.listSellProduct;
                $scope.listYear = resp.data.listYear;

                console.log(listSellProduct);

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
                $scope.setValuesortOrder("default", "Sắp xếp")
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

    $scope.exportExcel = function () {
        var createXLSLFormatObj = [];

        /* XLS Head Columns */
        var xlsHeader = ["Thứ tự", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm","Số lượng bán"];


        let listData = [];
        let i = 1;
        $scope.listData.forEach(element => {
            listData.push({
                "Thứ tự" :  i,
                "Mã sản phẩm" : element.product.id,
                "Tên sản phẩm" : element.product.name,
                "Loại sản phẩm" : element.product.productTypeId,
                "Số lượng bán" : element.quantity
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
        var filename = "Thong ke san pham ban chay nam "+ yearSelected +".xlsx";

        /* Sheet Name */
        var ws_name = "San pham ban chay nam " + yearSelected;

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
