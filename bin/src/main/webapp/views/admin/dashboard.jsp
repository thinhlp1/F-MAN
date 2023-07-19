<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Dashboard</title>
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
                    <div class="row justify-content-center mb-3">
                        <div class="col-md-4 col-sm-12">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="row g-0 align-items-center">
                                        <div class="col-3">
                                            <div class="bg-blue-primary text-white rounded-pill p-4 text-center"
                                                style="width: 70px; height: 70px">
                                                <i class="fa fa-cube fs-7" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <h3>57,600</h3>
                                            <p>Tổng Đã Bán</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="row g-0 align-items-center">
                                        <div class="col-3">
                                            <div class="bg-blue-primary text-white rounded-pill p-4 text-center"
                                                style="width: 70px; height: 70px">
                                                <i class="fa fa-users fs-7" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <h3>7,800</h3>
                                            <p>Khách Hàng Truy Cập</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="row g-0 align-items-center">
                                        <div class="col-3">
                                            <div class="bg-blue-primary text-white rounded-pill p-4 text-center"
                                                style="width: 70px; height: 70px">
                                                <i class="fa fa-cube fs-7" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <h3>11,23%</h3>
                                            <p>Trạng Thái</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="dashboard">
                        <div class="dashboard-title mb-3">
                            <h3 class="fw-bold">
                                Sản Phẩm Bán Chạy
                            </h3>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-4 col-sm-12">
                                <div class="card  shadow">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <img src="https://preview.redd.it/rrz3hmsxcll71.png?width=640&crop=smart&auto=webp&s=87cc5ed38d8f088ef9fffef7a4c5756b64309d6a"
                                                    class="img-fluid rounded-circle border">
                                            </div>
                                            <div class="col-8">
                                                <h4 class="fw-bold">Nike Air Force 1</h4>
                                                <p>1.500.000 vnđ</p>
                                                <p class="text-truncate">Mô Tả....</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-transparent position-relative">
                                        <div
                                            class="count-percent position-absolute end-0 bottom-0 bg-blue-primary text-white px-3 py-2">
                                            <span class="text-success">&bull;</span>
                                            +5%
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-12">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <img src="https://preview.redd.it/rrz3hmsxcll71.png?width=640&crop=smart&auto=webp&s=87cc5ed38d8f088ef9fffef7a4c5756b64309d6a"
                                                    class="img-fluid rounded-circle border">
                                            </div>
                                            <div class="col-8">
                                                <h4 class="fw-bold">Nike Air Force 1</h4>
                                                <p>1.500.000 vnđ</p>
                                                <p class="text-truncate">Mô Tả....</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-transparent position-relative">
                                        <div
                                            class="count-percent position-absolute end-0 bottom-0 bg-blue-primary text-white px-3 py-2">
                                            <span class="text-danger">&bull;</span>
                                            -5%
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-12">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <img src="https://preview.redd.it/rrz3hmsxcll71.png?width=640&crop=smart&auto=webp&s=87cc5ed38d8f088ef9fffef7a4c5756b64309d6a"
                                                    class="img-fluid rounded-circle border">
                                            </div>
                                            <div class="col-8">
                                                <h4 class="fw-bold">Nike Air Force 1</h4>
                                                <p>1.500.000 vnđ</p>
                                                <p class="text-truncate">Mô Tả....</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-transparent position-relative">
                                        <div
                                            class="count-percent position-absolute end-0 bottom-0 bg-blue-primary text-white px-3 py-2">
                                            <span class="text-success">&bull;</span>
                                            +15%
                                        </div>
                                    </div>
                                </div>
                            </div>
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