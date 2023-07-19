<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html class="no-js" lang="zxx">

        <head>
            <!-- Mobile Specific Meta -->
            <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
            <!-- Author Meta -->
            <meta content="CodePixar" name="author">
            <!-- Meta Description -->
            <meta content="" name="description">
            <!-- Meta Keyword -->
            <meta content="" name="keywords">
            <!-- meta character set -->
            <meta charset="UTF-8">
            <!-- Site Title -->
            <title>Danh Sách Đơn Hàng</title>

            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/css.jsp" />

            <style>
                .dropdown-item:hover {
                    background-color: rgb(255, 162, 0);
                    color: white !important;
                    cursor: pointer;
                }
            </style>
        </head>

        <body>

            <!-- Start Header Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/nav.jsp"></jsp:include>
            <!-- End Header Area -->

            <!--================Order Details Area =================-->
            <section class="">
                <div class="container" style="margin-top: 200px; margin-bottom: 50px">
                    <div class="row d-flex mb-3">
                        <div class="mx-3">
                            <span class="dropdown mb-5">

                                <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                    id="btn-sortBy" data-toggle="dropdown" aria-expanded="false">

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
                        </div>
                        <div class="mx-3">
                            <span class="dropdown mb-5">

                                <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                    id="btn-sortOrder" data-toggle="dropdown" aria-expanded="false">
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
                        </div>
                        <div class="mx-5">
                            <span class="dropdown mb-5">
                                <button class="btn bg-transparent border shadow dropdown-toggle" type="button"
                                    id="btn-orderState" data-toggle="dropdown" aria-expanded="false">
                                    Trạng thái
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="orderState" id="orderState">
                                    <li><a class="dropdown-item" value="ALL" type="button" id="ALL"
                                        onclick="setValueOrderState('ALL','Tất cả')">Tất cả</a>
                                     </li>
                                    <c:forEach var="item" items="${orderStates}">
                                        <li><a class="dropdown-item" value="${item.id}" type="button" id="${item.id}"
                                                onclick="setValueOrderState('${item.id}','${item.name}')">${item.name}</a>
                                        </li>
                                    </c:forEach>



                                </ul>
                            </span>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead class="" style="background: rgb(255, 162, 0)">
                                <tr class="text-white">
                                    <th scope="col"><span class="" style="font-family: 'Microsoft Sans Serif'">Mã hóa
                                            đơn</span>
                                    </th>
                                    <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Ngày tạo hóa
                                        đơn</span></th>
                                    <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Địa chỉ</span>
                                    </th>
                                    <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Tổng tiền ( VNĐ )</span>
                                    </th>
                                   
                                    <th scope="col"><span style="font-family: 'Microsoft Sans Serif'">Trạng thái</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${orders}" var="order">
                                    <tr class="order-row"
                                        data-href="${pageContext.request.contextPath}/user/orders/${order.id}?user-id=${order.user.id}">
                                        <td>${order.id}</td>
                                      
                                        <td>${order.createAtString}</td>
                                        <td>${order.address.receiverName}, ${order.address.numberPhone},
                                            ${order.address.address} </td>
                                            <td>${order.totalString}</td>
                                        <td>${order.orderState.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--===============Pagination Area ===================-->
                    <div class="filter-bar d-flex flex-wrap align-items-center justify-content-center">
                        <div class="pagination justify-content-center">
                            <a class="prev-arrow" type="button" onclick="paginationEdge('first')"><i aria-hidden="true" class="fa fa-long-arrow-left"></i></a>
                            <c:forEach var="i" begin="0" end="${totalPages - 1 < 0 ? 0 : totalPages - 1}">
                                <a id="page_${i + 1}" name="${(i == currentPage) ? 'page_active' : 'no'}" class="${(i == currentPage) ? 'active' : 'no'}" onclick="pagination(this)" type="button">${i + 1}</a>

                            </c:forEach>
                            <a class="next-arrow" type="button" onclick="paginationEdge('last')"><i aria-hidden="true" class="fa fa-long-arrow-right"></i></a>
                        </div>
                    </div>
                    <!--===============End Pagination Area ===================-->


                </div>
                </div>
            </section>
            <!--================End Order Details Area =================-->

            <!-- Start Footer Area -->
            <jsp:include page="${pageContext.request.contextPath}/views/user/view/component/footer.jsp"></jsp:include>
            <!-- End Footer Area -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
                crossorigin="anonymous"></script>

            <script>

                filt();

                function  filt(){
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

                function setValueOrderState(orderState, name) {
                    var orderStateDropdown = document.getElementById("orderState");
                    document.getElementById("btn-orderState").innerHTML = name;
                    orderStateDropdown.setAttribute("value", orderState);
                    filtOrder();
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

                function pagination(page){
                    var itemList = document.querySelectorAll('a[id^="page_"]');
                    console.log("PAGEEE:" + page.innerHTML)
                    itemList.forEach(function (item) {
                        item.setAttribute("name","");
                    })
                    page.setAttribute("name", "page_active");
                    filtOrder();
                }

                function paginationEdge(eage){
                    var itemList = document.querySelectorAll('a[id^="page_"]');
                    if (eage === "first"){                      
                        pagination(itemList[0])
                    }else{
                        pagination(itemList[itemList.length-1])
                    }
                }

                function filtOrder() {

                    var sortBy;
                    var sortOrder;
                    var orderState;
                    var page = document.getElementsByName("page_active")[0].innerHTML;
                
                    // Lấy phần tử dropdown menu
                    var orderStateDropdown = document.getElementById("orderState");
                    orderState = orderStateDropdown.getAttribute("value")
                    var sortByDropDown = document.getElementById("sortBy");
                    sortBy = sortByDropDown.getAttribute("value");
                    var sortOrderDropDown = document.getElementById("sortOrder");
                    sortOrder = sortOrderDropDown.getAttribute("value");
                    
                    if (sortBy == null){
                        sortBy = '';
                    }

                    if (sortOrder == null){
                        sortOrder = '';
                    }

                    if (orderState == null || orderState === "ALL"){
                        orderState =''; 
                    }

                    var pageSize = ${ size };
                    console.log("PAGEE: " + page);
                    var url = "${pageContext.request.contextPath}/user/orders/by-user/${userId}" + "?sortBy="+ sortBy +"&sortOrder="+sortOrder+"&orderStateId="+ orderState +"&page="+(page)+"&size=" +pageSize
                    window.location.href = url
                    // console.log(url);

                }
            </script>

            <script>
                $(document).ready(function () {
                    $(".order-row").click(function () {
                        window.location.href = $(this).data("href");
                    });
                });
            </script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <!--gmaps Js-->
            <jsp:include page="${pageContext.request.contextPath}/views/user/abstract/js.jsp" />
        </body>

        </html>