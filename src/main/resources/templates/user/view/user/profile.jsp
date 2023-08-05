<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!DOCTYPE html>
      <html class="no-js" lang="zxx">


      <head>
        <!-- Mobile Specific Meta -->
        <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
        <!-- Author Meta -->
        <meta content="CodePixar" name="author">
        <!-- Meta Description -->
        <meta content="" name="description">
        <!-- Meta Keyword -->
        <meta content="" name="keywords">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <!-- Site Title -->
        <title>Thông Tin Tài Khoản</title>
        <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
        <style>
          .avatar {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            background-size: cover;
            background-position: center;
          }
        </style>
      </head>

      <body>
        <!-- Start Header Area -->
        <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
        <!-- End Header Area -->

        <section class="section_gap_75"></section>

        <c:if test="${successEmail == true}">
          <!-- Flexbox container for aligning the toasts -->
          <div class="alert alert-success" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            ${showEmail}
          </div>
        </c:if>
        <!--================Profile Details Area =================-->
        <section>
          <div class="container">
            <div class="row">
              <div class="col-12 col-md-3">
                <div class="d-flex align-items-center justify-content-center">

                  <form:form action="${pageContext.request.contextPath}/user/profile/update-image/${user.id}"
                    modelAttribute="user" method="post" enctype="multipart/form-data">
                    <div id="imgDialog" class="col-12 mb-3">
                      <div id="image-box" class="text-center position-relative w-100">
                        <img src="${pageContext.request.contextPath}/views/admin/plugins/images/${user.image}" id="blah"
                          class="img-fluid w-100 rounded-circle" />
                        <i id="icon-upload"
                          class="fa fa-cloud-upload position-absolute top-50 start-50 translate-middle text-white"
                          style="font-size: 1.5rem" aria-hidden="true"></i>
                        <input accept="image/*" type="file" id="imgInp" hidden="hidden" name="photo_file" />
                      </div>
                    </div>
                    <button type="submit" class="btn btn-danger" id="imgButt" hidden="hidden">
                      Update ảnh
                    </button>
                  </form:form>

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
                      document.getElementById("imgButt").click();
                    };
                  </script>
                </div>
              </div>
              <div class="col-12 col-md-9">
                <div class="row">
                  <div class="col-12 col-sm-6 col-md-3">
                    <div style="height: 100px">
                      <p>Người dùng</p>
                      <p style="color: black">${user.name}</p>
                    </div>
                  </div>
                  <div class="col-12 col-sm-6 col-md-3">
                    <div style="height: 100px">
                      <p>Số điện thoại</p>
                      <p style="color: black">${user.numberPhone}</p>
                    </div>
                  </div>
                  <div class="col-12 col-sm-6 col-md-3">
                    <div style="height: 100px">
                      <p>Email:</p>
                      <p style="color: black">${user.email}</p>
                    </div>
                  </div>
                  <div class="col-12 col-sm-6 col-md-3">
                    <div style="height: 100px">
                      <div class="card border-0">
                        <span class="card-title m-0">Địa chỉ mặc định:</span>
                        <p class="card-text text-dark">
                          <c:if test="${listAddressSize == 0}">
                            <span class="text-dark font-weight-bold">Chưa nhập địa chỉ</span>
                          </c:if>
                          <c:if test="${listAddressSize > 0}">
                            Tên:
                            <span class="font-weight-bold text-danger">${address.receiverName}</span>
                            <br />
                            Số điện thoại:
                            <span class="font-weight-bold text-danger">${address.numberPhone}</span>
                            <br />
                            Địa chỉ:
                            <span class="font-weight-bold text-danger">${address.address}</span>
                          </c:if>
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-top: 30px">
                  <button type="button" formaction="${pageContext.request.contextPath}/user/profile/${user.id}"
                    class="btn" id="editProfileButt" data-toggle="modal" data-target="#editModal"
                    style="color: white; background-color: rgb(207, 136, 13)">
                    Thay đổi thông tin
                  </button>
                  <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog" role="document">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="editModalLabel">
                            Chỉnh sửa thông tin
                          </h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <c:if test="${errorIsTrue}">
                          <script>
                            window.onload = function callModal() {
                              document.getElementById("editProfileButt").click();
                            };
                          </script>
                        </c:if>
                        <form:form action="${pageContext.request.contextPath}/user/profile/${user.id}"
                          modelAttribute="user2" method="post">
                          <div class="modal-body">
                            <form:errors path="name" class="text-danger fst-italic fw-bold" />
                            <div class="form-group">
                              <label for="fullName">Họ tên</label>
                              <form:input type="text" class="form-control" id="fullName" placeholder="Nhập họ tên"
                                path="name" />
                            </div>
                            <form:errors path="numberPhone" class="text-danger fst-italic fw-bold" />
                            <div class="form-group">
                              <label for="phoneNumber">Số điện thoại</label>
                              <form:input type="text" class="form-control" id="phoneNumber"
                                placeholder="Nhập số điện thoại" path="numberPhone" />
                            </div>
                            <!-- <div class="form-group">
                          <label for="email">Email</label>
                          <form:input
                            type="email"
                            class="form-control"
                            id="email"
                            placeholder="Nhập email"
                            path="email"
                          />
                        </div> -->

                            <form:input type="text" class="form-control" hidden="hidden" id="phoneNumber"
                              path="password" value="${user.password}" />
                            <input accept="image/*" type="file" id="imgInp" hidden="hidden" name="photo_file" />
                          </div>
                          <div class="modal-footer">
                            <button formaction="${pageContext.request.contextPath}/user/profile/${user.id}"
                              type="button" class="btn btn-secondary" data-dismiss="modal">
                              Đóng
                            </button>
                            <button type="submit" class="btn" style="
                            color: white;
                            background-color: rgb(201, 131, 11);
                          ">
                              Lưu thay đổi
                            </button>
                          </div>
                        </form:form>
                      </div>
                    </div>
                  </div>

                  <a class="btn mx-5" href="${pageContext.request.contextPath}/user/address/all?user-id=${user.id}"
                    style="color: white; background-color: rgb(201, 131, 11)">Sổ địa chỉ</a>
                  <a class="btn mx-5" href="${pageContext.request.contextPath}/auth/change-password"
                    style="color: white; background-color: rgb(201, 131, 11)">Đổi mật khẩu</a>
                  <a class="btn mx-5" href="${pageContext.request.contextPath}/auth/sendComfirmOTPChangeEmail"
                    style="color: white; background-color: rgb(201, 131, 11)">Đổi email</a>
                  <a class="btn btn-danger ml-auto mr-3" href="${pageContext.request.contextPath}/auth/logout">Đăng
                    xuất</a>
                </div>
              </div>
            </div>
          </div>

          <!-- <div class="container my-3">
            <div class="table-responsive">
              <table class="table table-striped table-hover">
                <thead class="" style="background: rgb(255, 162, 0)">
                  <tr class="text-white">
                    <th scope="col"><span>Mã hóa đơn</span></th>
                    <th scope="col"><span>Tổng tiền</span></th>
                    <th scope="col"><span>Ngày tạo hóa đơn</span></th>
                    <th scope="col"><span>Địa chỉ</span></th>
                    <th scope="col"><span>Trạng thái</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>
                      <p>100.000VNĐ</p>
                    </td>
                    <td>
                      <p>12/08/2023</p>
                    </td>
                    <td>
                      <p>
                        ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến
                        Tre
                      </p>
                    </td>
                    <td>Đã nhận</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>
                      <p>100.000VNĐ</p>
                    </td>
                    <td>
                      <p>12/08/2023</p>
                    </td>
                    <td>
                      <p>
                        ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến
                        Tre
                      </p>
                    </td>
                    <td>Đã nhận</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>
                      <p>100.000VNĐ</p>
                    </td>
                    <td>
                      <p>12/08/2023</p>
                    </td>
                    <td>
                      <p>
                        ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến
                        Tre
                      </p>
                    </td>
                    <td>Đã nhận</td>
                  </tr>
                </tbody>
              </table>
            </div>-->
          <!--===============Pagination Area ===================-->
          <!-- <div class="filter-bar d-flex flex-wrap align-items-center justify-content-center">
              <div class="pagination">
                <a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                <a href="#" class="active">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
                <a href="#">6</a>
                <a href="#" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
              </div>
            </div> -->
          <!--===============End Pagination Area ===================-->
          </div>
        </section>
        <!--================End Order Details Area =================-->

        <!-- Start Footer Area -->
        <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
        <!-- End Footer Area -->

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
          integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
          crossorigin="anonymous"></script>
        <!--gmaps Js-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
        <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
      </body>

      </html>