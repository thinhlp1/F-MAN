<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Update Products</title>
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
                    <!--      CODE TRONG DDAAY-->
                    <div class="back mb-3">
                        <a class="btn btn-blue-primary" href="product-list.html">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay lại
                        </a>
                    </div>
                    <div class="row">
                        <div id="imgDialog" class="col-4 mb-3">
                            <div id="image-box" class="text-center position-relative w-100 h-100"
                                style="border: dashed">
                                <img id="blah" class="img-fluid w-100 h-100">
                                <i id="icon-upload"
                                    class="fa fa-cloud-upload fs-7 position-absolute top-50 start-50 translate-middle"
                                    aria-hidden="true"></i>
                                <input accept="image/*" type='file' id="imgInp" hidden="hidden">
                            </div>
                        </div>
                        <!--XỬ LÍ CLICK IMG SHOW DIALOG SELECT IMAGE-->
                        <script>
                            document.getElementById("imgDialog").onclick = (e) => {
                                document.getElementById("imgInp").click();
                            }
                            imgInp.onchange = evt => {
                                const [file] = imgInp.files
                                if (file) {
                                    blah.src = URL.createObjectURL(file)
                                    console.log(file.name);
                                    document.getElementById("icon-upload").remove();
                                    document.getElementById("image-box").style.border = "none";
                                }
                            }
                        </script>
                        <div class="col-8">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="product_name"
                                            placeholder="Tên sản phẩm">
                                        <label for="product_name">Tên sản phẩm</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="product_brand">
                                            <option selected>Chọn thương hiệu</option>
                                            <option value="1">One</option>
                                            <option value="2">Two</option>
                                        </select>
                                        <label for="product_brand">Thương hiệu</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="product_type">
                                            <option selected>Chọn loại sản phẩm</option>
                                            <option value="1">One</option>
                                            <option value="2">Two</option>
                                        </select>
                                        <label for="product_type">Loại sản phẩm</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="product_size">
                                            <option selected>Chọn size</option>
                                            <option value="1">One</option>
                                            <option value="2">Two</option>
                                        </select>
                                        <label for="product_size">Size</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="product_price"
                                            placeholder="Giá sản phẩm">
                                        <label for="product_price">Giá</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 mb-3">
                            <div class="form-floating">
                                <textarea class="form-control" placeholder="Nhập ghi chú" id="product_note"
                                    style="height: 100px"></textarea>
                                <label for="product_note">Ghi chú</label>
                            </div>
                        </div>
                        <div class="col-12 text-end">
                            <button class="btn btn-blue-primary btn-lg">
                                Cập Nhật
                            </button>
                        </div>
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