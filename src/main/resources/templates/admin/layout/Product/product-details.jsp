<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FMan | Product Details</title>
    <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp"/>
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
    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/header.jsp"/>
    <!-- Header -->
    <!-- Sidebar -->
    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/sidebar.jsp"/>
    <!-- Sidebar -->

    <div id="container" class="page-wrapper">
        <!-- Taskbar -->
        <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp"/>
        <!-- Taskbar -->
        <div class="container-fluid">
            <!--      CODE TRONG DDAAY          -->
            <!--BACK-->
            <div class="back mb-3">
                <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/products/">
                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                    Quay lại
                </a>
            </div>
            <div class="product-details">
                <div class="product-details-title mb-4">
                    <h3 class="fw-bold">Chi tiết sản phẩm</h3>
                </div>
                <div class="row product-details-content">
                    <div class="col-md-3 order-md-2">
                        <img class="img-fluid rounded-circle"
                             src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${product.image}">
                        <%--                        <img class="img-fluid" style="border-radius: 50%; width: 200px; height: 200px" src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${product.image}" alt="Demo">--%>
                    </div>
                    <div class="col-md-4 order-md-1">
                        <table class="table table table-responsive-sm table-borderless">
                            <tbody class="custom-table-list">
                            <tr>
                                <th>ID sản phẩm:</th>
                                <td>${product.id}</td>
                            </tr>
                            <tr>
                                <th>Tên sản phẩm:</th>
                                <td>${product.name}</td>
                            </tr>
                            <tr>
                                <th>Loại sản phẩm:</th>
                                <td>${product.productType.name}</td>
                            </tr>
                            <tr>
                                <th>Thương hiệu:</th>
                                <td>${product.brand.name}</td>
                            </tr>
                            <tr>
                                <th>Giá:</th>
                                <td>${product.price}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-5 order-md-3">
                        <table class="table table-responsive-sm table-borderless">
                            <tbody class="custom-table-list">
                            <tr>
                                <th>Ngày tạo:</th>
                                <td>${product.createAt}</td>
                            </tr>
                            <tr>
                                <th>Ngày cập nhật:</th>
                                <td>${product.updateAt}</td>
                            </tr>
                            <tr>
                                <th class="mb-3">
                                    <p class="mb-4">
                                        Size:
                                    </p>
                                    <p>
                                        Số lượng:
                                    </p>
                                </th>
                                <td>
                                    <div class="row">
                                        <c:forEach var="item" items="${product.productSizes}">
                                            <div class="col">
                                                <p class="mb-4">${item.size.size}</p>
                                                <p>${item.quantity}</p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-12 mt-0">
                    <table class="table table-borderless">
                        <tr>
                            <th style="width: 80px">
                                Ghi chú:
                            </th>
                            <td class="text-inverse">
                                ${product.desc}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
        <!-- Footer -->
        <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp"/>
        <!-- Footer -->
    </div>
</div>


<jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp"/>

</body>

</html>