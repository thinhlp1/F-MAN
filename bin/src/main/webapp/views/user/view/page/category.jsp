<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html lang="zxx" class="no-js">

		<head>
			<!-- Mobile Specific Meta -->
			<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
			<!-- Favicon-->
			<link rel="shortcut icon" href="${pageContext.request.contextPath}/views/user/img/fav.png">
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
			<style>
				.size-filter li {
					border: 1px solid #ccc;
					padding: 3px 5px;
					margin: 5px 5px;
					padding-left: 5px !important;
				}
			</style>

		</head>

		<body id="category">

			<!-- Start Header Area -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
			<!-- End Header Area -->

			<!-- Start Banner Area -->
			<section class="section_gap">

			</section>
			<!-- End Banner Area -->
			<div class="container section_gap">
				<div class="row">
					<div class="col-xl-3 col-lg-4 col-md-5">
						<div class="sidebar-categories">
							<div class="head">Loại giày</div>
							<ul class="main-categories">
								<li class="main-nav-list"><a data-toggle="collapse" href="#fruitsVegetable"
										aria-expanded="false" aria-controls="fruitsVegetable"><span
											class="lnr lnr-arrow-right"></span>Giày thể dục<span
											class="number">(53)</span></a>

								</li>
								<li class="main-nav-list"><a data-toggle="collapse" href="#fruitsVegetable"
										aria-expanded="false" aria-controls="fruitsVegetable"><span
											class="lnr lnr-arrow-right"></span>Giày thể thao<span
											class="number">(53)</span></a>

								</li>
								<li class="main-nav-list"><a data-toggle="collapse" href="#fruitsVegetable"
										aria-expanded="false" aria-controls="fruitsVegetable"><span
											class="lnr lnr-arrow-right"></span>Giày tây<span
											class="number">(53)</span></a>

								</li>
								<li class="main-nav-list"><a data-toggle="collapse" href="#fruitsVegetable"
										aria-expanded="false" aria-controls="fruitsVegetable"><span
											class="lnr lnr-arrow-right"></span>Giày Lười<span
											class="number">(53)</span></a>

								</li>


							</ul>
						</div>
						<div class="sidebar-filter mt-50">
							<div class="top-filter-head">Lọc sản phẩm</div>
							<div class="common-filter">
								<div class="head">Thương hiệu</div>
								<form action="#">
									<ul>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="apple"
												name="brand"><label for="apple">Nike<span>(29)</span></label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="asus"
												name="brand"><label for="asus">Sneaker<span>(29)</span></label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="gionee"
												name="brand"><label for="gionee">Adidas</label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="micromax"
												name="brand"><label for="micromax">Puma</label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung"
												name="brand"><label for="samsung">Khác</label></li>
									</ul>
								</form>
							</div>
							<div class="common-filter size-filter">
								<div class="head">Size giày</div>
								<form action="#">
									<ul class="list-unstyled d-flex flex-wrap">
										<li class="filter-list">
											<input class="pixel-radio" type="radio" id="" name="color">
											<label for="black">40</label>
										</li>
										<li class="filter-list">
											<input class="pixel-radio" type="radio" id="" name="color">
											<label for="balckleather">39</label>
										</li>
										<li class="filter-list">
											<input class="pixel-radio" type="radio" id="" name="color">
											<label for="blackred">38</label>
										</li>
										<li class="filter-list">
											<input class="pixel-radio" type="radio" id="" name="color">
											<label for="gold">37</label>
										</li>
										<li class="filter-list">
											<input class="pixel-radio" type="radio" id="" name="color">
											<label for="spacegrey">38</label>
										</li>
									</ul>
								</form>
							</div>
							<div class="common-filter">
								<div class="head">Giá</div>
								<form action="#">
									<ul>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="apple"
												name="brand"><label for="apple">Từ 500k - 1tr<span>(29)</span></label>
										</li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="asus"
												name="brand"><label for="asus">Từ 1tr - 2tr<span>(29)</span></label>
										</li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="gionee"
												name="brand"><label for="gionee">Từ 2tr - 5tr</label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="micromax"
												name="brand"><label for="micromax">Từ 5tr - 10tr</label></li>
										<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung"
												name="brand"><label for="samsung">Từ 5tr trở lên</label></li>
									</ul>
								</form>
							</div>
						</div>
					</div>
					<div class="col-xl-9 col-lg-8 col-md-7">
						<!-- Start Filter Bar -->
						<div class="filter-bar d-flex flex-wrap align-items-center">
							<div class="sorting">
								<select>
									<option value="1">Mặc định</option>
									<option value="1">Tăng dần</option>
									<option value="1">Giảm dần</option>
								</select>
							</div>
							<div class="sorting mr-auto">
								<select>
									<option value="1">Show 8</option>
									<option value="1">Show 16</option>
									<option value="1">Show 32</option>
								</select>
							</div>
							<div class="pagination">
								<a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left"
										aria-hidden="true"></i></a>
								<a href="#" class="active">1</a>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
								<a href="#">6</a>
								<a href="#" class="next-arrow"><i class="fa fa-long-arrow-right"
										aria-hidden="true"></i></a>
							</div>
						</div>
						<!-- End Filter Bar -->
						<!-- Start Best Seller -->
						<section class="lattest-product-area pb-40 category-list">
							<div class="row">
								<!-- single product -->
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p1.jpg"
											alt="">
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
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p2.jpg"
											alt="">
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
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p3.jpg"
											alt="">
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
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p4.jpg"
											alt="">
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
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p5.jpg"
											alt="">
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
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/views/user/img/product/p6.jpg"
											alt="">
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
						</section>
						<!-- End Best Seller -->
						<!-- Start Filter Bar -->
						<div class="filter-bar d-flex flex-wrap align-items-center">

							<div class="sorting mr-auto">
								<select>
									<option value="1">Show 8</option>
									<option value="1">Show 16</option>
									<option value="1">Show 32</option>
								</select>
							</div>
							<div class="pagination">
								<a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left"
										aria-hidden="true"></i></a>
								<a href="#" class="active">1</a>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
								<a href="#">6</a>
								<a href="#" class="next-arrow"><i class="fa fa-long-arrow-right"
										aria-hidden="true"></i></a>
							</div>
						</div>
						<!-- End Filter Bar -->
					</div>
				</div>
			</div>

			<!-- Start Footer Area -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
			<!-- End Footer Area -->

			<!-- Modal Quick Product View -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="container relative">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="product-quick-view">
							<div class="row align-items-center">
								<div class="col-lg-6">
									<div class="quick-view-carousel">
										<div class="item" style="background: url(img/organic-food/q1.jpg);">

										</div>
										<div class="item" style="background: url(img/organic-food/q1.jpg);">

										</div>
										<div class="item" style="background: url(img/organic-food/q1.jpg);">

										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="quick-view-content">
										<div class="top">
											<h3 class="head">Mill Oil 1000W Heater, White</h3>
											<div class="price d-flex align-items-center"><span
													class="lnr lnr-tag"></span>
												<span class="ml-10">$149.99</span>
											</div>
											<div class="category">Category: <span>Household</span></div>
											<div class="available">Availibility: <span>In Stock</span></div>
										</div>
										<div class="middle">
											<p class="content">Mill Oil is an innovative oil filled radiator with the
												most
												modern technology. If you are
												looking for something that can make your interior look awesome, and at
												the
												same
												time give you the pleasant
												warm feeling during the winter.</p>
											<a href="#" class="view-full">View full Details <span
													class="lnr lnr-arrow-right"></span></a>
										</div>
										<div class="bottom">
											<div class="color-picker d-flex align-items-center">Color:
												<span class="single-pick"></span>
												<span class="single-pick"></span>
												<span class="single-pick"></span>
												<span class="single-pick"></span>
												<span class="single-pick"></span>
											</div>
											<div class="quantity-container d-flex align-items-center mt-15">
												Quantity:
												<input type="text" class="quantity-amount ml-15" value="1" />
												<div class="arrow-btn d-inline-flex flex-column">
													<button class="increase arrow" type="button"
														title="Increase Quantity"><span
															class="lnr lnr-chevron-up"></span></button>
													<button class="decrease arrow" type="button"
														title="Decrease Quantity"><span
															class="lnr lnr-chevron-down"></span></button>
												</div>

											</div>
											<div class="d-flex mt-20">
												<a href="#" class="view-btn color-2"><span>Add to Cart</span></a>
												<a href="#" class="like-btn"><span class="lnr lnr-layers"></span></a>
												<a href="#" class="like-btn"><span class="lnr lnr-heart"></span></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
				integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
				crossorigin="anonymous"></script>
			<!--gmaps Js-->
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
			<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
		</body>

		</html>