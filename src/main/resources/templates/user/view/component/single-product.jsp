<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- single product -->
<div class="col-lg-3 col-md-6">
    <a href="${pageContext.request.contextPath}/product/${param.id}">
        <div class="single-product">
            <img class="img-fluid" style="max-height: 280px"
                 src="${pageContext.request.contextPath}/views/admin/plugins/images/products/${param.image}" alt="${param.name}">
            <div class="product-details">
                <h6>${param.name}</h6>
                <div class="price">
                    <h6>${param.price}</h6>
<%--                    <h6 class="l-through">$210.00</h6>--%>
                </div>

            </div>
        </div>
    </a>
</div>