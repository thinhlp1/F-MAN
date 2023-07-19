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

            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-2">
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="avatar"
                                style="background-image: url(${pageContext.request.contextPath}/views/user/img/blog/author.png);">
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-10">
                        <div class="row">
                            <div class="col-12 col-sm-6 col-md-3">
                                <div style="height: 100px;">
                                    <p>Người dùng</p>
                                    <p style="color: black;">Lê Phước Thịnh</p>
                                </div>
                            </div>
                            <div class="col-12 col-sm-6 col-md-3">
                                <div style="height: 100px;">
                                    <p>Số điện thoại</p>
                                    <p style="color: black;">0334831013</p>

                                </div>
                            </div>
                            <div class="col-12 col-sm-6 col-md-3">
                                <div style="height: 100px;">

                                    <p>Email: </p>
                                    <p style="color: black;">alanthinh3@gmail.com</p>
                                </div>
                            </div>

                            <div class="col-12 col-sm-6 col-md-3">
                                <div style="height: 100px;">
                                    <p>Địa chỉ</p>
                                    <p style="color: black;">ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh
                                        Bến
                                        Tre
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <button class="btn" data-toggle="modal" data-target="#editModal"
                                style="color: white; background-color: rgb(207, 136, 13);">Thay đổi thông
                                tin</button>
                            <a class="btn mx-5" href="../user/user-address.html"
                                style="color: white; background-color: rgb(201, 131, 11);">Sổ địa chỉ</a>
                        </div>
                    </div>
                </div>

            </div>


            <div class="container my-3">
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
                                <td>
                                    1
                                </td>
                                <td>
                                    <p>100.000VNĐ</p>
                                </td>
                                <td>
                                    <p>12/08/2023</p>
                                </td>
                                <td>
                                    <p>ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến Tre</p>
                                </td>
                                <td>
                                    Đã nhận
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    2
                                </td>
                                <td>
                                    <p>100.000VNĐ</p>
                                </td>
                                <td>
                                    <p>12/08/2023</p>
                                </td>
                                <td>
                                    <p>ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến Tre</p>
                                </td>
                                <td>
                                    Đã nhận
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    3
                                </td>
                                <td>
                                    <p>100.000VNĐ</p>
                                </td>
                                <td>
                                    <p>12/08/2023</p>
                                </td>
                                <td>
                                    <p>ấp Bến Xoài, xã Nhuận Phú Tân, huyện Mỏ Cày Bắc, tỉnh Bến Tre</p>
                                </td>
                                <td>
                                    Đã nhận
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!--===============Pagination Area ===================-->
                <div class="filter-bar d-flex flex-wrap align-items-center justify-content-center">

                    <div class="pagination">
                        <a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                        <a href="#" class="active">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
                        <a href="#">6</a>
                        <a href="#" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                    </div>
                </div>
                <!--===============End Pagination Area ===================-->
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
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Nhập email">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn" style="color: white; background-color: rgb(201, 131, 11);">
                            Lưu
                            thay đổi</button>
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