<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Product Details</title>
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
                    <!--BACK-->
                    <div class="back mb-3">
                        <a class="btn btn-blue-primary" href="order-list.html">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay Lại
                        </a>
                    </div>
                    <div class="product-details">
                        <div class="product-details-title mb-4">
                            <h3 class="fw-bold">Chi tiết sản phẩm</h3>
                        </div>
                        <div class="row product-details-content">
                            <div class="col-md-5">
                                <table class="table table table-responsive-sm table-borderless">
                                    <tbody class="custom-table-list">
                                        <tr>
                                            <th>Tên khách hàng:</th>
                                            <td>Nguyễn Phước Tài</td>
                                        </tr>
                                        <tr>
                                            <th>Địa chỉ</th>
                                            <td>Amalazada</td>
                                        </tr>
                                        <tr>
                                            <th>Phương thức thanh toán:</th>
                                            <td>Ship COD</td>
                                        </tr>
                                        <tr>
                                            <th>Ngày mua:</th>
                                            <td>20/09/2033</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-5 offset-2">
                                <table class="table table-responsive-sm table-borderless">
                                    <tbody class="custom-table-list">
                                        <tr>
                                            <th>Tên sản phẩm:</th>
                                            <td>Nike Air Force</td>
                                        </tr>
                                        <tr>
                                            <th>Số lượng:</th>
                                            <td>Nguyễn Phước Tài</td>
                                        </tr>
                                        <tr>
                                            <th>Đơn giá:</th>
                                            <td>1.200.000 vnđ</td>
                                        </tr>
                                        <tr>
                                            <th>Ghi chú:</th>
                                            <td>Lorem ipsum...</td>
                                        </tr>
                                        <tr class="fw-bolder text-danger fs-6">
                                            <th class="text-decoration-underline">Tổng tiền:</th>
                                            <td>200.000 vnđ</td>
                                        </tr>
                                    </tbody>
                                </table>
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