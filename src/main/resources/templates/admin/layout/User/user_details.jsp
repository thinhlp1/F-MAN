<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>FMan | Product Details</title>
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
          <!--      CODE TRONG DDAAY          -->
          <!--BACK-->
          <div class="back mb-3">
            <a
              class="btn btn-blue-primary"
              href="${pageContext.request.contextPath}/admin/users/"
            >
              <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
              Quay Lại
            </a>
          </div>
          <div class="product-details">
            <div class="product-details-title mb-4">
              <h3 class="fw-bold">Thông tin tài khoản</h3>
            </div>
            <div class="row product-details-content">
              <div class="col-md-4">
                <img
                  src="${pageContext.request.contextPath}/views/admin/plugins/images/${user.image}"
                  class="rounded-circle img-fluid border w-100"
                  alt="Demo"
                />
              </div>

              <div class="col-md-5 offset-1">
                <table class="table table-responsive-sm table-borderless">
                  <tbody class="custom-table-list">
                    <tr>
                      <th>Tên người dùng:</th>
                      <td>${user.name}</td>
                    </tr>
                    <tr>
                      <th>Tên tài khoản:</th>
                      <td>${user.username}</td>
                    </tr>

                    <tr>
                      <th>Số điện thoại:</th>
                      <td>${user.numberPhone}</td>
                    </tr>
                    <tr>
                      <th>Email:</th>
                      <td>${user.email}</td>
                    </tr>
                    <tr>
                      <th>Ngày tạo:</th>
                      <td>${createDate}</td>
                    </tr>
                    <tr>
                      <th>Ngày cập nhật:</th>
                      <td>
                        ${user.updateAt == null ? 'Chưa có ngày cập nhật' :
                        updateDate }
                      </td>
                    </tr>
                    <tr>
                      <th>Vai trò:</th>
                      <td>${user.roleId}</td>
                    </tr>
                    <tr>
                      <th>Hoạt động:</th>
                      <td>Đang hoạt động</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
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
