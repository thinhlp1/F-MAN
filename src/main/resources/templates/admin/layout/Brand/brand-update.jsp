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
                        <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/brands/">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay lại
                        </a>
                    </div>
                        <form:form action="${pageContext.request.contextPath}/admin/brands/update-form/${brand.id}" method="post" modelAttribute="brand"  enctype="multipart/form-data">
                    <div class="row">
                        <div id="imgDialog" class="col-4 mb-3">
                            <div id="image-box" class="text-center position-relative w-100 h-100"
                                style="border: dashed">
                               
                                <img src="${pageContext.request.contextPath}/views/admin/plugins/images/${brand.image}" id="blah" class="img-fluid" style="max-height: 300px">
                                <i id="icon-upload"
                                    class="fa fa-cloud-upload fs-7 position-absolute top-50 start-50 translate-middle"
                                    aria-hidden="true"></i>
                                <input accept="image/*" type='file' name="photo_file" id="imgInp" hidden="hidden">
                            </div>
                        </div>
                        <!--XỬ LÍ CLICK IMG SHOW DIALOG SELECT IMAGE-->
                        <script>
                            document.getElementById("imgDialog").onclick = (e) => {
                                document.getElementById("imgInp").click();
                            }
                            imgInp.onchange = evt => {
                                const [file] = imgInp.files
                                if (file) {
                                    blah.src = URL.createObjectURL(file)
                                    console.log(file.name);
                                    document.getElementById("icon-upload").remove();
                                    document.getElementById("image-box").style.border = "none";
                                }
                            }
                        </script>
                        <div class="col-8">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" path="id" id="id_brand" readonly="true"
                                            placeholder="ID thương hiệu" />
                                        <label for="id_brand">ID</label>
                                    </div>
                                </div>
                                <div class="col-12 mb-3">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" path="name" id="brand_name"
                                            placeholder="Tên Thương Hiệu" />
                                              <label for="brand_name">Tên Thương Hiệu</label>     
                                    </div>
                                </div>
                                  <div class="col-12 mb-3">
									   <div class="form-floating mb-3">
									   <c:if test="${activeBrand == false}">
									            <div class="form-check form-check-inline">
										            <input class="form-check-input"    type="radio" name="activeBrand" id="Activate_brand" value="true">
										            <label class="form-check-label" for="Activate_brand">Restore</label>
									   		 </div>
									   	</c:if>
									  		<!--   <div class="form-check form-check-inline">
										            <input class="form-check-input" type="radio" name="active" id="Inactive_brand" value="false">
										            <label class="form-check-label" for="Inactive_brand">Inactive</label>
									   		 </div> -->
									   </div>
								  </div>
                            </div>
                        </div>
                        <div class="col-12 text-end">
                            <button type="submit" class="btn btn-blue-primary btn-lg">
                                Cập Nhật
                            </button>
                        </div>
                    </div>
                    </form:form>

                </div>
                <!-- Footer -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                <!-- Footer -->
            </div>
        </div>

        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
    </body>

    </html>