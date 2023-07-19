<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html lang="zxx" class="no-js">

		<head>
			<!-- Mobile Specific Meta -->
			<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
			<!-- Favicon-->
			<link rel="shortcut icon"
				href="${pageContext.request.contextPath}/views/user/${pageContext.request.contextPath}/views/user/img/fav.png">
			<!-- Author Meta -->
			<meta name="author" content="CodePixar">
			<!-- Meta Description -->
			<meta name="description" content="">
			<!-- Meta Keyword -->
			<meta name="keywords" content="">
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

			<!-- Start Banner Area -->
			<section class="section_gap">

			</section>
			<!-- End Banner Area -->

			<!--================Login Box Area =================-->
			<section class="login_box_area section_gap">
				<div class="container">
					<div class="row">
						<div class="col-lg-6">
							<div class="login_box_img">
								<img class="img-fluid" src="${pageContext.request.contextPath}/views/user/img/login.jpg"
									alt="">
								<div class="hover">
									<h4>Chưa có tài khoản ?</h4>
									<p>Tham gia ngày cùng chúng tôi nào</p>
									<a class="primary-btn" href="../../view/account/registration.html">Tạo tài khoản</a>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="login_form_inner">
								<h3>Đăng nhập</h3>
								<form class="row login_form" method="post" id="contactForm" novalidate="novalidate">
									<div class="col-md-12 form-group">
										<input type="text" class="form-control" id="username" name="username"
											placeholder="Username" onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Username'">
									</div>
									<div class="col-md-12 form-group">
										<input type="password" class="form-control" id="password" name="password"
											placeholder="Password" onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Password'">
									</div>
									<div class="col-md-12 form-group">
										<div class="creat_account">
											<input type="checkbox" id="f-option2" name="selector">
											<label for="f-option2">Duy trì đăng nhập</label>
										</div>
									</div>
									<div class="col-md-12 form-group">
										<button type="submit" value="submit" class="primary-btn">Đăng nhập</button>
										<a href="../../view/account/forget.html">Quên mật khẩu?</a>
									</div>
								</form>
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
			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
		</body>

		</html>