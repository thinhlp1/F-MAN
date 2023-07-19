<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html dir="ltr" lang="en">

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <!-- Tell the browser to be responsive to screen width -->
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>FMan | Discount</title>

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
                        <jsp:include
                            page="${pageContext.request.contextPath}/views/admin/layout/Component/taskbar.jsp" />
                        <!-- Taskbar -->

                        <div class="container-fluid">
                            <!--      CODE TRONG DDAAY          -->
                            <div class="d-flex justify-content-between mb-3">
                                <div class="sort-fillter align-items-center">
                                    <span class="dropdown">
                                        <!-- <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                            id="sort" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                                            Sắp xếp theo
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="sort">
                                            <li><a class="dropdown-item"
                                                    href="${pageContext.request.contextPath}/admin/discounts/?field=name&p=${items.number}&state=${selectedState}">Tên</a>
                                            </li>
                                            <li><a class="dropdown-item"
                                                    href="${pageContext.request.contextPath}/admin/discounts/?field=salePercent&p=${items.number}&state=${selectedState}">Phần
                                                    trăm giảm</a></li>
                                            <li><a class="dropdown-item"
                                                    href="${pageContext.request.contextPath}/admin/discounts/?field=createAt&p=${items.number}&state=${selectedState}">Ngày
                                                    tạo</a></li>
                                        </ul> -->
                                        <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                            id="sort" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                                            Sắp xếp theo
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="sort">
                                            <li>
                                                <a class="dropdown-item" href="javascript:void(0);" data-field="name"
                                                    onclick="changeSort('name', '${selectedState}' ,'${items.number}')">Tên</a>
                                            </li>
                                            <li>
                                                <a class="dropdown-item" href="javascript:void(0);"
                                                    data-field="salePercent"
                                                    onclick="changeSort('salePercent', '${selectedState}','${items.number}')">Phần trăm
                                                    giảm</a>
                                            </li>
                                            <li>
                                                <a class="dropdown-item" href="javascript:void(0);"
                                                    data-field="createAt"
                                                    onclick="changeSort('createAt', '${selectedState}','${items.number}')">Ngày tạo</a>
                                            </li>
                                        </ul>
                                    </span>
                                    <span>
                                        <button id="callfillter" class="btn bg-transparent border shadow" type="button"
                                            data-bs-toggle="modal" data-bs-target="#openFillter">
                                            <i class="fa fa-filter"></i>
                                            Bộ lọc
                                        </button>
                                        <form id="filterForm"
                                            action="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=${items.number}&state=${state}"
                                            method="post">
                                            <!-- Modal -->
                                            <div class="modal fade" id="openFillter" tabindex="-1"
                                                aria-labelledby="fillter" aria-hidden="true">
                                                <div class="modal-dialog modal-xl">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title text-uppercase" id="fillter">Bộ Lọc
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body py-3 px-5">
                                                            <div class="row g-3">
                                                                <div class="col-3">
                                                                    <div class="fill-box">
                                                                        <div class="fill-box-title mb-3">
                                                                            <h5
                                                                                class="text-dark fw-bold text-uppercase">
                                                                                Tình trạng
                                                                            </h5>
                                                                        </div>
                                                                        <div class="fill-box-content">
                                                                            <div class="form-check">
                                                                                <input class="form-check-input"
                                                                                    type="radio" name="state"
                                                                                    value="upcommingVoucher"
                                                                                    id="upcommingVoucher">
                                                                                <label class="form-check-label"
                                                                                    for="upcommingVoucher">
                                                                                    Voucher chưa đến ngày sử dụng
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input"
                                                                                    type="radio" name="state"
                                                                                    value="outDatedVoucher"
                                                                                    id="outDatedVoucher">

                                                                                <label class="form-check-label"
                                                                                    for="outDatedVoucher">
                                                                                    Voucher hết hạn sử dụng
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- <div class="col-3">
                                                                    <div class="fill-box">
                                                                        <div class="fill-box-title mb-3">
                                                                            <h5 class="text-dark fw-bold text-uppercase">
                                                                                Thương Hiệu
                                                                            </h5>
                                                                        </div>
                                                                        <div class="fill-box-content">
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="brand" id="all-brand" checked>
                                                                                <label class="form-check-label"
                                                                                    for="all-brand">
                                                                                    Tất cả
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="brand" id="nike">
                                                                                <label class="form-check-label" for="nike">
                                                                                    Nike
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="brand" id="adidas">
                                                                                <label class="form-check-label"
                                                                                    for="adidas">
                                                                                    Adidas
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="brand" id="vans">
                                                                                <label class="form-check-label" for="vans">
                                                                                    Vans
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="brand" id="reebok">
                                                                                <label class="form-check-label"
                                                                                    for="reebok">
                                                                                    Reebok
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-3">
                                                                    <div class="fill-box">
                                                                        <div class="fill-box-title mb-3">
                                                                            <h5 class="text-dark fw-bold text-uppercase">Giá
                                                                            </h5>
                                                                        </div>
                                                                        <div class="fill-box-content">
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="price" id="all-price" checked>
                                                                                <label class="form-check-label"
                                                                                    for="all-price">
                                                                                    Tất cả
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="price" id="low-price">
                                                                                <label class="form-check-label"
                                                                                    for="low-price">
                                                                                    Từ 0 - 500.000 vnđ
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="price" id="medium-price">
                                                                                <label class="form-check-label"
                                                                                    for="medium-price">
                                                                                    Từ 500.000 - 1.000.000 vnđ
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="price" id="large-price">
                                                                                <label class="form-check-label"
                                                                                    for="large-price">
                                                                                    Từ 1.000.000 - 2.000.000 vnđ
                                                                                </label>
                                                                            </div>
                                                                            <div class="form-check">
                                                                                <input class="form-check-input" type="radio"
                                                                                    name="price" id="extralarge-price">
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
                                                                            <h5 class="text-dark fw-bold text-uppercase">
                                                                                Size</h5>
                                                                        </div>
                                                                        <div class="fill-box-content">
                                                                            <div class="row g-0">
                                                                                <div class="col-4">
                                                                                    <div class="form-check">
                                                                                        <input class="form-check-input"
                                                                                            type="checkbox" value=""
                                                                                            id="size33">
                                                                                        <label class="form-check-label"
                                                                                            for="size33">
                                                                                            33
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-4">
                                                                                    <div class="form-check">
                                                                                        <input class="form-check-input"
                                                                                            type="checkbox" value=""
                                                                                            id="size34">
                                                                                        <label class="form-check-label"
                                                                                            for="size34">
                                                                                            34
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-4">
                                                                                    <div class="form-check">
                                                                                        <input class="form-check-input"
                                                                                            type="checkbox" value=""
                                                                                            id="size35">
                                                                                        <label class="form-check-label"
                                                                                            for="size35">
                                                                                            35
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-4">
                                                                                    <div class="form-check">
                                                                                        <input class="form-check-input"
                                                                                            type="checkbox" value=""
                                                                                            id="size36">
                                                                                        <label class="form-check-label"
                                                                                            for="size36">
                                                                                            36
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-4">
                                                                                    <div class="form-check">
                                                                                        <input class="form-check-input"
                                                                                            type="checkbox" value=""
                                                                                            id="size">
                                                                                        <label class="form-check-label"
                                                                                            for="size">
                                                                                            37
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div> -->
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-outline-dark">Bỏ
                                                                lọc</button>
                                                            <button type="submit" class="btn btn-viva">Lọc</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                        <script>
                                            // Bắt sự kiện khi người dùng click vào nút "Lọc"
                                            document.getElementById('filterForm').addEventListener('submit', function (event) {
                                              event.preventDefault();
                                          
                                              var stateInputs = document.getElementsByName('state');
                                              var selectedState = '';
                                              for (var i = 0; i < stateInputs.length; i++) {
                                                if (stateInputs[i].checked) {
                                                  selectedState += stateInputs[i].value + ",";
                                                }
                                              }
                                              selectedState = selectedState.slice(0, -1); // Xóa dấu ',' cuối cùng
                                          
                                              var form = document.getElementById('filterForm');
                                              var currentAction = form.getAttribute('action');
                                              var currentStates = currentAction.match(/state=[^&]*/); // Lấy chuỗi 'state' hiện tại từ action
                                              var newAction;
                                          
                                              if (currentStates) {
                                                // Xóa giá trị 'state' hiện tại trong action
                                                newAction = currentAction.replace(currentStates[0], 'state=' + selectedState);
                                              } else {
                                                // Thêm giá trị 'state' mới vào action
                                                newAction = currentAction + '&state=' + selectedState;
                                              }
                                          
                                              form.setAttribute('action', newAction);
                                          
                                              form.submit();
                                            });
                                          
                                            // Bắt sự kiện khi người dùng chọn mục sắp xếp
                                            var sortItems = document.getElementsByClassName('dropdown-item');
                                            for (var i = 0; i < sortItems.length; i++) {
                                              sortItems[i].addEventListener('click', function (event) {
                                                event.preventDefault();
                                          
                                                var field = event.target.getAttribute('data-field'); // Lấy giá trị 'field' từ data-field
                                                var state = 'outDatedVoucher'; // Thay thế bằng giá trị state cần xử lý
                                                var page = 0; // Thay thế bằng giá trị trang hiện tại
                                          
                                                changeSort(field, state, page);
                                              });
                                            }
                                          
                                            function changeSort(field, state, page) {
                                              var currentURL = window.location.href;
                                              var newURL;
                                          
                                              // Xóa các tham số về trang (p)
                                              var regexPage = /(\?|&)p=[^&]*/;
                                              newURL = currentURL.replace(regexPage, '');
                                          
                                              // Xóa các tham số về trạng thái (state)
                                              var regexState = /(\?|&)state=[^&]*/;
                                              newURL = newURL.replace(regexState, '');
                                          
                                              // Xóa các tham số về trường sắp xếp (field)
                                              var regexField = /(\?|&)field=[^&]*/;
                                              newURL = newURL.replace(regexField, '');
                                          
                                              // Thêm tham số mới vào URL
                                              if (newURL.includes('?')) {
                                                newURL += '&';
                                              } else {
                                                newURL += '?';
                                              }
                                              newURL += `field=${field}&state=${state}&p=${page}`;
                                          
                                              // Chuyển hướng đến URL mới
                                              window.location.href = newURL;
                                            }
                                          </script>
                                          

                                    </span>
                                </div>
                                <a class="btn btn-blue-primary"
                                    href="${pageContext.request.contextPath}/admin/discounts/create">
                                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                                    Thêm
                                </a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead class="table-header-custom">
                                        <tr>
                                            <!-- <th scope="col">ID</th> -->
                                            <th scope="col">Tên mã</th>
                                            <th scope="col">Phần Trăm</th>
                                            <th scope="col">Ngày Tạo</th>
                                            <th scope="col">Giá tiền sử dụng</th>
                                            <th scope="col">Ngày Bắt Đầu</th>
                                            <th scope="col">Ngày Kết Thúc</th>
                                            <th scope="col">Thao tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${items.content}">
                                            <tr>
                                                <!-- <th scope="row">${item.id}</th> -->
                                                <td>${item.toUpperCaseString}</td>
                                                <td>${item.salePercentString}</td>
                                                <td>${item.createAtString}</td>
                                                <td>${item.minPriceStringVnd}</td>
                                                <td>${item.startAtString}</td>
                                                <td>${item.endAtString}</td>
                                                <td>
                                                    <a class="text-dark"
                                                        href="${pageContext.request.contextPath}/admin/discounts/update-form/${item.id}">
                                                        <button class="border-0 bg-transparent">
                                                            <i class="fa fa-pencil" aria-hidden="true"></i>
                                                        </button>
                                                    </a>

                                                    <button class="border-0 bg-transparent" data-bs-toggle="modal"
                                                        data-bs-target="#id${item.id}"><i class="fa fa-trash"
                                                            aria-hidden="true"></i>
                                                    </button>
                                                    <div class="modal fade" id="id${item.id}" aria-hidden="true"
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
                                                                    <img class="img-fluid mb-3" width="300px"
                                                                        height="300px"
                                                                        src="${pageContext.request.contextPath}/views/admin/plugins/images/large/cat-screaming-no.jpg">

                                                                    <h4>Bạn có chắc chắn muốn xóa không?</h4>
                                                                </div>
                                                                <form:form
                                                                    action="${pageContext.request.contextPath}/admin/discounts/delete/${item.id}"
                                                                    method="post" modelAttribute="voucher">
                                                                    <div class="modal-footer">
                                                                        <div class="row g-3 w-100">
                                                                            <div class="col-6">
                                                                                <button
                                                                                    class="btn btn-outline-danger w-100"
                                                                                    class="btn-close"
                                                                                    data-bs-dismiss="modal"
                                                                                    aria-label="Close">Không
                                                                                </button>
                                                                            </div>
                                                                            <div class="col-6">
                                                                                <button type="submit"
                                                                                    class="btn btn-blue-primary w-100">Có</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form:form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                                <div class="d-flex justify-content-center pagination-custom">
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a class="page-link fs-4"
                                                href="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=0">
                                                First
                                            </a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link fs-4"
                                                href="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=${items.number - 1 < 0 ? 0 : items.number - 1}">
                                                <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="0"
                                            end="${items.totalPages - 1 < 0 ? 0 : items.totalPages - 1}">
                                            <li class="page-item ${i == items.number ? 'active' : ''}"><a
                                                    class="page-link fs-4"
                                                    href="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=${i}">${i+1}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item">
                                            <a class="page-link fs-4"
                                                href="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=${items.number + 1 > (items.totalPages - 1) ? items.totalPages - 1 : items.number + 1 }">
                                                <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
                                            </a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link fs-4"
                                                href="${pageContext.request.contextPath}/admin/discounts/?field=${field}&p=${items.totalPages - 1}">
                                                Last
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                        <!-- Footer -->
                        <jsp:include
                            page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                        <!-- Footer -->
                    </div>
                </div>


                <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />

            </body>

            </html>