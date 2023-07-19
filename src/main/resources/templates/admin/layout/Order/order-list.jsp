<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html dir="ltr" lang="en">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <!-- Tell the browser to be responsive to screen width -->
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>FMan | Đơn hàng</title>
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

                                <span class="dropdown mb-5">

                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-sortBy" data-bs-toggle="dropdown" aria-expanded="false">

                                        Sắp xếp theo
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="sort" id="sortBy">
                                        <li><a class="dropdown-item" value="createAt" type="button" id="createAt"
                                                onclick="setValueSortBy('createAt','Ngày tạo')">Ngày tạo</a>
                                        </li>
                                        <li><a class="dropdown-item" value="total" type="button" id="total"
                                                onclick="setValueSortBy('total','Tổng tiền')">Tổng tiền</a>
                                        </li>

                                    </ul>
                                </span>
                                <span class="dropdown mb-5">

                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-sortOrder" data-bs-toggle="dropdown" aria-expanded="false">
                                        Chiều
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="sort" id="sortOrder">
                                        <li><a class="dropdown-item" value="asc" type="button" id="asc"
                                                onclick="setValuesortOrder('asc','Tăng dần')">Tăng dần</a>
                                        </li>
                                        <li><a class="dropdown-item" value="desc" type="button" id="desc"
                                                onclick="setValuesortOrder('desc','Giảm dần')">Giảm dần</a>
                                        </li>

                                    </ul>
                                </span>
                                <span class="dropdown mb-5 mx-3">
                                    <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                        id="btn-orderState" data-bs-toggle="dropdown" aria-expanded="false">
                                        Trạng thái
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="orderState" id="orderState">
                                        <li><a class="dropdown-item" value="ALL" type="button" id="ALL"
                                                onclick="setValueOrderState('ALL','Tất cả')">Tất cả</a>
                                        </li>
                                        <c:forEach var="item" items="${orderStates}">
                                            <li><a class="dropdown-item" value="${item.id}" type="button"
                                                    id="${item.id}"
                                                    onclick="setValueOrderState('${item.id}','${item.name}')">${item.name}</a>
                                            </li>
                                        </c:forEach>



                                    </ul>
                                </span>

                            </div>

                        </div>

                        <div class="table-responsive">
                            <table class="table table-hover table-striped">
                                <thead class="table-header-custom">
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Mã đơn</th>
                                        <th scope="col">Khách hàng</th>
                                        <th scope="col">Ngày đặt</th>
                                        <th scope="col">Địa chỉ</th>
                                        <th scope="col">Đơn giá ( VNĐ ) </th>
                                        <th scope="col">Trạng thái </th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${orders}" var="order" varStatus="index">
                                        <tr>
                                            <th scope="row">${index.count}</th>
                                            <td>${order.id}</td>
                                            <td>${order.user.name}</td>
                                            <td>${order.createAtString}</td>
                                            <td>${order.address.receiverName}, ${order.address.numberPhone},
                                                ${order.address.address} </td>
                                            <td>${order.totalString}</td>
                                            <td>${order.orderState.name}</td>
                                            <td>
                                                <a class="text-dark"
                                                    href="${pageContext.request.contextPath}/admin/orders/details/${order.id}">
                                                    <button class="border-0 bg-transparent">
                                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                                    </button>
                                                </a>


                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-center pagination-custom">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link fs-4" type="button" onclick="paginationEdge('first')">
                                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                                        </a>
                                    </li>

                                    <c:forEach var="i" begin="0" end="${totalPages - 1 < 0 ? 0 : totalPages - 1}">
                                        <li class="page-item"><a id="page_${i + 1}"
                                                name="${(i == currentPage) ? 'page_active' : 'no'}"
                                                class="${(i == currentPage) ? 'active' : 'no'} page-link fs-4 "
                                                type="button" onclick="pagination(this)">${i + 1}</a></li>
                                    </c:forEach>

                                    <li class="page-item">
                                        <a class="page-link fs-4" onclick="paginationEdge('last')" type="button">
                                            <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- Footer -->
                    <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                    <!-- Footer -->
                </div>
            </div>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>

                filt();

                function filt() {

                    var orderStateDropdown = document.getElementById("orderState");
                    document.getElementById("btn-orderState").innerHTML = document.getElementById('${orderStateId}').innerHTML;
                    orderStateDropdown.setAttribute("value", '${orderStateId}');

                    var sortByDropDown = document.getElementById("sortBy");
                    document.getElementById("btn-sortBy").innerHTML = document.getElementById('${sortBy}').innerHTML;
                    sortByDropDown.setAttribute("value", '${sortBy}');

                    var sortOrderDropDown = document.getElementById("sortOrder");
                    document.getElementById("btn-sortOrder").innerHTML = document.getElementById('${sortOrder}').innerHTML;
                    sortOrderDropDown.setAttribute("value", '${sortOrder}');


                    console.log('${sortOrder}')
                    console.log(sortOrderDropDown.getAttribute("value"))
                }

                function setValueSortBy(sortBy, name) {
                    var sortByDropDown = document.getElementById("sortBy");
                    document.getElementById("btn-sortBy").innerHTML = name;
                    sortByDropDown.setAttribute("value", sortBy);
                    filtOrder();
                }

                function setValuesortOrder(sortOrder, name) {
                    var sortOrderDropDown = document.getElementById("sortOrder");
                    document.getElementById("btn-sortOrder").innerHTML = name;
                    sortOrderDropDown.setAttribute("value", sortOrder);
                    filtOrder();
                }

                function pagination(page) {
                    var itemList = document.querySelectorAll('a[id^="page_"]');
                    console.log("PAGEEE:" + page.innerHTML)
                    itemList.forEach(function (item) {
                        item.setAttribute("name", "");
                    })
                    page.setAttribute("name", "page_active");
                    filtOrder();
                }

                function setValueOrderState(orderState, name) {
                    var orderStateDropdown = document.getElementById("orderState");
                    document.getElementById("btn-orderState").innerHTML = name;
                    orderStateDropdown.setAttribute("value", orderState);
                    filtOrder();
                }


                function paginationEdge(eage) {
                    var itemList = document.querySelectorAll('a[id^="page_"]');
                    if (eage === "first") {
                        pagination(itemList[0])
                    } else {
                        pagination(itemList[itemList.length - 1])
                    }
                }

                function filtOrder() {

                    var sortBy;
                    var sortOrder;
                    var orderState;
                    var page = document.getElementsByName("page_active")[0].innerHTML;

                    var orderStateDropdown = document.getElementById("orderState");
                    orderState = orderStateDropdown.getAttribute("value")
                    var sortByDropDown = document.getElementById("sortBy");
                    sortBy = sortByDropDown.getAttribute("value");
                    var sortOrderDropDown = document.getElementById("sortOrder");
                    sortOrder = sortOrderDropDown.getAttribute("value");

                    if (sortBy == null) {
                        sortBy = '';
                    }

                    if (sortOrder == null) {
                        sortOrder = '';
                    }
                    if (orderState == null || orderState === "ALL") {
                        orderState = '';
                    }

                    var pageSize = ${ size };
                    console.log("PAGEE: " + page);
                    var url = "${pageContext.request.contextPath}/admin/orders/all?orderStateId=" + orderState + "&sortBy=" + sortBy + "&sortOrder=" + sortOrder + "&page=" + (page) + "&size=" + pageSize
                    window.location.href = url
                    // console.log(url);

                }
            </script>

            <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />
        </body>

        </html>