<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
  <head>
    <!-- Mobile Specific Meta -->
    <meta
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
      name="viewport"
    />
    <!-- Favicon-->
    <link
      href="${pageContext.request.contextPath}/views/user/img/fav.png"
      rel="shortcut icon"
    />
    <!-- Author Meta -->
    <meta content="CodePixar" name="author" />
    <!-- Meta Description -->
    <meta content="" name="description" />
    <!-- Meta Keyword -->
    <meta content="" name="keywords" />
    <!-- meta character set -->
    <meta charset="UTF-8" />
    <!-- Site Title -->
    <title>Địa Chỉ</title>

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
        <title>Karma Shop</title>
    <jsp:include
      page="${pageContext.request.contextPath}/views/user/abstract/css.jsp"
    />
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

        <!--================Profile Details Area =================-->
        <section>
          <div class="container my-3">
            <div class="table-responsive">
              <table class="table table-striped table-hover">
                <thead class="" style="background: rgb(255, 162, 0)">
                  <tr class="text-white">
                    <th scope="col"><span>Họ tên</span></th>
                    <th scope="col"><span>Số điện thoại</span></th>
                    <th scope="col"><span>Địa chỉ</span></th>
                    <th scope="col"><span>Trạng thái</span></th>
                    <th scope="col"><span>Hành động</span></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="item" items="${items.content}">
                    <tr>
                      <td>${item.receiverName}</td>
                      <td>
                        <p>${item.numberPhone}</p>
                      </td>

                      <td>
                        <p>${item.address}</p>
                      </td>
                      <td>
                        <p>${item.isDefault == 1 ? 'Mặc định' : 'Ẩn'}</p>
                      </td>

                      <td>
                        <button class="btn" data-toggle="modal" data-target="#editModal${item.id}"
                          style="color: white; background-color: rgb(207, 136, 13)">
                          Chỉnh sửa
                        </button>
                        <div class="modal fade" id="editModal${item.id}" tabindex="-1" role="dialog"
                          aria-labelledby="editModalLabel" aria-hidden="true">
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
                              <form:form
                                action="${pageContext.request.contextPath}/user/address/update/${item.id}?user-id=${item.user.id}"
                                modelAttribute="address" method="post">
                                <div class="modal-body">
                                  <div class="form-group">
                                    <label for="fullName">Họ tên</label>
                                    <form:input type="text" class="form-control" id="fullName"
                                      placeholder="Nhập họ và tên" value="${item.receiverName}" path="receiverName" />
                                  </div>
                                  <div class="form-group">
                                    <label for="phoneNumber">Số điện thoại</label>
                                    <form:input type="text" class="form-control" id="phoneNumber"
                                      placeholder="Nhập số điện thoại" value="${item.numberPhone}" path="numberPhone" />
                                  </div>
                                  <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Địa chỉ</label>
                                    <form:input class="form-control" id="exampleFormControlTextarea1" path="address"
                                      value="${item.address}" placeholder="Nhập địa chỉ" />
                                  </div>
                                </div>

                                <div class="modal-footer">
                                  <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Đóng
                                  </button>
                                  <button
                                    formaction="${pageContext.request.contextPath}/user/address/address-default/${item.id}?user-id=${item.user.id}"
                                    type="submit" class="btn btn-secondary">
                                    Đặt làm mặc định
                                  </button>
                                  <button
                                    formaction="${pageContext.request.contextPath}/user/address/update/${item.id}?user-id=${item.user.id}"
                                    type="submit" class="btn" style="
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

                        <!-- ======Modal xóa============ -->
                        <button class="btn btn-danger" data-toggle="modal" data-target="#delete${item.id}">
                          <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                        <div class="modal fade" id="delete${item.id}" tabindex="-1" role="dialog"
                          aria-labelledby="editModalLabel" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger">
                                  <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                                  XÓA
                                </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body text-center">
                                <img class="img-fluid mb-3" width="300px" height="300px"
                                  src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-screaming-no.jpg" />
                                <h4>Bạn có chắc chắn muốn xóa không?</h4>
                              </div>
                              <form:form
                                action="${pageContext.request.contextPath}/user/address/delete/${item.id}?user-id=${item.user.id}"
                                modelAttribute="address" method="post">
                                <div class="modal-footer">
                                  <div class="row g-3 w-100">
                                    <div class="col-6">
                                      <button class="btn btn-outline-danger w-100" class="btn-close"
                                        data-dismiss="modal" aria-label="Close">
                                        Không
                                      </button>
                                    </div>
                                    <div class="col-6">
                                      <button type="submit" class="btn btn-secondary w-100">
                                        Có
                                      </button>
                                    </div>
                                  </div>
                                </div>
                              </form:form>
                            </div>
                          </div>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="row d-flex justify-content-end my-2 mx-2">
              <button class="btn" id="addAddressButt" data-toggle="modal" data-target="#newModal"
                style="color: white; background-color: rgb(238, 159, 21)">
                Thêm địa chỉ
              </button>
            </div>
          </div>
        </section>
        <!--================End Order Details Area =================-->

        <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="newModalLabel"
          aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Thêm địa chỉ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <c:if test="${errorIsTrue}">
                <script>
                  window.onload = function callModal() {
                    document.getElementById("addAddressButt").click();
                  };
                </script>
              </c:if>
              <form:form action="${pageContext.request.contextPath}/user/address/all?user-id=${userId}"
                modelAttribute="address" method="post">
                <div class="modal-body">
                  <form:errors path="receiverName" class="text-danger fst-italic fw-bold" />
                  <div class="form-group">
                    <label for="fullName">Họ tên</label>
                    <form:input type="text" class="form-control" id="fullName" placeholder="Nhập họ tên"
                      path="receiverName" />
                  </div>
                  <form:errors path="numberPhone" class="text-danger fst-italic fw-bold" />
                  <div class="form-group">
                    <label for="phoneNumber">Số điện thoại</label>
                    <form:input type="text" class="form-control" id="phoneNumber" placeholder="Nhập số điện thoại"
                      path="numberPhone" />
                  </div>
                  <form:errors path="address" class="text-danger fst-italic fw-bold" />
                  <div class="form-group">
                    <label for="address">Địa chỉ</label>
                    <form:textarea class="form-control" id="address" placeholder="Nhập địa chỉ" rows="3" path="address"></form:textarea>
                    
                  </div>
                </div>
                <div class="modal-footer">
                  <button class="btn btn-secondary" data-dismiss="modal">
                    Đóng
                  </button>
                  <button formaction="${pageContext.request.contextPath}/user/address/all?user-id=${userId}" class="btn"
                    style="color: white; background-color: rgb(201, 131, 11)">
                    Thêm mới
                  </button>
                </div>
              </form:form>
            </div>
          </div>
        </div>

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