<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Brands</title>
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
                    <div class="d-flex justify-content-between mb-3">
                        <div class="sort-fillter align-items-center">
                            <span class="dropdown">
                                <button class="btn bg-transparent border shadow dropdown-toggle" type="button" id="sort"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                                    Sắp xếp theo
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="sort">
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/admin/brands/?field=id">ID</a></li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/admin/brands/?field=name">Name</a></li>
                                   
                                </ul>
                            </span>
                        </div>
                        <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/brands/create">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Thêm
                        </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead class="table-header-custom">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tên Thương Hiệu</th>
                                    <th scope="col">Hình Ảnh</th>
                                    <th scope="col">Trạng Thái</th>                   
                                    <th scope="col">Thao Tác</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${page.content}">
                            
                           
                                <tr>
                                    <th scope="row">${item.id}</th>
                                    <th scope="row">${item.name}</th>
                                    <th scope="row"> <img alt="" height="40px" src="${pageContext.request.contextPath}/views/admin/plugins/images/${item.image}"></th>
                                    <td>${item.active == 1 ?
                                    '<span class="badge bg-success">Hoạt động</span>' :
                                    '<span class="badge bg-danger">Vô hiệu hóa</span>'
                                    }</td>
                                    
                                    <td>
                                        <a class="text-dark"
                                            href="${pageContext.request.contextPath}/admin/brands/update-form/${item.id}">
                                            <button class="border-0 bg-transparent">
                                                <i class="fa fa-pencil" aria-hidden="true"></i>
                                            </button>
                                        </a>
                                        <a class="text-dark"
                                            href="${pageContext.request.contextPath}/admin/brands/details/${item.id}">
                                            <button class="border-0 bg-transparent">
                                                <i class="fa fa-eye" aria-hidden="true"></i>
                                            </button>
                                        </a>
                                        <button class="border-0 bg-transparent" data-bs-toggle="modal"
                                            data-bs-target="#${item.id}"><i class="fa fa-trash" aria-hidden="true"></i>
                                        </button>
                                        <div class="modal fade" id="${item.id}" aria-hidden="true"
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
                                                        <h4>Bạn chắc chắn muốn xóa thương hiệu ${item.name} không?</h4>
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
                                                                <a href="${pageContext.request.contextPath}/admin/brands/delete/${item.id}" class="btn btn-blue-primary w-100" >Có</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </td>
                                </tr>
                                 </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-flex justify-content-center pagination-custom">
                         <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link fs-4"
                               href="${pageContext.request.contextPath}/admin/brands/?field=${field}&p=${page.number - 1 < 0 ? '0' : page.number-1}">
                                <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            </a>
                        </li>
                        <c:forEach var="i" begin="0" end="${page.totalPages - 1 < 0 ? 0 : page.totalPages - 1}">
                            <li class="page-item ${i == page.number ? 'active' : ''}"><a class="page-link fs-4"
                                                     href="${pageContext.request.contextPath}/admin/brands/?field=${field}&p=${i}">${i + 1}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <a class="page-link fs-4"
                               href="${pageContext.request.contextPath}/admin/brands/?field=${field}&p=${page.number + 1 > page.totalPages - 1 ? page.totalPages - 1 : page.number + 1}">
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