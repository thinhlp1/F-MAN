app.controller(
    "productController",
    function(
      $scope,
      $http,
      DataService,
      FormService,
      ErrorService,
      $location,
      $timeout,
    ){
        const PRODUCT_URL = "http://localhost:8080/admin/products";
        const PRODUCT_LIST_URL = "http://localhost:8080/admin/products/list";



          //Khởi tạo các biến toàn cục
    $scope.id = "";
    $scope.data = DataService.getData(); //Chứa danh sách tất cả đối tượng (Category)
    $scope.form = FormService.getForm(); //Chưa đối tượng được chỉ định (Update, Create)
    $scope.errors = ErrorService.getError();
    $scope.listSizes = DataService.getData();
    /*NOTE: Mục đích của tạo Service là truyền dữ liệu qua lại giữa các Controller*/


    $scope.showPreview = false;

    $scope.toggleInput = function() {
        var input = document.getElementById('imgInp');
        if (input) {
            input.click();
        }
    };
    
    $scope.reset = () => {
      $scope.id = "";
      $scope.form = {};
    };

    $scope.load = () => {
      return $http
        .get(PRODUCT_LIST_URL)
        .then((resp) => {
          //Lấy dữ liệu từ server và gán vào DataService
          DataService.setData(resp.data);
          //Gán dữ liệu vào $scope.data trên biến toàn cục
          $scope.data = DataService.getData();
         
        })
        .catch((err) => {
          console.log(err);
          notification(
            "ERROR " + err.status + ": Lỗi tải dữ liệu",
            3000,
            "right",
            "top",
            "error",
          );
        });
        };


// idit chuyen vao trang update kem product
$scope.edit = async (id) => {
  $scope.isLoading = true; // Đánh dấu là phần xử lý bất đồng bộ đang được thực hiện
  try {
    const resp = await $http.get(PRODUCT_URL + "/" + id);
    FormService.setForm(resp.data);
    $scope.form = FormService.getForm();
    console.log($scope.form);
    $timeout(() => {
      $scope.isLoading = false; // Đánh dấu là phần xử lý bất đồng bộ đã hoàn thành
      $location.path("/product-update");
    });
  } catch (err) {
    console.log(err);
    notification(
      "ERROR " + err.status + ": Lỗi tải dữ liệu",
      3000,
      "right",
      "top",
      "error",
    );
  }
};

$scope.clickSelectSize = () => {
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
            url: "http://localhost:8080/admin/products/create/productsize",
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

    };

    $scope.clickSelectSizeUpdate = () => {
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
          url: "http://localhost:8080/admin/products/create/productsizeupdate",
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

  };

    $scope.create = () => {
      var fileInput = document.getElementById("imgInp");
      var file = fileInput.files[0];
      var formData = new FormData();
    
      formData.append('photo_file', file);
      formData.append('product_id', $scope.form.id);
      formData.append('product_name', $scope.form.name);
      formData.append('brandId', $scope.form.brandId);
      formData.append('productTypeId', $scope.form.productTypeId);
      formData.append('price', $scope.form.price);
      formData.append('desc', $scope.form.desc);
      
      $http
        .post(PRODUCT_URL + "/create", formData, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
      }).then((resp) => {
          $scope.data.push(resp.data);
          notification("Thêm thành công", 3000, "right", "top", "success");
          console.log("Thêm thành công", resp);
      }).catch((err) => {
          console.log(err);
          notification("ERROR " + err.status + ": Thêm thất bại", 3000, "right", "top", "error");
      });
    };


    $scope.update = () => {
      var fileInput = document.getElementById("imgInp");
      var file = fileInput.files[0];
      var formData = new FormData();
      formData.append('photo_file', file);
      formData.append('product_name', $scope.form.name);
      formData.append('brandId', $scope.form.brandId);
      formData.append('productTypeId', $scope.form.productTypeId);
      formData.append('price', $scope.form.price);
      formData.append('desc', $scope.form.desc);

      const url = `${PRODUCT_URL}/${$scope.form.id}`;
      $http
        .put(url, formData ,{
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
      }).then((resp) => {
           var index = $scope.data.findIndex(
          (item) => item.id === $scope.form.id,
      );
          $scope.data[index] = resp.data;
          $scope.load();
          notification("Cập nhật thành công", 3000, "right", "top", "success");
          console.log("Cập nhật thành công", resp);
      }).catch((err) => {
          console.log(err);
          notification("ERROR " + err.status + ": Cập nhật thất bại", 3000, "right", "top", "error");
      });
    };


    $scope.delete = (id) => {
      $("#product").modal("hide");
      const url = `${PRODUCT_URL}/delete/` + id;
      $http
        .delete(url)
        .then((resp) => {
          // Xóa bản ghi khỏi $scope.data
          $scope.data = $scope.data.filter((item) => item.id !== id);
          $scope.updateTable($scope.data);
          notification("Xóa thành công", 3000, "right", "top", "success");
        })
        .catch((err) => {
          notification(
            "ERROR " + err.status + ": Xóa thất bại",
            3000,
            "right",
            "top",
            "error",
          );
          console.log("Fail", err);
        });
    };


    // add row table size and quantity
    $scope.addRowHTML2 = () => {
     listSizesRender = {};
      $http
        .get(PRODUCT_URL +"/getListSize")
        .then((resp) => {
          //Lấy dữ liệu từ server và gán vào DataService
       
          //Gán dữ liệu vào $scope.data trên biến toàn cục
          listSizesRender = resp.data;
          console.log(listSizesRender);
       
      var my_table = document.getElementById("my-table");
   
      // Tạo một hàng mới
      const newRow = my_table.insertRow();
      // Tạo hai ô mới cho hàng
      const cell1 = newRow.insertCell();
      const cell2 = newRow.insertCell();
      const cell3 = newRow.insertCell();
      cell1.innerHTML = `
      <div class="form-floating mb-3">
      <select name="size_product" class="h-100 w-100 py-2 fs-4 form-select"> 
      
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
        my_table.deleteRow(newRow.rowIndex);
      });
     

    })
    .catch((err) => {
      console.log(err);
      notification(
        "ERROR " + err.status + ": Lỗi tải dữ liệu",
        3000,
        "right",
        "top",
        "error",
      );
    });      
    };
       


    //Khởi tạo table GRIDJS
    $scope.initGrid = () => {
        //Kiểm tra có tồn tại thẻ gốc có id = 'grid' để đặt table hay không
        //Nếu tồn tại xóa tất cả element bên trong
        const container = document.getElementById("grid");
        if (container) {
          while (container.firstChild) {
            container.removeChild(container.firstChild);
          }
  
          // Khởi tạo Grid.js
          $scope.grid = new gridjs.Grid({
            /*
                     - column là table header chứa tất cả các cột của bảng
                     - @: id: map với key của đối tượng json trả về
                     - @: name: tên cột sẽ hiển thị ra view
                    */
            columns: [
              {
                id: "id",
                name: "ID",
              },
              {
                id: "name",
                name: "Tên Sản Phẩm",
              },
              {
                id: "active",
                name: "Trạng Thái",
                formatter: (cell) => {
                  // cell là nội dụng trong một ô ở cột 'Trạng thái' (cell sẽ auto duyệt qua bằng data được truyền vào)
                  if (cell === 1) {
                    return "Hoạt động";
                  } else if (cell === 0) {
                    return "Không hoạt động";
                  } else {
                    return "";
                  }
                },
              },
              {
                name: "Action",
                formatter: (cell, row) => {
                  //row là hàng hiện tại
                  //cell là ô hiện tại
  
                  //tạo ra một thẻ div ở cột 'Action' dùng để chưa 3 nút (Edit, Delete, View)
                  // gridjs.h() là khởi tạo một element, 3 tham số chủ yếu (tên thẻ, {className, event}, nội dung thẻ)
                  //Tham số thứ nhất là tên thẻ
                  //Tham số thứ hai là một đối tượng chứa attribute và event
                  //Tham số thứ ba là nội dung của thẻ ở vd dưới nội dung là một thẻ i lồng vào button
                  return gridjs.h(
                    "div",
                    {
                      className: "d-flex gap-1",
                    },
                    gridjs.h(
                      "button",
                      {
                        className: "border-0 bg-transparent",
                        onClick: () => {
                          //Gán cho $scope.id là id của item vừa click vào
                          $scope.id = row.cells[0].data;
                          //Gọi funcntion edit() để xử lí
                          $scope.edit($scope.id);
                        },
                      },
                      //gridjs.html() là thẻ sẽ được truyền vào ở đây là một thẻ <i> chứa icon
                      //Nếu muốn ghi text bình thường thì bỏ gridjs.html() và ghi text vào bình thường
                      gridjs.html(
                        '<i class="fa fa-pencil" aria-hidden="true"></i>',
                      ),
                    ),
                    gridjs.h(
                      "button",
                      {
                        className: "border-0 bg-transparent",
                        onClick: () => {
                          $scope.id = row.cells[0].data;
                          $scope.$evalAsync(() => {
                            console.log($scope.id);
                            //Xử lí khi click vào thì gọi thằng modal ra
                            //Modal nằm ở bên trang html
                            $("#product").modal("show");
                          });
                        },
                      },
                      gridjs.html(
                        '<i class="fa fa-trash"aria-hidden="true"></i>',
                      ),
                    ),
                    gridjs.h(
                      "button",
                      {
                        className: "border-0 bg-transparent",
                        onClick: () =>
                          alert(
                            `Viewing "${row.cells[0].data}" "${row.cells[1].data}"`,
                          ),
                      },
                      gridjs.html('<i class="fa fa-eye" aria-hidden="true"></i>'),
                    ),
                  );
                },
              },
            ],


            data: $scope.data, //Data được truyền vào (JSON, ARRAY)
            sort: true, //cho phép thêm plugin sắp xếp vào bảng
            pagination: {
              //cho phép thêm plugin phân trang vào bảng
              limit: 5, // Giới hạn số item trong bảng
              enabled: true,
            },
            search: true, //cho phép thêm plugin tìm kiếm vào bảng
            className: {
              thead: "table-header-custom", //css className custom cho bảng
            },
            style: {
              //css style custom cho bảng
              th: {
                "background-image": "linear-gradient(180deg, #2f323e, #707893)",
              },
            },
            language: {
              search: {
                placeholder: "🔍 Nhập từ khóa...",
              },
              pagination: {
                previous: "⬅️",
                next: "➡️",
                to: "đến",
                of: "trên",
                showing: "Hiển thị",
                results: () => "Bản ghi",
              },
            },
          }).render(container);
        }
      };
  
      //updateTable dùng để cập nhật lại dữ liệu cho bảng GRIDJS mỗi khi thêm, sửa hoặc xóa
      $scope.updateTable = (data) => {
        // Cập nhật lại dữ liệu trên Grid.js
        $scope.grid.config.plugin.remove("pagination");
        $scope.grid.config.plugin.remove("search");
        $scope.grid.updateConfig({ data: data }).forceRender();
      };
  
      //Call Function
      $scope.load().then(() => {
        $scope.initGrid();
        document.getElementById('imgInp').addEventListener('change', function() {
          var preview = document.getElementById('imgPreview');
          var file = document.getElementById('imgInp').files[0];
          var reader = new FileReader();

          reader.onload = function(e) {
              $scope.$apply(function() {
                  $scope.previewImage = e.target.result;
                  $scope.showPreview = true;
              });
          };

          if (file) {
              reader.readAsDataURL(file);
          } else {
              $scope.$apply(function() {
                  $scope.previewImage = '';
                  $scope.showPreview = false;
              });
          }
      });
      });
    },

    

)