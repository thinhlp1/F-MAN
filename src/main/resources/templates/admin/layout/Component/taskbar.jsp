<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


      
        <% Object quantity=request.getSession().getAttribute("quantityOrderApprove"); int quantityOrderApprove=0; if
            (quantity !=null) { quantityOrderApprove=(int) quantity; }%>
            <div class="page-breadcrumb bg-white">
                <div class="row align-items-center">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">
                            <% String uri=request.getRequestURL().toString(); String
                                title=uri.substring(uri.lastIndexOf("layout/") + 7, uri.lastIndexOf("/") ); String
                                result="" ; switch(title){ case "Analysis" : result="Thống Kê" ; break; case "Brand" :
                                result="Thương Hiệu" ; break; case "Category" : result="Danh Mục" ; break;
                                case "Discount" : result="Giảm Giá" ; break; case "Order" : result="Đơn Hàng" ; break;
                                case "Product" : result="Sản Phẩm" ; break; case "Size" : result="Size" ; break;
                                case "User" : result="Người Dùng" ; break;case "Payment" :
                                result="Phương Thức Thanh Toán" ; break; default: result="Bảng Điều Khiển" ; break; } %>
                                <%=result%>
                        </h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <div class="d-md-flex">
                            <ol class="breadcrumb ms-auto">
                                <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="fw-normal">Bảng
                                        Điều
                                        Khiển</a></li>
                            </ol>
                            <a href="${pageContext.request.contextPath}/admin/orders/approve?orderStateId=PENDING_APPROVAL"
                                class="btn btn-blue-primary position-relative d-none d-md-block pull-right ms-3 text-white">Đơn
                                Chờ

                                <span id="quantityOrderApprove"
                                    class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                    ${quantityOrderApprove}
                                    <span class="visually-hidden">unread messages</span>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>