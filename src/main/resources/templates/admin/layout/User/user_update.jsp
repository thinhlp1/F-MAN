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
    <title>FMan | Update User</title>
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
              href="${pageContext.request.contextPath}/admin/users/"
            >
              <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
              Quay Lại
            </a>
          </div>
          <form:form
            action="${pageContext.request.contextPath}/admin/users/update/${user.username}"
            modelAttribute="user"
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
                    src="${pageContext.request.contextPath}/views/admin/plugins/images/${user.image}"
                    id="blah"
                    class="img-fluid w-100 h-100"
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
                      <form:input
                        type="text"
                        class="form-control"
                        id="user_name"
                        placeholder="${user.name}"
                        path="name"
                      />
                      <label for="user_name">Họ và tên</label>
                    </div>
                  </div>

                  <div class="col-12">
                    <div class="form-floating mb-3">
                      <div class="form-floating mb-3">
                        <form:input
                          type="text"
                          class="form-control"
                          id="user_account"
                          placeholder="${user.username}"
                          path="username"
                          readonly="true"
                        />
                        <label for="user_account">Tên đăng nhập</label>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="col-6">
                    <div class="form-floating mb-3">
                      <div class="form-floating mb-3">
                        <form:input
                          type="text"
                          class="form-control"
                          id="user_password"
                          path="password"
                          value="Nhập mật khẩu mới"
                        />
                        <label for="user_password">Nhập mật khẩu</label>
                      </div>
                    </div>
                  </div> -->

                  <div class="col-12">
                    <div class="form-floating mb-3">
                      <div class="form-floating mb-3">
                        <form:input
                          type="text"
                          class="form-control"
                          id="user_email"
                          placeholder="${user.email}"
                          path="email"
                          readonly="true"
                        />
                        <label for="user_email">Email</label>
                      </div>
                    </div>
                  </div>

                  <div class="col-12">
                    <div class="form-floating mb-3">
                      <div class="form-floating mb-3">
                        <form:input
                          type="text"
                          class="form-control"
                          id="user_email"
                          placeholder="${user.numberPhone}"
                          path="numberPhone"
                          readonly="true"
                        />
                        <label for="user_email">Số điện thoại</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="col-12 mb-3">
                  <div class="row">
                    <div class="col-6">
                      <label class="me-2">Vai trò:</label>
                      <div class="form-check form-check-inline">
                        <form:radiobutton
                          class="form-check-input"
                          name="inlineRadioOptions"
                          id="inlineRadio1"
                          value="ADMIN"
                          path="roleId"
                        />
                        <label class="form-check-label" for="inlineRadio1"
                          >Admin</label
                        >
                      </div>
                      <div class="form-check form-check-inline">
                        <form:radiobutton
                          class="form-check-input"
                          name="inlineRadioOptions"
                          id="inlineRadio2"
                          value="USER"
                          path="roleId"
                        />
                        <label class="form-check-label" for="inlineRadio2"
                          >User</label
                        >
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="form-check form-switch">
                        <label
                          class="form-check-label"
                          for="flexSwitchCheckChecked"
                          >Hoạt động</label
                        >
                        <form:checkbox
                          class="form-check-input"
                          id="flexSwitchCheckChecked"
                          path="active"
                          value="true"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-12 text-end">
                <!-- <button
                  formaction="${pageContext.request.contextPath}/admin/users/reset-password/${user.username}"
                  class="btn btn-danger btn-lg text-white"
                >
                  Reset mật khẩu
                </button> -->
                <button
                  formaction="${pageContext.request.contextPath}/admin/users/reset-password/${user.username}"
                  type="button"
                  class="btn btn-danger btn-lg text-white"
                  data-bs-toggle="modal"
                  href="#confirmDeleteDialog"
                >
                  Reset mật khẩu
                </button>

                <button
                  formaction="${pageContext.request.contextPath}/admin/users/update/${user.username}"
                  class="btn btn-blue-primary btn-lg"
                >
                  Cập nhật
                </button>
              </div>
            </div>
          </form:form>

          <!-- ========Modal reset mật khẩu -->
          <div
            class="modal fade"
            id="confirmDeleteDialog"
            aria-hidden="true"
            tabindex="-1"
          >
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title text-danger">
                    <i
                      class="fa fa-exclamation-triangle"
                      aria-hidden="true"
                    ></i>
                    Reset mật khẩu
                  </h5>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div class="modal-body text-center">
                  <img
                    class="img-fluid mb-3"
                    width="300px"
                    height="300px"
                    src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-screaming-no.jpg"
                  />
                  <h4>Bạn có chắc chắn muốn reset mật khẩu không?</h4>
                </div>
                <form:form
                  action="${pageContext.request.contextPath}/admin/users/reset-password/${user.username}"
                  modelAttribute="user"
                  method="post"
                >
                  <div class="modal-footer">
                    <div class="row g-3 w-100">
                      <div class="col-6">
                        <button
                          class="btn btn-outline-danger w-100"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        >
                          Không
                        </button>
                      </div>
                      <div class="col-6">
                        <button
                          type="submit"
                          class="btn btn-blue-primary w-100"
                        >
                          Có
                        </button>
                      </div>
                    </div>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
          <div
            class="col-12 mt-3 text-end d-flex justify-content-end align-items-center"
          >
            <span class="fst-italic fs-4 text-black text-uppercase fw-bold mx-3"
              >${message}</span
            >
          </div>
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
