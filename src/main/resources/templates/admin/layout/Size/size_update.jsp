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
    <title>FMan | Update Size</title>
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
            <c:choose>
                <c:when test="${isCreate}">
                    <div id="show-alert" class="alert alert-success alert-dismissible fade show sticky-top"
                         role="alert">
                        <strong>Success!</strong> Tạo size thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:when>
                <c:when test="${isUpdate}">
                    <div id="show-alert" class="alert alert-success alert-dismissible fade show sticky-top"
                         role="alert">
                        <strong>Success!</strong> Cập nhật size thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:when>
                <c:when test="${isUpdateFalse}">
                        <div id="show-alert" class="alert alert-danger alert-dismissible fade show sticky-top"
                             role="alert">
                            <strong>Fail!</strong> Size đã tồn tại!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                </c:when>
            </c:choose>

            <div class="back mb-3">
                <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/sizes/">
                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                    Quay lại
                </a>
            </div>
            <div class="row">
                <form:form action="${pageContext.request.contextPath}/admin/sizes/update-form/${size.id}" method="post"
                           modelAttribute="size">
                    <div class="col-12">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating mb-3">
                                    <form:input type="text" path="id" class="form-control" id="size_id" maxlength="6" readonly="true"
                                                placeholder="Nhập ID"/>
                                    <label for="size_id">ID</label>
                                    <form:errors path="id" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating mb-3">
                                    <form:input type="number" path="size" class="form-control" id="size" step="0.5" maxlength="3"
                                                placeholder="Nhập size"/>
                                    <label for="size">Size</label>
                                    <form:errors path="size" cssClass="text-danger"/>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="form-floating mb-3">
                                    <div class="form-floating mb-3">
                                        <form:input type="number" path="length" class="form-control" id="size_dai" maxlength="3"
                                                    placeholder="Nhập chiều dài"/>
                                        <label for="size_dai">Chiều dài <sup>(cm)</sup></label>
                                        <form:errors path="length" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating mb-3">
                                    <div class="form-floating mb-3">
                                        <form:input type="number" path="width" class="form-control" id="size_rong" maxlength="3"
                                                    placeholder="Nhập chiều rộng"/>
                                        <label for="size_rong">Chiều rộng <sup>(cm)</sup></label>
                                        <form:errors path="width" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 text-end">
                        <button class="btn btn-blue-primary btn-lg">
                            Cập nhật
                        </button>
                    </div>
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