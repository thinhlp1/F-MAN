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
    <title>FMan | Create Category</title>
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
            <!--      CODE TRONG DDAAY-->
            <c:if test="${isNameExsited}">
                <div id="show-alert" class="alert alert-warning alert-dismissible fade show sticky-top"
                     role="alert">
                    <strong>Warning!</strong> Tên danh mục này đã tồn tại vui lòng thử tên khác!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="back mb-3">
                <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/categorys/">
                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                    Quay Lại
                </a>
            </div>
            <div class="row">
                <form:form action="${pageContext.request.contextPath}/admin/categorys/create"
                           modelAttribute="productType" method="post">
                    <div class="col-12">
                        <div class="form-floating mb-3">
                            <form:input type="text" path="id" class="form-control" id="category_id" maxlength="15"
                                        placeholder="ID Danh Mục"/>
                            <label for="category_id">ID (VD: NIKE, ADIDAS,...)</label>
                            <form:errors path="id" cssClass=" text-danger"/>
                        </div>
                    </div>
                    <div class="col-12 mb-3">
                        <div class="form-floating mb-3">
                            <form:input type="text" path="name" class="form-control" id="category_name" maxlength="50"
                                        placeholder="Tên Danh Mục"/>
                            <label for="category_name">Tên danh mục</label>
                            <form:errors path="name" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="col-12 text-end">
                        <button class="btn btn-blue-primary btn-lg">
                            Tạo
                        </button>
                    </div>
                    <%--=======================================================--%>
                    <c:if test="${isCreateFasle}">
                        <%--Call thằng modal comfirm khi có lỗi--%>
                        <script>
                            window.onload = function CallModal() {
                                document.getElementById('modalshow').click();
                            }
                        </script>
                        <!-- Button trigger modal -->
                        <button id="modalshow" type="button"
                                class="d-none" data-bs-toggle="modal" data-bs-target="#showDialogConfirm">
                            Errors
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="showDialogConfirm" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-7" id="exampleModalLabel">Xác nhận</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body fs-4">
                                        Danh mục này đã tồn tại, bạn có muốn cập nhật lại danh mục này?
                                    </div>
                                    <div class="modal-footer">
                                        <div class="row g-3 w-100">
                                            <div class="col-6">
                                                <button type="button" class="btn btn-outline-secondary w-100"
                                                        data-bs-dismiss="modal">Không
                                                </button>
                                            </div>
                                            <div class="col-6">
                                                <button formaction="${pageContext.request.contextPath}/admin/categorys/create?isConfirm=true" type="submit" class="btn btn-viva w-100">Có</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <%--=======================================================--%>
                </form:form>
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