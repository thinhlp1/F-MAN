<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>4Man | Danh Mục</title>

    <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp"/>
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

<!-- End Banner Area -->
<div class="container mt-5 section_gap">
    <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
            <div class="sidebar-categories">
                <div class="head">Loại giày</div>
                <ul class="main-categories">
                    <c:forEach var="category" varStatus="i" items="${list_productType}">
                        <c:if test="${category.products.size() > 0}">
                            <li class="main-nav-list">
                                <a class="${category.id.equalsIgnoreCase(productTypeId) ? 'text-warning' : ''}"
                                   href="${pageContext.request.contextPath}/category/?show=${show}&direction=${direction}&p=${p}&productTypeId=${category.id}">
                                    <span class="lnr lnr-arrow-right"></span>${category.name}
                                    <span class="number">(${category.products.size()})</span></a>
                            </li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>
            <div class="sidebar-filter mt-50">
                <div class="top-filter-head">Lọc sản phẩm</div>
                <form action="${pageContext.request.contextPath}/category/fillter" method="post">
                    <div class="common-filter">
                        <div class="head">Thương hiệu</div>
                        <ul>
                            <c:forEach var="brand" items="${list_brand}">
                                <c:if test="${brand.products.size() > 0}">
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="${brand.id}"
                                                                   name="brandId"
                                                                   value="${brand.id}" ${brandId.equalsIgnoreCase(brand.id) ? 'checked' : ''}><label
                                            for="${brand.id}">${brand.name}<span>(${brand.products.size()})</span></label>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>

                    </div>
                    <div class="common-filter">
                        <div class="head">Giá</div>
                        <%--                        <ul>--%>
                        <%--                            <li class="filter-list"><input class="pixel-radio" type="radio" id="apple"--%>
                        <%--                                                           name="price"--%>
                        <%--                                                           value="500000to1000000" ${price.equalsIgnoreCase("500000to1000000") ? 'checked' : ''}><label--%>
                        <%--                                    for="apple">Từ 500k ---%>
                        <%--                                1tr</label>--%>
                        <%--                            </li>--%>
                        <%--                            <li class="filter-list"><input class="pixel-radio" type="radio" id="asus"--%>
                        <%--                                                           name="price"--%>
                        <%--                                                           value="1000000to2000000" ${price.equalsIgnoreCase("1000000to2000000") ? 'checked' : ''}><label--%>
                        <%--                                    for="asus">Từ 1tr - 2tr</label>--%>
                        <%--                            </li>--%>
                        <%--                            <li class="filter-list"><input class="pixel-radio" type="radio" id="gionee"--%>
                        <%--                                                           name="price"--%>
                        <%--                                                           value="2000000to5000000" ${price.equalsIgnoreCase("2000000to5000000") ? 'checked' : ''}><label--%>
                        <%--                                    for="gionee">Từ 2tr - 5tr</label></li>--%>
                        <%--                            <li class="filter-list"><input class="pixel-radio" type="radio" id="micromax"--%>
                        <%--                                                           name="price"--%>
                        <%--                                                           value="5000000to10000000" ${price.equalsIgnoreCase("5000000to10000000") ? 'checked' : ''}><label--%>
                        <%--                                    for="micromax">Từ 5tr - 10tr</label></li>--%>
                        <%--                        </ul>--%>
                        <div class="form-group mx-4">
                            <label for="min-price">Giá nhỏ nhất:</label>
                            <input type="number" name="min_price"  value="${min_price}" class="form-control" id="min-price">
                        </div>
                        <div class="form-group mx-4">
                            <label for="max-price">Giá lớn nhất:</label>
                            <input type="number" name="max_price" value="${max_price}" class="form-control" id="max-price">
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" value="submit" class="primary-btn rounded-0 mt-3 border-0 w-100">Lọc Sản
                            phẩm
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
            <!-- Start Filter Bar -->
            <div class="filter-bar d-flex flex-wrap align-items-center">
                <div class="sorting">
                    <select onchange="window.location.href=this.value;">
                        <option value="${pageContext.request.contextPath}/category/?show=${show}&direction=desc"
                                selected disabled>Sắp xếp theo
                        </option>
                        <option value="${pageContext.request.contextPath}/category/?show=${show}&direction=asc&productTypeId=${productTypeId}&field=price&keywords=${keywords}" ${direction.equalsIgnoreCase('asc') ? 'selected' : ''}>
                            Giá tăng
                            dần
                        </option>
                        <option value="${pageContext.request.contextPath}/category/?show=${show}&direction=desc&productTypeId=${productTypeId}&field=price&keywords=${keywords}" ${direction.equalsIgnoreCase('desc') ? 'selected' : ''}>
                            Giá giảm
                            dần
                        </option>
                    </select>
                </div>

                <div class="sorting mr-auto">
                    <select onchange="window.location.href=this.value;">
                        <option value="${pageContext.request.contextPath}/category/?show=8&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}" ${show.intValue() == 8 ? 'selected' : ''}>
                            Hiển
                            thị 8 sản phẩm
                        </option>
                        <option value="${pageContext.request.contextPath}/category/?show=16&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}" ${show.intValue() == 16 ? 'selected' : ''}>
                            Hiển
                            thị 16 sản phẩm
                        </option>
                        <option value="${pageContext.request.contextPath}/category/?show=32&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}" ${show.intValue() == 32  ? 'selected' : ''}>
                            Hiển
                            thị 32 sản phẩm
                        </option>
                    </select>
                </div>
                <div class="text-white mr-auto align-middle">Tìm thấy ${items.totalElements} sản phẩm</div>
                <div class="pagination">
                    <c:if test="${items.number >= 3}">
                        <a href="${pageContext.request.contextPath}/category/?show=${show}&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}&p=${items.number - 1 < 0 ? 0 : items.number - 1}"
                           class="prev-arrow"><i class="fa fa-long-arrow-left"
                                                 aria-hidden="true"></i></a>
                    </c:if>
                    <c:if test="${items.totalPages - 1 >= 0}">
                        <c:forEach begin="0" end="${items.totalPages - 1}" var="i">
                            <a class="${i == items.number ? 'active' : ''}"
                               href="${pageContext.request.contextPath}/category/?show=${show}&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}&p=${i}">${i + 1}</a>
                        </c:forEach>
                    </c:if>
                    <%--                    <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>--%>
                    <c:if test="${items.number <= items.totalPages -1}">
                        <a href="${pageContext.request.contextPath}/category/?show=${show}&direction=${direction}&field=${field}&productTypeId=${productTypeId}&keywords=${keywords}&p=${items.number + 1 > items.totalPages - 1 ? items.totalPages - 1 : items.number + 1}"
                           class="next-arrow"><i class="fa fa-long-arrow-right"
                                                 aria-hidden="true"></i></a>
                    </c:if>
                </div>
            </div>
            <!-- End Filter Bar -->
            <!-- Start Best Seller -->
            <section class="lattest-product-area pb-40 category-list">
                <div class="row">
                    <c:forEach var="item" varStatus="i" items="${items.content}">
                        <jsp:include
                                page="${pageContext.request.contextPath}/views/user/view/component/single-product.jsp">
                            <jsp:param name="id" value="${item.id}"/>
                            <jsp:param name="name" value="${item.name}"/>
                            <jsp:param name="image" value="${item.image}"/>
                            <jsp:param name="price" value="${item.getPriceStringVND()}"/>
                            <jsp:param name="desc" value="${item.desc}"/>
                        </jsp:include>
                    </c:forEach>
                    <c:if test="${items.content.size() <= 0}">
                        <div class="col mt-3">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <strong>Không tìm thấy sản phẩm nào!</strong>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                    </c:if>
                </div>
            </section>
            <!-- End Best Seller -->
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
<jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp"/>
</body>

</html>