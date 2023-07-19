<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html class="no-js" lang="zxx">

    <head>
        <!-- Mobile Specific Meta -->
        <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
        <!-- Favicon-->
        <link href="${pageContext.request.contextPath}/views/user/img/fav.png" rel="shortcut icon">
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

        <section class="section_gap_75">

        </section>

        <!--================Profile Details Area =================-->
        <section>
            <div class="container my-3">
                <div class="table-responsive text-center">
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
                            <tr>
                                <td>
                                    Lê Phước Thịnh
                                </td>
                                <td>
                                    <p>0334831013</p>
                                </td>

                                <td>
                                    <p>ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến Tre</p>
                                </td>
                                <td>
                                    <p>Mặc định</p>
                                </td>

                                <td>
                                    <button class="btn" data-toggle="modal" data-target="#editModal"
                                        style="color: white; background-color: rgb(207, 136, 13);">Chỉnh
                                        sửa</button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                <div class="row d-flex justify-content-end my-2 mx-2">
                    <button class="btn" data-toggle="modal" data-target="#newModal"
                        style="color: white; background-color: rgb(238, 159, 21);">Thêm địa chỉ</button>
                </div>

            </div>
        </section>
        <!--================End Order Details Area =================-->


        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Chỉnh sửa thông tin</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="fullName">Họ tên</label>
                                <input type="text" class="form-control" id="fullName" placeholder="Nhập họ tên">
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber">Số điện thoại</label>
                                <input type="text" class="form-control" id="phoneNumber"
                                    placeholder="Nhập số điện thoại">
                            </div>
                            <div class="form-group">
                                <label for="address">Địa chỉ</label>
                                <textarea class="form-control" id="address" placeholder="Nhập địa chỉ"
                                    rows="3"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-secondary"> Đặt làm mặc định</button>
                        <button type="button" class="btn" style="color: white; background-color: rgb(201, 131, 11);">
                            Lưu
                            thay đổi</button>
                    </div>
                </div>
            </div>
        </div>

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
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="fullName">Họ tên</label>
                                <input type="text" class="form-control" id="fullName" placeholder="Nhập họ tên">
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber">Số điện thoại</label>
                                <input type="text" class="form-control" id="phoneNumber"
                                    placeholder="Nhập số điện thoại">
                            </div>
                            <div class="form-group">
                                <label for="address">Địa chỉ</label>
                                <textarea class="form-control" id="address" placeholder="Nhập địa chỉ"
                                    rows="3"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn" style="color: white; background-color: rgb(201, 131, 11);">
                            Thêm
                            mới</button>
                    </div>
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