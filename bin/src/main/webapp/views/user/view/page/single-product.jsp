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

		<body>

			<!-- Start Header Area -->
			<jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
			<!-- End Header Area -->

			<!-- Start Banner Area -->
			<section class="section_gap">

			</section>
			<!-- End Banner Area -->

			<!--================Single Product Area =================-->
			<div class="product_image_area">
				<div class="container">
					<div class="row s_product_inner">
						<div class="col-lg-6">
							<div class="single-prd-item">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}/views/user/img/category/s-p1.jpg" alt="">
							</div>
						</div>
						<div class="col-lg-5 offset-lg-1">
							<div class="s_product_text">
								<h3>Faded SkyBlu Denim Jeans</h3>
								<h2>$149.99</h2>
								<ul class="list">
									<li><a class="active" href="#"><span>Loại giày</span> : Giày thế thao</a></li>
									<li><a href="#"><span>Thương hiệu</span> : Hong biết</a></li>
									<li><a href="#"><span>Số lượng còn</span> : 12</a></li>
								</ul>
								<p>Mill Oil is an innovative oil filled radiator with the most modern technology. If you
									are
									looking for
									something that can make your interior look awesome, and at the same time give you
									the
									pleasant warm feeling
									during the winter.</p>

								<div class="common-filter size-filter mb-3">
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

								<div class="product_count">
									<label for="qty">Quantity:</label>
									<input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:"
										class="input-text qty">
									<button
										onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
										class="increase items-count" type="button"><i
											class="lnr lnr-chevron-up"></i></button>
									<button
										onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
										class="reduced items-count" type="button"><i
											class="lnr lnr-chevron-down"></i></button>
								</div>

								<div class="card_area d-flex align-items-center">
									<a class="primary-btn" href="#">Thêm vào giỏ hàng</a>
									<a class="primary-btn" href="#">Mua luôn</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--================End Single Product Area =================-->

			<!--================Product Description Area =================-->
			<section class="product_description_area">
				<div class="container">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item ">
							<a class="nav-link active	" id="home-tab" data-toggle="tab" href="#home" role="tab"
								aria-controls="home" aria-selected="true">Mô tả</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
								aria-controls="profile" aria-selected="false">Thông số</a>
						</li>


					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
							<p>Beryl Cook is one of Britain’s most talented and amusing artists .Beryl’s pictures
								feature
								women
								of all shapes
								and sizes enjoying themselves .Born between the two world wars, Beryl Cook eventually
								left
								Kendrick School in
								Reading at the age of 15, where she went to secretarial school and then into an
								insurance
								office. After moving to
								London and then Hampton, she eventually married her next door neighbour from Reading,
								John
								Cook.
								He was an
								officer in the Merchant Navy and after he left the sea in 1956, they bought a pub for a
								year
								before John took a
								job in Southern Rhodesia with a motor company. Beryl bought their young son a box of
								watercolours, and when
								showing him how to use it, she decided that she herself quite enjoyed painting. John
								subsequently bought her a
								child’s painting set for her birthday and it was with this that she produced her first
								significant work, a
								half-length portrait of a dark-skinned lady with a vacant expression and large drooping
								breasts.
								It was aptly
								named ‘Hangover’ by Beryl’s husband and</p>
							<p>It is often frustrating to attempt to plan meals that are designed for one. Despite this
								fact, we
								are seeing
								more and more recipe books and Internet websites that are dedicated to the act of
								cooking
								for
								one. Divorce and
								the death of spouses or grown children leaving for college are all reasons that someone
								accustomed to cooking for
								more than one would suddenly need to learn how to adjust all the cooking practices
								utilized
								before into a
								streamlined plan of cooking that is more efficient for one person creating less</p>
						</div>
						<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							<div class="table-responsive">
								<table class="table">
									<tbody>
										<tr>
											<td>
												<h5>Width</h5>
											</td>
											<td>
												<h5>128mm</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Height</h5>
											</td>
											<td>
												<h5>508mm</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Depth</h5>
											</td>
											<td>
												<h5>85mm</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Weight</h5>
											</td>
											<td>
												<h5>52gm</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Quality checking</h5>
											</td>
											<td>
												<h5>yes</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Freshness Duration</h5>
											</td>
											<td>
												<h5>03days</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>When packeting</h5>
											</td>
											<td>
												<h5>Without touch of hand</h5>
											</td>
										</tr>
										<tr>
											<td>
												<h5>Each Box contains</h5>
											</td>
											<td>
												<h5>60pcs</h5>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
			</section>
			<!--================End Product Description Area =================-->


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