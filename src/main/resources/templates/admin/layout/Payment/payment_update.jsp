<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>FMan | Update Payment</title>
    <jsp:include
      page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp"
    />
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

    <div
      id="main-wrapper"
      data-layout="vertical"
      data-navbarbg="skin5"
      data-sidebartype="full"
      data-sidebar-position="absolute"
      data-header-position="absolute"
      data-boxed-layout="full"
    >
      <!-- ============================================================== -->
      <!-- Header -->
      <jsp:include
        page="${pageContext.request.contextPath}/views/admin/layout/Component/header.jsp"
      />
      <!-- Header -->
      <!-- Sidebar -->
      <jsp:include
        page="${pageContext.request.contextPath}/views/admin/layout/Component/sidebar.jsp"
      />
      <!-- Sidebar -->

      <div id="container" class="page-wrapper">
        <!-- Taskbar -->
        <jsp:include
          page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp"
        />
        <!-- Taskbar -->
        <div class="container-fluid">
          <!--      CODE TRONG DDAAY-->
          <div class="back mb-3">
            <a
              class="btn btn-blue-primary"
              href="${pageContext.request.contextPath}/admin/payments/"
            >
              <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
              Quay lại
            </a>
          </div>
          <form:form
            action="${pageContext.request.contextPath}/admin/payments/update/${payment.id}"
            modelAttribute="payment"
            method="post"
            enctype="multipart/form-data"
          >
            <div class="row">
              <div id="imgDialog" class="col-4 mb-3">
                <div
                  id="image-box"
                  class="text-center position-relative w-100 h-100"
                  style="border: dashed"
                >
                  <img
                    id="blah"
                    class="img-fluid"
                    src="${pageContext.request.contextPath}/views/admin/plugins/images/${payment.image}"
                  />
                  <i
                    id="icon-upload"
                    class="fa fa-cloud-upload fs-7 position-absolute top-50 start-50 translate-middle"
                    aria-hidden="true"
                  ></i>
                  <input
                    accept="image/*"
                    type="file"
                    id="imgInp"
                    hidden="hidden"
                    name="photo_file"
                  />
                </div>
              </div>
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
              <div class="col-8">
                <div class="row">
                  <div class="col-12">
                    <div class="form-floating mb-3">
                      <div class="form-floating mb-3">
                        <form:input
                          type="text"
                          class="form-control"
                          id="payment_id"
                          placeholder="${payment.id}"
                          path="id"
                          readonly="true"
                        />
                        <label for="payment_id">ID</label>
                      </div>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="form-floating mb-3">
                      <form:input
                        type="text"
                        class="form-control"
                        id="payment_name"
                        placeholder="${payment.name}"
                        path="name"
                      />
                      <label for="payment_name">Tên phương thức</label>
                    </div>
                  </div>
                  <div class="col-12">
                    <form:errors
                      class="text-danger fst-italic fw-bold"
                      path="account_number"
                    />
                    <div class="form-floating mb-3">
                      <form:input
                        type="text"
                        class="form-control"
                        id="card_number"
                        placeholder="${payment.account_number}"
                        path="account_number"
                      />
                      <label for="card_number">Số tài khoản</label>
                    </div>
                  </div>
                  <div class="col-12">
                    <label class="form-label">Trạng thái</label>
                    <div class="form-check form-switch ms-3">
                      <form:checkbox
                        class="form-check-input"
                        id="active"
                        path="active"
                        value="1"
                      />
                      <label class="form-check-label" for="active"
                        >Hoạt động</label
                      >
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-12 text-end">
                <button class="btn btn-blue-primary btn-lg">Cập Nhật</button>
              </div>
            </div>
          </form:form>
        </div>
        <!-- Footer -->
        <jsp:include
          page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp"
        />
        <!-- Footer -->
      </div>
    </div>

    <jsp:include
      page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp"
    />
  </body>
</html>
