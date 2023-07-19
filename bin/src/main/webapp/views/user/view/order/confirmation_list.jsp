<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html class="no-js" lang="zxx">

    <head>
        <!-- Mobile Specific Meta -->
        <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
        <!-- Favicon-->
        <link
            href="${pageContext.request.contextPath}/views/user/${pageContext.request.contextPath}/views/user/img/fav.png"
            rel="shortcut icon">
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
    </head>

    <body>

        <!-- Start Header Area -->
        <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
        <!-- End Header Area -->

        <!--================Order Details Area =================-->
        <section class="">
            <div class="container" style="margin-top: 200px; margin-bottom: 50px">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="" style="background: rgb(255, 162, 0)">
                            <tr class="text-white">
                                <th scope="col"><span class="" style="font-family: 'Microsoft Sans Serif'">Mã hóa
                                        đơn</span>
                                </th>
                                <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Tổng tiền</span>
                                </th>
                                <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Ngày tạo hóa
                                        đơn</span></th>
                                <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Địa chỉ</span>
                                </th>
                                <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Trạng thái</span>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    1
                                </td>
                                <td>
                                    <p>10000000</p>
                                </td>
                                <td>
                                    <h5>29/5/2003</h5>
                                </td>
                                <td>
                                    <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                                </td>
                                <td>
                                    Đã thanh toán
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    2
                                </td>
                                <td>
                                    <p>10000000</p>
                                </td>
                                <td>
                                    <h5>29/5/2003</h5>
                                </td>
                                <td>
                                    <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                                </td>
                                <td>
                                    Đã thanh toán
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    3
                                </td>
                                <td>
                                    <p>10000000</p>
                                </td>
                                <td>
                                    <h5>29/5/2003</h5>
                                </td>
                                <td>
                                    <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                                </td>
                                <td>
                                    Đã thanh toán
                                </td>
                            </tr>
                            <td>
                                3
                            </td>
                            <td>
                                <p>10000000</p>
                            </td>
                            <td>
                                <h5>29/5/2003</h5>
                            </td>
                            <td>
                                <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                            </td>
                            <td>
                                Đã thanh toán
                            </td>
                            </tr>
                            <td>
                                3
                            </td>
                            <td>
                                <p>10000000</p>
                            </td>
                            <td>
                                <h5>29/5/2003</h5>
                            </td>
                            <td>
                                <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                            </td>
                            <td>
                                Đã thanh toán
                            </td>
                            </tr>
                            <td>
                                3
                            </td>
                            <td>
                                <p>10000000</p>
                            </td>
                            <td>
                                <h5>29/5/2003</h5>
                            </td>
                            <td>
                                <p>141, Xã gì đó, Huyện thanh minh, Cần Thơ</p>
                            </td>
                            <td>
                                Đã thanh toán
                            </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!--===============Pagination Area ===================-->
                <div class="pagination justify-content-center">
                    <a class="prev-arrow" href="#"><i aria-hidden="true" class="fa fa-long-arrow-left"></i></a>
                    <a class="active" href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a class="dot-dot" href="#"><i aria-hidden="true" class="fa fa-ellipsis-h"></i></a>
                    <a href="#">6</a>
                    <a class="next-arrow" href="#"><i aria-hidden="true" class="fa fa-long-arrow-right"></i></a>
                </div>
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