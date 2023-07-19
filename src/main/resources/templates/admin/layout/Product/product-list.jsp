<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Products</title>
        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/css.jsp" />
    </head>

    <body>
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->

        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
            data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <!-- ============================================================== -->
            <!-- Header -->
            <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/header.jsp" />
            <!-- Header -->
            <!-- Sidebar -->
            <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/sidebar.jsp" />
            <!-- Sidebar -->

            <div id="container" class="page-wrapper">
                <!-- Taskbar -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp" />
                <!-- Taskbar -->

                <div class="container-fluid">
                    <!--      CODE TRONG DDAAY          -->
                    <div class="d-flex justify-content-between mb-3">
                        <div class="sort-fillter align-items-center">
                            <span class="dropdown">
                                <button class="btn bg-transparent border shadow dropdown-toggle" type="button" id="sort"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                                    Sắp xếp theo
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="sort">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products/?field=id&p=${items.number}">ID</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products/?field=name&p=${items.number}">Tên</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products/?field=price&p=${items.number}">Giá</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products/?field=productType.name&p=${items.number}">Loại</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/products/?field=brand.name&p=${items.number}">Thương hiệu</a></li>
                                </ul>
                            </span>
                            <span>
                                <button id="callfillter" class="btn bg-transparent border shadow" type="button"
                                    data-bs-toggle="modal" data-bs-target="#openFillter">
                                    <i class="fa fa-filter"></i>
                                    Bộ lọc
                                </button>
                                <!-- Modal -->
                                <div class="modal fade" id="openFillter" tabindex="-1" aria-labelledby="fillter"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-xl">
                                    <form action="${pageContext.request.contextPath}/admin/products/" method="POST">
                                  
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title text-uppercase" id="fillter">Bộ Lọc
                                                </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body py-3 px-5">
                                                <div class="row g-3">
                                                    
                                                    <div class="col-3">
                                                        <div class="fill-box">
                                                            <div class="fill-box-title mb-3">
                                                                <h5 class="text-dark fw-bold text-uppercase">Thương Hiệu
                                                                </h5>
                                                            </div>
                                                            <div class="fill-box-content">
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="brand" value="all" id="all-brand" checked>
                                                                    <label class="form-check-label" for="all-brand">
                                                                        Tất cả
                                                                    </label>
                                                                </div>
                                                                
                                                                <c:forEach var="item" items="${list_brands}">
                                                                	 <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                     value="${item.id}" name="brand" id="brand${item.id}">
                                                                    <label class="form-check-label" for="brand${item.id}">
                                                                     ${item.name}
                                                                    </label>
                                                                </div>
                                                                </c:forEach>
                                                               
                                                           
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-3">
                                                        <div class="fill-box">
                                                            <div class="fill-box-title mb-3">
                                                                <h5 class="text-dark fw-bold text-uppercase">Giá</h5>
                                                            </div>
                                                            <div class="fill-box-content">
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="price" value="all" id="all-price" checked>
                                                                    <label class="form-check-label" for="all-price">
                                                                        Tất cả
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="price" value="0"  id="low-price">
                                                                    <label class="form-check-label" for="low-price">
                                                                        Từ 0 - 500.000 vnđ
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="price" value="500" id="medium-price">
                                                                    <label class="form-check-label" for="medium-price">
                                                                        Từ 500.000 - 1.000.000 vnđ
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="price" value="1000"  id="large-price">
                                                                    <label class="form-check-label" for="large-price">
                                                                        Từ 1.000.000 - 2.000.000 vnđ
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio"
                                                                        name="price" value="2000" id="extralarge-price">
                                                                    <label class="form-check-label"
                                                                        for="extralarge-price">
                                                                        Trên 2.000.000 vnđ
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-3">
                                                        <div class="fill-box">
                                                            <div class="fill-box-title mb-3">
                                                                <h5 class="text-dark fw-bold text-uppercase">Size</h5>
                                                            </div>
                                                            <div class="fill-box-content">
                                                                <div class="row g-0">
                                                                <c:forEach var="item" items="${list_sizes}">
                                                                    <div class="col-3">
                                                                        <div class="form-check">
                                                                            <input class="form-check-input"
                                                                                type="checkbox" value="${item.id}" id="#${item.id}" name="sizeProuct">
                                                                            <label class="form-check-label"
                                                                                for="size33">
                                                                                ${item.size}
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                    </c:forEach>
                                                                   
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                           
                                            
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-outline-dark">Bỏ
                                                    lọc</button>
                                                <button type="submit" id="clickButton"  class="btn btn-viva">Lọc</button>
                                            </div>
                                        </div>
                                     </form>
                                    </div>
                                    
                                </div>
                            </span>
                        </div>
                        <script>
                                               
                            const clickSize = document.getElementById("clickButton");
                            clickSize.addEventListener('click' ,function() {
                                const sizeProduct = document.getElementsByName("sizeProuct");
                                
                                var sizeArray = [];
                                
                                 for (let index = 0; index < sizeProduct.length; index++) {
                                    if (sizeProduct[index].checked)
                                     sizeArray.push(sizeProduct[index].value) 
                                 }

                                 if (sizeArray.length == 0) {
                                    sizeArray.push("notsize");
                                 }

                                //  for (let index = 0; index < sizeArray.length; index++) {
                                  
                                //           console.log(sizeArray[index])
                                //  }
                                 $.ajax({
                                                url: "${pageContext.request.contextPath}/admin/products/listfilter",
                                                type: "POST",
                                                contentType: "application/json",
                                                data: JSON.stringify(sizeArray),
                                                success: function (response) {
                                                    // Xử lý phản hồi thành công
                                            
                                                },
                                            error: function ( error) {
                                                // Xử lý phản hồi lỗi
                                                console.log("Checkout failed");
                                    
                                            }});
                        
                            })
                        </script>
                        <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/products/create">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>
                            Thêm
                        </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead class="table-header-custom">
                                <tr>
                                    <th scope="col">STT</th>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Loại</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Thương hiệu</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" varStatus="i" items="${items.content}">
                                    <tr>
                                        <th scope="row">${i.count}</th>
                                        <td>${item.id}</td>
                                        <td>${item.name}</td>
                                        <td>${item.productType.name}</td>
                                        <td>${item.price}</td>
                                        <td>${item.brand.name}</td>
                                        <td>${item.active == 1 ?
                                                '<span class="badge bg-success">Hoạt động</span>' :
                                                '<span class="badge bg-danger">Vô hiệu hóa</span>'
                                                }
                                        </td>
                                        <td>
                                            <a class="text-dark"
                                               href="${pageContext.request.contextPath}/admin/products/update-form/${item.id}">
                                                <button class="border-0 bg-transparent">
                                                    <i class="fa fa-pencil" aria-hidden="true"></i>
                                                </button>
                                            </a>
                                            <a class="text-dark"
                                               href="${pageContext.request.contextPath}/admin/products/details/${item.id}">
                                                <button class="border-0 bg-transparent">
                                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                                </button>
                                            </a>
                                            <button type="button" class="border-0 bg-transparent" data-bs-toggle="modal"
                                                    data-bs-target="#PRODUCT${item.id}"><i class="fa fa-trash"
                                                                                   aria-hidden="true"></i>
                                            </button>
                                            <div class="modal fade" id="PRODUCT${item.id}" aria-hidden="true"
                                                 tabindex="-1">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title text-danger">
                                                                <i class="fa fa-exclamation-triangle"
                                                                   aria-hidden="true"></i>
                                                                XÓA
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>

                                                        </div>
                                                        <div class="modal-body text-center">
                                                            <img class="img-fluid mb-3" width="300px" height="300px"
                                                                 src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-screaming-no.jpg">
                                                            <h4>Bạn có chắc chắc muốn sản phẩm <span class="text-danger fw-bold">${item.name.toUpperCase()}</span> ?</h4>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <div class="row g-3 w-100">
                                                                <div class="col-6">
                                                                    <button class="btn btn-outline-danger w-100"
                                                                            class="btn-close" data-bs-dismiss="modal"
                                                                            aria-label="Close">Không
                                                                    </button>
                                                                </div>
                                                                <div class="col-6">
                                                                <a href="${pageContext.request.contextPath}/admin/products/delete/${item.id}" class="btn btn-blue-primary w-100" >Có</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                       <c:choose>
                       	  <c:when test="${checkItems == true}">
                        <div class="d-flex justify-content-center pagination-custom">
                            <ul class="pagination">
                                <c:if test="${items.number > 0}">
                                    <li class="page-item">
                                        <a class="page-link fs-4" href="${pageContext.request.contextPath}/admin/products/?field=${field}&p=${items.number - 1}">
                                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="0" end="${items.totalPages - 1}">
                                    <c:if test="${i >= items.number - 2 && i <= items.number + 2}">
                                        <li class="page-item ${i == items.number ? 'active' : ''}">
                                            <a class="page-link fs-4" href="${pageContext.request.contextPath}/admin/products/?field=${field}&p=${i}">
                                                    ${i + 1}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${items.number < items.totalPages - 1}">
                                    <li class="page-item">
                                        <a class="page-link fs-4" href="${pageContext.request.contextPath}/admin/products/?field=${field}&p=${items.number + 1}">
                                            <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        	  </c:when>
                        	  <c:otherwise>
                        	  
                        	  </c:otherwise>
                       </c:choose> 
                    </div>

                </div>
                <!-- Footer -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                <!-- Footer -->
            </div>
        </div>


        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />

    </body>

    </html>