<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html lang="zxx" class="no-js">

		<head>
			<!-- Mobile Specific Meta -->
			<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
			<!-- Favicon-->
			<link rel="shortcut icon"
				href="${pageContext.request.contextPath}/views/user/${pageContext.request.contextPath}/views/user/${pageContext.request.contextPath}/views/user/img/fav.png">
			<!-- Author Meta -->
			<meta name="author" content="CodePixar">
			<!-- Meta Description -->
			<meta name="description" content="">
			<!-- Meta Keyword -->
			<meta name="keywords" content="">
			<!-- meta character set -->
			<meta charset="UTF-8">
			<!-- Site Title -->
			<title>F-MAN</title>

			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />
		</head>

		<body>
			<!-- Start Header Area -->
			<header class="header_area sticky-header">
				<div class="main_menu">
					<nav class="navbar navbar-expand-lg navbar-light main_box">
						<div class="container">
							<!-- Brand and toggle get grouped for better mobile display -->
							<a class="navbar-brand logo_h" href="index.html"><img
									src="${pageContext.request.contextPath}/views/user/img/logo.png" alt=""></a>
							<button class="navbar-toggler" type="button" data-toggle="collapse"
								data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
								aria-expanded="false" aria-label="Toggle navigation">
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
								<ul class="nav navbar-nav menu_nav ml-auto">
									<li class="nav-item active"><a class="nav-link" href="index.html">Trang chủ</a></li>
									<li class="nav-item"><a class="nav-link"
											href="${pageContext.request.contextPath}/views/user/view/page/category.html">Danh
											mục</a></li>
									<li class="nav-item"><a class="nav-link"
											href="${pageContext.request.contextPath}/views/user/view/order/confirmation_list.html">Đơn
											hàng
											của tôi</a></li>

									<li class="nav-item mx-0">
										<a href="${pageContext.request.contextPath}/views/user/view/account/login.html"
											class="nav-link">Đăng nhập</a>
									</li>
									<li>
										<span class="nav-link">/</span>
									</li>
									<li class="nav-item">
										<a href="${pageContext.request.contextPath}/views/user/view/account/registration.html"
											class="nav-link">Đăng ký</a>
									</li>

								</ul>
								<ul class="nav navbar-nav navbar-right">
									<li class="nav-item"><a
											href="${pageContext.request.contextPath}/views/user/view/user/profile.html"
											class="cart"><span class="ti-user"></span></a></li>
									<li class="nav-item"><a
											href="${pageContext.request.contextPath}/views/user/view/cart/cart.html"
											class="cart"><span class="ti-bag"></span></a></li>
									<li class="nav-item">
										<button class="search"><span class="lnr lnr-magnifier"
												id="search"></span></button>
									</li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
				<div class="search_input" id="search_input_box">
					<div class="container">
						<form class="d-flex justify-content-between">
							<input type="text" class="form-control" id="search_input" placeholder="Search Here">
							<button type="submit" class="btn"></button>
							<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
						</form>
					</div>
				</div>
			</header>
			<!-- End Header Area -->

			<!-- start features Area -->
			<section class="features-area section_gap">

			</section>
			<!-- end features Area -->
			<!-- Start brand Area -->

			<section class="brand-area">
				<div class="container">
					<div class="row">
						<a class="col single-img" href="#">
							<img class="img-fluid d-block mx-auto"
								src="${pageContext.request.contextPath}/views/user/img/brand/1.png" alt="">
						</a>
						<a class="col single-img" href="#">
							<img class="img-fluid d-block mx-auto"
								src="${pageContext.request.contextPath}/views/user/img/brand/2.png" alt="">
						</a>
						<a class="col single-img" href="#">
							<img class="img-fluid d-block mx-auto"
								src="${pageContext.request.contextPath}/views/user/img/brand/3.png" alt="">
						</a>
						<a class="col single-img" href="#">
							<img class="img-fluid d-block mx-auto"
								src="${pageContext.request.contextPath}/views/user/img/brand/4.png" alt="">
						</a>
						<a class="col single-img" href="#">
							<img class="img-fluid d-block mx-auto"
								src="${pageContext.request.contextPath}/views/user/img/brand/5.png" alt="">
						</a>
					</div>
				</div>
			</section>
			<!-- End brand Area -->
			<!-- start product Area -->
			<section class="owl-carousel active-product-area section_gap">
				<!-- single product slide -->
				<div class="single-product-slider">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-6 text-center">
								<div class="section-title">
									<h1>Mới nhất</h1>
									<p>Những sản phẩm mới nhất được cập nhật liên tục</p>
								</div>
							</div>
						</div>
						<div class="row">
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p1.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p2.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p3.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">
											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p4.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p5.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p6.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p7.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p8.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- single product slide -->
				<div class="single-product-slider">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-6 text-center">
								<div class="section-title">
									<h1>Giảm giá</h1>
									<p>Những sản phẩm giảm giá với giá hời nhất</p>
								</div>
							</div>
						</div>
						<div class="row">
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p6.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p8.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p3.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p5.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p1.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p4.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p1.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- single product -->
							<div class="col-lg-3 col-md-6">
								<div class="single-product">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/views/user/img/product/p8.jpg" alt="">
									<div class="product-details">
										<h6>addidas New Hammer sole
											for Sports person</h6>
										<div class="price">
											<h6>$150.00</h6>
											<h6 class="l-through">$210.00</h6>
										</div>
										<div class="prd-bottom">

											<a href="" class="social-info">
												<span class="ti-bag"></span>
												<p class="hover-text">add to bag</p>
											</a>

											<a href="" class="social-info">
												<span class="lnr lnr-move"></span>
												<p class="hover-text">view more</p>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- end product Area -->

			<!-- Start Product List -->
			<section>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-6 text-center">
							<div class="section-title">
								<h1>Sản phẩm</h1>
								<p>Những sản phẩm có mặt trên cửa hàng</p>
							</div>
						</div>
					</div>
					<div class="row">
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p1.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p2.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p3.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">
										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p4.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p5.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p6.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p7.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- single product -->
						<div class="col-lg-3 col-md-6">
							<div class="single-product">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/product/p8.jpg" alt="">
								<div class="product-details">
									<h6>addidas New Hammer sole
										for Sports person</h6>
									<div class="price">
										<h6>$150.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
									<div class="prd-bottom">

										<a href="" class="social-info">
											<span class="ti-bag"></span>
											<p class="hover-text">add to bag</p>
										</a>

										<a href="" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- End Product List -->

			<!-- Start Footer Area -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
			<!-- End Footer Area -->


			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
				integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
				crossorigin="anonymous"></script>
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
		</body>

		</html>