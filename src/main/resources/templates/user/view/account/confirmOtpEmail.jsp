<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
			<title>Xác nhận OTP</title>

			<!--
		CSS
		============================================= -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
		</head>

		<body onload="countdown()">

			<!-- Start Header Area -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
			<!-- End Header Area -->

			<!-- End Banner Area -->

			<!--================Login Box Area =================-->
			<section class="login_box_area section_gap">
				<div class="container">
					<div class="row d-flex mx-auto justify-content-center align-items-center">

						<div class="col-lg-6">
							<div class="login_form_inner">
								<h3>Xác nhận OTP</h3>
								<h5 class="text-danger my-5">${err_message_email}</h5>
								<h5 class="text-primary">Kiểm tra email ${email} của bạn </h5>
								<form class="row login_form" action="${pageContext.request.contextPath}/auth/confirmOTPChangeEmail"
									 method="post" id="confirmOTP" novalidate="novalidate">
									<div class="col-md-12 form-group">
										<input type="number" class="form-control text-center" maxlength="6"  required="required"
											minlength="6" id="otp" name="otp" placeholder="Nhập mã OTP"
											onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nhập mã OTP'">
									</div>

									<div class="col-md-12 form-group">
										<button onclick="submitFormWithOTP()" class="primary-btn">Xác
											nhận</button>

										<p id="resend" class="my-4">Gửi lại sau: <span id="countdown"></span>s</p>
										<p id="reloadButton" style="display: none; cursor: pointer;"
											class="my-4 text-primary" onclick=" location.reload(true);" type="button">
											Gửi lại</p>
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

			<script>
				function countdown() {
					var count = 60;
					var countdownElement = document.getElementById("countdown");
					var reloadButton = document.getElementById("reloadButton");

					// Hiển thị đếm ngược ban đầu
					countdownElement.innerHTML = count;

					// Đếm ngược
					var countdownInterval = setInterval(function () {
						count--;
						countdownElement.innerHTML = count;
						console.log("countdown: " + count);

						// Kiểm tra khi đếm ngược kết thúc
						if (count === 0) {
							console.log("display")
							clearInterval(countdownInterval);
							reloadButton.style.display = "block"; // Hiển thị nút "Reload"
							document.getElementById("resend").style.display = "none";
						}
					}, 1000);
				}
			</script>

			<script>
				function submitFormWithOTP() {
					var otpInput = document.getElementById("otp");
					var otpValue = otpInput.value;

					if (otpValue.length === 6) {
						var form = document.getElementById("confirmOTP");
						var actionUrl = form.getAttribute("action") + "?otp=" + otpValue;
						form.setAttribute("action", actionUrl);
						form.submit();
					} else {
						// Xử lý trường hợp nhập mã OTP không hợp lệ
					}
				}
			</script>



			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
				integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
				crossorigin="anonymous"></script>
			<!--gmaps Js-->
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
			<script src="${pageContext.request.contextPath}/views/user/js/validition.js"></script>
			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />

		</body>

		</html>