<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Payments</title>
        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp" />
    </head>

    <body>
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
                    <div class="text-end mb-3">
                        <a class="btn btn-blue-primary" href="payment_add.html">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Thêm
                        </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead class="table-header-custom">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tên Phương Thức</th>
                                    <th scope="col">Hình ảnh</th>
                                    <th scope="col">Ngày Tạo</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Banking</td>
                                    <td>
                                        <img src="https://preview.redd.it/rrz3hmsxcll71.png?width=640&crop=smart&auto=webp&s=87cc5ed38d8f088ef9fffef7a4c5756b64309d6a"
                                            alt="test" style="width: 40px; height: 40px;">
                                    </td>
                                    <td>20/09/2023</td>

                                    <td>
                                        <a class="text-dark" href="payment_update.html">
                                            <button class="border-0 bg-transparent">
                                                <i class="fa fa-pencil" aria-hidden="true"></i>
                                            </button>
                                        </a>

                                        <button class="border-0 bg-transparent" data-bs-toggle="modal"
                                            href="#confirmDeleteDialog"><i class="fa fa-trash" aria-hidden="true"></i>
                                        </button>
                                        <div class="modal fade" id="confirmDeleteDialog" aria-hidden="true"
                                            tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title text-danger">
                                                            <i class="fa fa-exclamation-triangle"
                                                                aria-hidden="true"></i>
                                                            XÓA
                                                        </h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>

                                                    </div>
                                                    <div class="modal-body text-center">
                                                        <img class="img-fluid mb-3" width="300px" height="300px"
                                                            src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-screaming-no.jpg">
                                                        <h4>Bạn có chắc chắn muốn xóa?</h4>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="row g-3 w-100">
                                                            <div class="col-6">
                                                                <button class="btn btn-outline-danger w-100"
                                                                    class="btn-close" data-bs-dismiss="modal"
                                                                    aria-label="Close">Không
                                                                </button>
                                                            </div>
                                                            <div class="col-6">
                                                                <button class="btn btn-blue-primary w-100">Có</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

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