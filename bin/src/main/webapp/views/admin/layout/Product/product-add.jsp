<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FMan | Create Products</title>
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
                                        <input type="text" class="form-control" id="product_price"
                                            placeholder="Giá sản phẩm">
                                        <label for="product_price">Giá</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <button class="btn btn-outline-dark w-100 h-75" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal">
                                        <i class="fa fa-scribd" aria-hidden="true"></i>
                                        Chọn size
                                    </button>
                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1"
                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Bảng Size</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div id="component-container">
                                                        <div class="component">
                                                            <table id="my-table"
                                                                class="table table-responsive-sm table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col">Size</th>
                                                                        <th scope="col">Số lượng</th>
                                                                        <th scope="col"></th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td><input class="h-100 w-100 py-2 fs-4"
                                                                                type="text" placeholder="Nhập size">
                                                                        </td>
                                                                        <td><input class="h-100 w-100 py-2 fs-4"
                                                                                type="number"
                                                                                placeholder="Nhập số lượng"></td>
                                                                        <td>
                                                                            <button
                                                                                class="delete-row-root btn bg-transparent text-danger h-100 w-100"><i
                                                                                    class="fa fa-trash fs-7 "
                                                                                    aria-hidden="true"></i></button>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="text-end">
                                                        <button id="add-row-button"
                                                            class="btn bg-transparent text-success text-end"><i
                                                                class="fa fa-plus fs-7" aria-hidden="true"></i></button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Đóng</button>
                                                    <button type="button" class="btn btn-primary">Chọn</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <script>
                                        const table = document.getElementById('my-table');
                                        const addRowButton = document.getElementById('add-row-button');

                                        addRowButton.addEventListener('click', function () {
                                            // Tạo một hàng mới
                                            const newRow = table.insertRow();

                                            // Tạo hai ô mới cho hàng
                                            const cell1 = newRow.insertCell();
                                            const cell2 = newRow.insertCell();
                                            const cell3 = newRow.insertCell();

                                            // Thêm nội dung cho hai ô mới tạo
                                            cell1.innerHTML = `<input class="h-100 w-100 py-2 fs-4" type="text" placeholder="Nhập size">`;
                                            cell2.innerHTML = `<input class="h-100 w-100 py-2 fs-4" type="number" placeholder="Nhập số lượng">`;

                                            // Thêm nút "Delete" cho hàng mới tạo
                                            const deleteButton = document.createElement('button');
                                            deleteButton.classList.add('delete-row-button');
                                            deleteButton.classList.add('text-danger');
                                            deleteButton.classList.add('bg-transparent');
                                            deleteButton.classList.add('w-100');
                                            deleteButton.classList.add('h-100');
                                            deleteButton.classList.add('border-0');
                                            deleteButton.innerHTML = `<i class="fa fa-trash fs-7" text-danger aria-hidden="true"></i>`;
                                            cell3.appendChild(deleteButton);

                                            // Gắn sự kiện click cho nút "Delete"
                                            deleteButton.addEventListener('click', function () {
                                                table.deleteRow(newRow.rowIndex);
                                            });
                                        });

                                        // Gắn sự kiện cho tất cả các nút "Delete" được tạo ra ban đầu
                                        const deleteButtons = document.querySelectorAll('.delete-row-button');
                                        deleteButtons.forEach(function (button) {
                                            button.addEventListener('click', function () {
                                                const row = button.parentNode.parentNode;
                                                row.parentNode.removeChild(row);
                                            });
                                        });

                                    </script>
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
                            <div class="btn btn-blue-primary btn-lg">
                                Tạo
                            </div>
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