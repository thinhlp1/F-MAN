<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <!DOCTYPE html>
      <html dir="ltr" lang="en">

      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>FMan | Discount</title>

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
                <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/discounts/">
                  <i class="fa fa-chevron-circle-left" aria-hidden="true"></i> Quay
                  lại
                </a>
              </div>
              <div class="row">
                <!--XỬ LÍ CLICK IMG SHOW DIALOG SELECT IMAGE-->
                <script>
                  document.getElementById("imgDialog").onclick = (e) => {
                    document.getElementById("imgInp").click();
                  };
                  imgInp.onchange = (evt) => {
                    const [file] = imgInp.files;
                    if (file) {
                      blah.src = URL.createObjectURL(file);
                      console.log(file.name);
                      document.getElementById("icon-upload").remove();
                      document.getElementById("image-box").style.border = "none";
                    }
                  };
                </script>
                
                <form:form action="${pageContext.request.contextPath}/admin/discounts/update/${voucher.id}"
                  modelAttribute="voucher" method="post">
                  <div class="col-12">
                    <span class="text-danger fst-italic fw-bold">${message}</span>
                    <form:errors class="text-danger fst-italic fw-bold" path="name" />
                    <div class="row">
                      <div class="col-6">
                        <div class="form-floating mb-3">
                          <form:input type="text" class="form-control" id="name" path="name"
                            placeholder="${voucher.name}" />
                          <label for="name">Tên mã giảm giá</label>
                        </div>
                      </div>

                      <form:errors class="text-danger fst-italic fw-bold" path="salePercent" />
                      <div class="col-6">
                        <div class="form-floating mb-3">
                          <div class="form-floating mb-3">
                            <form:input type="number" class="form-control" id="sale_percent" path="salePercent"
                              placeholder="${voucher.salePercent}" />
                            <label for="sale_percent">Phần trăm giảm</label>
                          </div>
                        </div>
                      </div>

                      <form:errors class="text-danger fst-italic fw-bold" path="startAt" />
                      <div class="col-6">
                        <div class="form-floating mb-3">
                          <div class="form-floating mb-3">
                            <form:input type="datetime-local" class="form-control" id="start-date" path="startAt"
                              value="${voucher.startAt}" />
                            <label for="start-date">Ngày bắt đầu</label>
                          </div>
                        </div>
                      </div>

                      <form:errors class="text-danger fst-italic fw-bold" path="endAt" />
                      <div class="col-6">
                        <div class="form-floating mb-3">
                          <div class="form-floating mb-3">
                            <form:input type="datetime-local" class="form-control" id="end-date" path="endAt"
                              value="${voucher.endAt}" />
                            <label for="end-date">Ngày kết thúc</label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="col-12">
                    <form:errors class="text-danger fst-italic fw-bold" path="minPrice" />
                    <div class="row">
                      <div class="col-12">
                        <div class="form-floating mb-3">
                          <form:input type="number" class="form-control" id="min-price" path="minPrice"
                            placeholder="${voucher.minPrice}" />
                          <label for="min-price">Giá thấp nhất</label>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="col-12 text-end">
                    <button type="submit" class="btn btn-blue-primary btn-lg">
                      Cập Nhật
                    </button>
                  </div>
                </form:form>
              </div>
            </div>
            <!-- Footer -->
            <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
            <!-- Footer -->
          </div>
        </div>

        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
        <script src="${pageContext.request.contextPath}/views/admin/js/custom_discount.js"></script>
      </body>

      </html>