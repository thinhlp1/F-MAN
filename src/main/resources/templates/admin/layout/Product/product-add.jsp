<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
.mgs_errors{
 		color: red;
 	}
</style>
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
                        <a class="btn btn-blue-primary" href="${pageContext.request.contextPath}/admin/products/">
                            <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
                            Quay lại
                        </a>
                    </div>
                    
                    <!-- Form xử lý -->
                    <form:form action="${pageContext.request.contextPath}/admin/products/create" method="post" modelAttribute="product" enctype="multipart/form-data">
                    
      
                 
                    <div class="row">
                        <div id="imgDialog" class="col-4 mb-3">
                            <div id="image-box" class="text-center position-relative w-100 h-100"
                                style="border: dashed">
                                <img id="blah" class="img-fluid w-100 h-100">
                                <i id="icon-upload"
                                    class="fa fa-cloud-upload fs-7 position-absolute top-50 start-50 translate-middle"
                                    aria-hidden="true"></i>
                                <input accept="image/*" type='file' name="photo_file" id="imgInp" hidden="hidden">
                                  <div class="mgs_errors">${message_img}</div>
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
                                        <form:input type="text" class="form-control" path="id" id="product_id"
                                            placeholder="ID Sản Phẩm"/>
                                        <label for="product_id">ID Sản Phẩm</label>
                                         <form:errors path="id" cssClass="text-danger"/>
                                         <div class="mgs_errors">${message_id}</div> 
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <form:input type="text" class="form-control" path="name" id="product_name"
                                            placeholder="Tên sản phẩm"/>
                                        <label for="product_name">Tên sản phẩm</label>
                                          <form:errors path="name" cssClass="text-danger"/>
                                           <div class="mgs_errors">${message_name}</div>
                                    </div>
                                </div>
                                <div class="col-12">
                                   <div class="form-floating mb-3">													 
								           <select name="id_brand" class="form-select">
								 			<option value="">Chọn Thương Hiệu</option>
								 			<c:forEach var="items" items="${list_brands}">
								 				<option value="${items.id}">${items.name}</option>	
								 			</c:forEach>													
									</select>
										<div class="mgs_errors">${message_id_brand}</div>		
									<label for="product_brand">Thương hiệu</label>													
								  </div> 
                                </div>
                                 <div class="col-12">
                                    <div class="form-floating mb-3">													 
								           <select name="id_product_type" class="form-select">
								 			<option value="">Chọn Loại Sản Phẩm</option>
								 			<c:forEach var="items" items="${list_pd_type}">
								 				<option value="${items.id}">${items.name}</option>	
								 			</c:forEach>
																						
									</select> 
									<div class="mgs_errors">${message_id_producttype}</div>		
									<label for="product_type">Loại sản phẩm</label>													
								  </div> 
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <form:input type="number" min="200000" path="price" class="form-control" id="product_price"
                                            placeholder="Giá sản phẩm" />
                                        <label for="product_price">Giá</label>
                                         <form:errors path="price" cssClass="text-danger"/>
                                           <div class="mgs_errors">${message_price}</div>
                                    </div>
                                </div> 
                                
                                
                           <!-- start modal -->     
                                <div class="col-6">
                                    <button type="button" class="btn btn-outline-dark w-100 h-75" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal">
                                        <i class="fa fa-scribd" aria-hidden="true"></i>
                                        Số lượng
                                    </button>
                                    <div class="mgs_errors">${message_size_quantity}</div>		
                           			
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
                                                                        <td><!-- <input class="h-100 w-100 py-2 fs-4"
                                                                                type="text" placeholder="Nhập size"> -->
                                                                          
                																 <div class="form-floating mb-3">													 
     								   										        <select name="size_product" class="h-100 w-100 py-2 fs-4 form-select">
										    								 			
										    								 				<c:forEach var="items" items="${list_sizes}">
										    								 					<option value="${items.id}">${items.size}</option>	
										    								 				</c:forEach>													
										    											</select>
										    									  </div> 
                									
                                                                        </td>
                                                                        <td>
                                                                        <input class="h-100 w-100 py-2 fs-4" min="1" name="quatity_product"
                                                                                type="number"
                                                                                placeholder="Nhập số lượng"></td>
                                                                        <td>
                                                                            <button type="button"
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
                                                            type="button" class="btn bg-transparent text-success text-end"><i
                                                                class="fa fa-plus fs-7" aria-hidden="true"></i></button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Đóng</button>
                                                        
                                                        
                                                        
                                                    <button type="button" class="btn btn-primary" id="addSize">Chọn</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                   
                                    <script>
                                        const table = document.getElementById('my-table');
                                        const addRowButton = document.getElementById('add-row-button');
                                        const clickSize = document.getElementById('addSize');


                                        clickSize.addEventListener('click' , function() {
                                            const listSize = document.getElementsByName('size_product');
                                            const listQuatity = document.getElementsByName('quatity_product');

                                            var sizeArray = [];
                                            


                                            listSize.forEach(function(item , index) {
                                                var sizeOject = {};
                                                sizeOject.id = item.value;        
                                                sizeArray.push(sizeOject);   
                                            } )
                                            for(var i =0; i< sizeArray.length; i++) {
                                                sizeArray[i].quatity = listQuatity[i].value;
                                            }
                                           
                                           
                                            console.log(sizeArray);
                                            $.ajax({
                                                url: "${pageContext.request.contextPath}/admin/products/create/productsize",
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
                                        addRowButton.addEventListener('click', function () {
                                            // Tạo một hàng mới
                                            const newRow = table.insertRow();

                                            // Tạo hai ô mới cho hàng
                                            const cell1 = newRow.insertCell();
                                            const cell2 = newRow.insertCell();
                                            const cell3 = newRow.insertCell();

                                            // Thêm nội dung cho hai ô mới tạo
                                           // cell1.innerHTML = `<input class="h-100 w-100 py-2 fs-4" type="text" name="size_product" placeholder="Nhập size">`;
                                            cell1.innerHTML = `	  
                                            <div class="form-floating mb-3">													 
     								           <select name="size_product" class="form-select h-100 w-100 py-2 fs-4">
    								 			
    								 				<c:forEach var="items" items="${list_sizes}">
    								 					<option value="${items.id}">${items.size}</option>	
    								 				</c:forEach>													
    											</select>
    									  </div> 
    										`; 
                                            cell2.innerHTML = `<input class="h-100 w-100 py-2 fs-4" type="number" name="quatity_product" placeholder="Nhập số lượng">`;

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
 					  <!-- end modal -->
 					  
 					  
                            </div>
                        </div>
                        
                     
                        
                        <div class="col-12 mb-3">
                            <div class="form-floating">
                                <form:textarea class="form-control" path="desc" placeholder="Nhập ghi chú" id="product_note"
                                    style="height: 100px"></form:textarea>
                                <label for="product_note">Ghi chú</label>
                                 <form:errors path="desc" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-12 text-end">
                            <button type="submit" class="btn btn-blue-primary btn-lg">
                                Tạo
                            </button>
                        </div>
                    </div>
				   </form:form>
		
                </div>
                <!-- Footer -->
                <jsp:include page="${pageContext.request.contextPath}/views/admin/layout/Component/footer.jsp" />
                <!-- Footer -->
            </div>
        </div>


        <jsp:include page="${pageContext.request.contextPath}/views/admin/abstract/js.jsp" />

    </body>

    </html>