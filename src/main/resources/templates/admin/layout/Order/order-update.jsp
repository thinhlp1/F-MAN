<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Update Products</title>
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
                        <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/orders/">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay Lại
                        </a>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="customer_name" placeholder="Tên sản phẩm">
                                <label for="customer_name">Tên khách hàng</label>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="customer_phone" placeholder="Số điện thoại">
                                <label for="customer_phone">Số điện thoại</label>
                            </div>
                        </div>


                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="address" placeholder="Địa chỉ">
                                <label for="address">Địa chỉ</label>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <select class="form-select" id="payment_method">
                                    <option selected>Phương thức thanh toán</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                </select>
                                <label for="payment_method">Phương thức thanh toán</label>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="address" placeholder="Địa chỉ">
                                <label for="address">Giảm giá</label>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="address" placeholder="Địa chỉ">
                                <label for="address">Tổng Tiền</label>
                            </div>
                        </div>
                        <h3 class="mt-3 mb-2 fw-bold">Danh sách sản phẩm</h3>
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th class="text-uppercase fw-bold" scope="col">Tên Sản phẩm</th>
                                        <th class="text-uppercase fw-bold" scope="col">Số lượng</th>
                                        <th class="text-uppercase fw-bold" scope="col">Đơn giá</th>
                                        <th class="text-uppercase fw-bold" scope="col">Tổng giá</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Nike</th>
                                        <td><input type="number" value="20"
                                                class="h-100 text-start p-1 border-0 bg-transparent"></td>
                                        <td>2.000.000 vnđ</td>
                                        <td>4.000.000 vnđ</td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>

                    </div>

                    <div class="col-12 text-end">
                        <a class="btn btn-outline-danger btn-lg me-2" href="product-list.html">
                            Hủy
                        </a>
                        <button class="btn btn-blue-primary btn-lg">
                            Cập Nhật
                        </button>
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