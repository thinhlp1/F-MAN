<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html dir="ltr" lang="en">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <!-- Tell the browser to be responsive to screen width -->
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>FMan | Analystis</title>
            <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp" />
        </head>

        <body>
            <!-- Spinner Hiệu ứng load trang -->
            <div class="preloader">
                <div class="lds-ripple">
                    <div class="lds-pos"></div>
                    <div class="lds-pos"></div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- Main wrapper - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
                data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
                <!-- ============================================================== -->
                <!-- Header -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/header.jsp" />
                <!-- Header -->
                <!-- Sidebar -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/sidebar.jsp" />
                <!-- Sidebar -->
                <div id="container" class="page-wrapper">
                    <!-- Taskbar -->
                    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp" />
                    <!-- Taskbar -->
                    <div class="container-fluid">
                        <!--      CODE TRONG DDAAY          -->
                        <div class="row d-flex">
                            <div class=" col-2">
                                <a type="button" href="/admin/analysis/revenue" class="btn btn-blue-primary">Thống kê doanh thu</a >
                            </div>
                            <div class="col-2">
                                <a href="/admin/analysis/sellProducts" class="btn btn-blue-primary">Thống kê sản phẩm</a >
                            </div>
                            <div class="col-2">
                                <a href="/admin/analysis/orders" class="btn btn-blue-primary">Thống kê đơn hàng</a >
                            </div>
                        </div>
                        <div class="d-flex justify-content-between mb-3 mt-5">
                            <div class="sort-fillter align-items-center">

                                <span class="dropdown mb-5">

                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-renvueYear" data-bs-toggle="dropdown" aria-expanded="false">

                                        Thời gian
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="sort" id="sortOrder">
                                      
                                        <c:forEach items="${listYear}" var="year">
                                            <li><a class="dropdown-item" value="${year}" type="button"
                                                href="/admin/analysis/orders?year=${year}" >${year}</a>
                                         </li>
                                        </c:forEach>

                                    </ul>
                                </span>
                                <span class="dropdown mb-5">

                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-sortOrder" data-bs-toggle="dropdown" aria-expanded="false">
                                        Chiều
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="sort" id="sortOrder">
                                        <li><a class="dropdown-item" value="asc" type="button" id="asc"
                                                onclick="setValuesortOrder('asc','Tăng dần')">Tăng dần</a>
                                        </li>
                                        <li><a class="dropdown-item" value="desc" type="button" id="desc"
                                                onclick="setValuesortOrder('desc','Giảm dần')">Giảm dần</a>
                                        </li>
                                        <li><a class="dropdown-item" value="default" type="button" id="default"
                                                onclick="setValuesortOrder('default','Mặc định')">Mặc định</a>
                                        </li>
                                      

                                    </ul>
                                </span>
                                <span class="dropdown mb-5">

                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-filtQuarter" data-bs-toggle="dropdown" aria-expanded="false">
                                        Quý
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="sort" id="sortOrder">
                                        <li><a class="dropdown-item" value="quy1" type="button" id="quy1"
                                                onclick="filtDataQuarter('quy1','Quý 1')">Quý 1</a>
                                        </li>
                                        <li><a class="dropdown-item" value="quy2" type="button" id="quy2"
                                                onclick="filtDataQuarter('quy2','Quý 2')">Quý 2</a>
                                        </li>
                                        <li><a class="dropdown-item" value="quy3" type="button" id="quy3"
                                                onclick="filtDataQuarter('quy3','Quý 3')">Quý 3</a>
                                        </li>
                                        <li><a class="dropdown-item" value="quy4" type="button" id="quy4"
                                                onclick="filtDataQuarter('quy4','Quý 4')">Quý 4</a>
                                        </li>
                                        <li><a class="dropdown-item" value="all" type="button" id="all"
                                            onclick="filtDataQuarter('all','Cả năm')">Cả năm</a>
                                    </li>

                                    </ul>
                                </span>

                            </div>

                        </div>
                        <div>
                            <canvas id="myChart"></canvas>
                        </div>



                    </div>
                    <!-- Footer -->
                    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                    <!-- Footer -->
                </div>
            </div>
            <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script>
                const ctx = document.getElementById('myChart');

                var listData = [];
                var listDataFilt = [];
                var listDataSort = [];
                var isSortOrder = "default";
                var isFiltQuartar = "all";
                document.getElementById("btn-renvueYear").innerHTML = "${not empty year ? year : 'Năm'}";
                <c:forEach items="${dataOrderByMonths}" var="data">
                    listData.push(${data})
                </c:forEach>

                listDataFilt = listData.slice();
                listDataSort = listData.slice();
               

                console.log(listData);
                var chart = new Chart(ctx, {
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

                function setValuesortOrder(order, label) {
                    document.getElementById("btn-sortOrder").innerHTML = label;

                    isSortOrder = order; // Giá trị sắp xếp hiện tại (asc: tăng dần, desc: giảm dần)
                    var sortedData = [];
                    var listNeedSort = [];

                    if (isFiltQuartar === "all") {
                        listNeedSort= listDataFilt;
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

                function filtDataQuarter(quarter, label) {
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
            </script>
        </body>

        </html>