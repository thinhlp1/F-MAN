<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Create Products</title>
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
                    <!--      CODE TRONG DDAAY-->
                    <div class="back mb-3">
                        <a class="btn btn-blue-primary" href="order-list.html">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay Lại
                        </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead class="table-header-custom">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tên khách hàng</th>
                                    <th scope="col">Đơn giá</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Phương thức thanh toán</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Nguyễn Phước Tài</td>
                                    <td>2.000.000 vnđ</td>
                                    <td>Campuchiasaa</td>
                                    <td>Ship COD</td>
                                    <td>
                                        <a class="text-dark" href="order-details.html">
                                            <button class="border-0 bg-transparent">
                                                <i class="fa fa-eye" aria-hidden="true"></i>
                                            </button>
                                        </a>
                                        <a class="text-dark" href="">
                                            <button class="border-0 bg-transparent text-success">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </a>

                                        <button class="border-0 bg-transparent text-danger" data-bs-toggle="modal"
                                            href="#confirmDeleteDialog"><i class="fa fa-times" aria-hidden="true"></i>
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
                                                        <h4>Bạn có chắc muốn xóa không?</h4>
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