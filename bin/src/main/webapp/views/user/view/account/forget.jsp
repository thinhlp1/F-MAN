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
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp" />
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
									<h4>Nhớ lại mật khẩu chưa ?</h4>
									<p>Nhớ thì đăng nhập nè</p>
									<a class="primary-btn"
										href="${pageContext.request.contextPath}/views/user/login.html">Đăng nhập</a>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="login_form_inner">
								<h3>Quên mật khẩu</h3>
								<form class="row login_form" method="post" id="contactForm" novalidate="novalidate">
									<div class="col-md-12 form-group">
										<input type="email" class="form-control" id="email" name="email"
											placeholder="Email của bạn" onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Email của bạn'">
									</div>

									<div class="col-md-12 form-group">
										<button type="submit" value="submit" class="primary-btn">Nhận mã OTP</button>
									</div>

									<div class="col-md-12 form-group mt-5">
										<input type="number" class="form-control" id="confirm-otp" name="confirm-otp"
											placeholder="Nhập mã OTP" onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Nhập mã OTP'">
									</div>

									<div class="col-md-12 form-group">
										<button type="submit" value="submit" class="primary-btn">Xác nhận OTP</button>
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