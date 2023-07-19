<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <!DOCTYPE html>
        <html lang="zxx" class="no-js">

        <head>
            <!-- Mobile Specific Meta -->
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <!-- Author Meta -->
            <meta name="author" content="CodePixar">
            <!-- Meta Description -->
            <meta name="description" content="">
            <!-- Meta Keyword -->
            <meta name="keywords" content="">
            <!-- meta character set -->
            <meta charset="UTF-8">
            <!-- Site Title -->
            <title>Đổi email</title>

            <!--
		CSS
		============================================= -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
        </head>

        <body>

            <!-- Start Header Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
            <!-- End Header Area -->

            <!-- Start Banner Area -->
            <section class="section_gap">

            </section>
            <!-- End Banner Area -->

            <!--================Login Box Area =================-->
            <section class="login_box_area section_gap">
                <div class="container">
                    <div class="row d-flex mx-auto justify-content-center align-items-center">
                        <div class="col-lg-6">
                            <div class="login_form_inner">
                                <h3>Đổi email</h3>
                                <h5 class="text-danger my-5">${err_message_email}</h5>
                                <form:errors path="newEmail" cssClass="text-danger d-flex" /> <br>
                                <form:form class="row login_form needs-validation" modelAttribute="account"
                                    action="${pageContext.request.contextPath}/auth/change-email" method="post"
                                    id="registerForm" >
                                    <div class="col-md-12 form-group">
                                        <form:input type="email" cssClass="form-control" id="newEmail" name="newEmail"
                                            path="newEmail" placeholder="Nhập email mới của bạn" onfocus="this.placeholder = ''"
                                            required="required" onblur="this.placeholder = 'Email'" />
                                    </div>
                                    <div class="col-md-12 form-group">
                                        <button type="submit" class="primary-btn">Đổi email</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--================End Login Box Area =================-->

            <!-- Start Footer Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
            <!-- End Footer Area -->


            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                crossorigin="anonymous"></script>
            <!--gmaps Js-->
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
            <script src="${pageContext.request.contextPath}/views/user/js/validition.js"></script>
            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />

        </body>

        </html>