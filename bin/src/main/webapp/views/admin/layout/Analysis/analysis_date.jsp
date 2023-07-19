<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <div class="mb-3 d-flex justify-content-between">
                        <div class="">
                            <label for="sort_date" class="fs-5 me-2 fw-bold">Thống kê theo thời gian</label>
                            <input class="p-2 fs-5 border-0 shadow-lg" type="date" id="sort_date">
                            <span class="fs-6 mx-2">&nbsp;&rarr;&nbsp;</span>
                            <input class="p-2 fs-5 border-0 shadow-lg" type="date" id="sort_date">
                        </div>
                        <button class="btn-blue-primary">Thống kê theo loại</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead class="table-header-custom">
                                <tr>
                                    <th scope="col">STT</th>
                                    <th scope="col">Ngày Bắt Đầu</th>
                                    <th scope="col">Ngày Kết Thúc</th>
                                    <th scope="col">Tổng Doanh Thu</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>20/09/2023</td>
                                    <td>20/09/2023</td>
                                    <td>
                                        20.000.000vnđ
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="d-flex justify-content-center pagination-custom">
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link fs-4" href="#">
                                        <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link fs-4" href="#">1</a></li>
                                <li class="page-item">
                                    <a class="page-link fs-4" href="#">
                                        <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- Footer -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                <!-- Footer -->
            </div>
        </div>
        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
    </body>

    </html>