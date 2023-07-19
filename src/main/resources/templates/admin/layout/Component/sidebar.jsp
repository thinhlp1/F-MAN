<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <aside class="left-sidebar" data-sidebarbg="skin6">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <!-- User Profile-->
                    <li class="sidebar-item pt-2">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${pageContext.request.contextPath}/admin/dashboard"
                            aria-expanded="false">
                            <i class="fa fa-th-large" aria-hidden="true"></i>
                            <span class="hide-menu">Bảng Điều Khiển</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="${pageContext.request.contextPath}/admin/products/" aria-expanded="false">
                            <i class="fa fa-cube" aria-hidden="true"></i>
                            <span class="hide-menu">Sản Phẩm</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${pageContext.request.contextPath}/admin/brands/"
                            aria-expanded="false">
                            <i class="fa fa-diamond" aria-hidden="true"></i>
                            <span class="hide-menu">Thương Hiệu</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="${pageContext.request.contextPath}/admin/categorys/" aria-expanded="false">
                            <i class="fa fa-align-left" aria-hidden="true"></i>
                            <span class="hide-menu">Danh Mục</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${pageContext.request.contextPath}/admin/sizes/"
                            aria-expanded="false">
                            <i class="fa fa-strikethrough" aria-hidden="true"></i>
                            <span class="hide-menu">Size</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="${pageContext.request.contextPath}/admin/discounts/" aria-expanded="false">
                            <i class="fa fa-ticket" aria-hidden="true"></i>
                            <span class="hide-menu">Giảm Giá</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${pageContext.request.contextPath}/admin/orders/all"
                            aria-expanded="false">
                            <i class="fa fa-shopping-bag" aria-hidden="true"></i>
                            <span class="hide-menu">Đơn Hàng</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${pageContext.request.contextPath}/admin/users/"
                            aria-expanded="false">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <span class="hide-menu">Người Dùng</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="${pageContext.request.contextPath}/admin/payments/" aria-expanded="false">
                            <i class="fa fa-credit-card-alt" aria-hidden="true"></i>
                            <span class="hide-menu">Phương Thức Thanh Toán</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="${pageContext.request.contextPath}/admin/analysis/revenue" aria-expanded="false">
                            <i class="fa fa-pie-chart fs-5" aria-hidden="true"></i>
                            <span class="hide-menu">Thống Kê</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/auth/logout" aria-expanded="false">
                            <i class="fa fa-sign-out fs-5" aria-hidden="true"></i>
                            <span class="hide-menu">Đăng Xuất</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->