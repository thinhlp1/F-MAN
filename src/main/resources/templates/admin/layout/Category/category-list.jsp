<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FMan | Category</title>
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
            <c:choose>
                <c:when test="${isDelete}">
                    <div id="show-alert" class="alert alert-success alert-dismissible fade show sticky-top"
                         role="alert">
                        <strong>Success!</strong> Xóa danh mục thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:when>
                <c:when test="${isRestore}">
                    <div id="show-alert" class="alert alert-success alert-dismissible fade show sticky-top"
                         role="alert">
                        <strong>Success!</strong> Phục hồi danh mục thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:when>
            </c:choose>
            <div class="d-flex justify-content-between mb-3">
                <div class="sort-fillter align-items-center">
                    <span class="dropdown">
                        <button class="btn bg-transparent border shadow dropdown-toggle" type="button" id="sort"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                            ${field.equalsIgnoreCase("id") ? 'ID' : field.equalsIgnoreCase("name") ? 'Tên thương hiệu' : 'Sắp xếp theo'}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="sort">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=id&direction=${direction}&p=${items.number}">ID</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=name&direction=${direction}&p=${items.number}">Tên thương hiệu</a></li>
                        </ul>
                    </span>
                    <span class="dropdown">
                        <button class="btn bg-transparent border shadow dropdown-toggle" type="button" id="direction"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-sort-alpha-asc" aria-hidden="true"></i>
                            ${direction.equalsIgnoreCase("desc") ? 'Giảm dần' : 'Tăng dần'}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="sort">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=${field}&direction=asc&p=${items.number}">Tăng dần</a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=${field}&direction=desc&p=${items.number}">Giảm dần</a></li>
                        </ul>
                    </span>
                    <c:choose>
                        <c:when test="${!field.equalsIgnoreCase('inactive')}">
                            <a class="btn btn-outline-danger"
                               href="${pageContext.request.contextPath}/admin/categorys/?field=inactive&p=0">Không hoạt
                                động</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-outline-success"
                               href="${pageContext.request.contextPath}/admin/categorys/?field=active&p=0">Hoạt động</a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <form class="d-flex position-relative w-50"
                      action="${pageContext.request.contextPath}/admin/categorys/search"
                      method="post">
                    <input class="form-control rounded-1" placeholder="Nhập từ khóa (VD: Giày thể thao,...)"
                           name="keywords" value="${keywords}" aria-label="Search">
                    <button class="btn btn-outline-blue-primary position-absolute top-0 end-0 rounded-circle bg-transparent text-black-50 border-0"
                            type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </form>
                <a class="btn btn-blue-primary"
                   href="${pageContext.request.contextPath}/admin/categorys/create">
                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                    Thêm
                </a>
            </div>
            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead class="table-header-custom">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">ID</th>
                        <th scope="col">Tên thương hiệu</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${items.numberOfElements <= 0}">
                        <tr>
                            <th colspan="7" scope="row" class="text-center text-danger"><i class="fa fa-spinner"
                                                                                           aria-hidden="true"></i> Danh
                                sách rỗng
                            </th>
                        </tr>
                    </c:if>
                    <c:forEach varStatus="status" var="item" items="${items.content}">
                        <tr>
                            <th scope="row">${status.count}</th>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.active == 1 ?
                                    '<span class="badge bg-success">Hoạt động</span>' :
                                    '<span class="badge bg-danger">Vô hiệu hóa</span>'
                                    }
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.active == 0}">
                                        <button class="border-0 bg-transparent" data-bs-toggle="modal"
                                                data-bs-target="#CATEGORY${item.id}"><i class="fa fa-repeat"
                                                                                        aria-hidden="true"></i>
                                        </button>
                                        <div class="modal fade" id="CATEGORY${item.id}" aria-hidden="true"
                                             tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title text-danger">
                                                            <i class="fa fa-exclamation-triangle"
                                                               aria-hidden="true"></i>
                                                            PHỤC HỒI
                                                        </h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>

                                                    </div>
                                                    <div class="modal-body text-center">
                                                        <img class="img-fluid mb-3" width="300px" height="300px"
                                                             src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-sayin-happy.jpg">
                                                        <h4>Bạn có chắc chắn muốn phục hồi danh mục <span
                                                                class="text-danger fw-bold">${item.name}</span>?</h4>
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
                                                                <a href="${pageContext.request.contextPath}/admin/categorys/restore/${item.id}"
                                                                   class="btn btn-blue-primary w-100">Có</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="text-dark"
                                           href="${pageContext.request.contextPath}/admin/categorys/update-form/${item.id}">
                                            <button class="border-0 bg-transparent">
                                                <i class="fa fa-pencil" aria-hidden="true"></i>
                                            </button>
                                        </a>
                                        <button class="border-0 bg-transparent" data-bs-toggle="modal"
                                                data-bs-target="#CATEGORY${item.id}"><i class="fa fa-trash"
                                                                                        aria-hidden="true"></i>
                                        </button>
                                        <div class="modal fade" id="CATEGORY${item.id}" aria-hidden="true"
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
                                                        <h4>Bạn có chắc chắn muốn xóa danh mục <span
                                                                class="text-danger fw-bold">${item.name}</span>?</h4>
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
                                                                <a href="${pageContext.request.contextPath}/admin/categorys/delete/${item.id}"
                                                                   class="btn btn-blue-primary w-100">Có</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <div class="d-flex justify-content-center pagination-custom">
                    <ul class="pagination">
                        <c:if test="${items.number > 0}">
                            <li class="page-item">
                                <a class="page-link fs-4"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=${field}&direction=${direction}&p=${items.number - 1}">
                                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${items.totalPages > 0}">
                            <c:forEach var="i" begin="0" end="${items.totalPages - 1}">
                                <c:if test="${i >= items.number - 2 && i <= items.number + 2}">
                                    <li class="page-item ${i == items.number ? 'active' : ''}">
                                        <a class="page-link fs-4"
                                           href="${pageContext.request.contextPath}/admin/categorys/?field=${field}&direction=${direction}&p=${i}">
                                                ${i + 1}
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${items.number < items.totalPages - 1}">
                            <li class="page-item">
                                <a class="page-link fs-4"
                                   href="${pageContext.request.contextPath}/admin/categorys/?field=${field}&direction=${direction}&p=${items.number + 1}">
                                    <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
                                </a>
                            </li>
                        </c:if>
                    </ul>
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