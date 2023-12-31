app.controller(
    "CategoryController",
    function (
        $scope,
        $http,
        DataService,
        FormService,
        ErrorService,
        $location,
        $timeout,
    ) {
        const CATEGORY_URL = "http://localhost:8080/admin/categorys";
        const CATEGORY_LIST_URL = `${CATEGORY_URL}/list`;
        //Khởi tạo các biến toàn cục
        $scope.id = "";
        $scope.data = DataService.getData(); //Chứa danh sách tất cả đối tượng (Category)
        $scope.form = FormService.getForm(); //Chưa đối tượng được chỉ định (Update, Create)
        $scope.errors = ErrorService.getError();
        /*NOTE: Mục đích của tạo Service là truyền dữ liệu qua lại giữa các Controller*/

        $scope.reset = () => {
            $scope.id = "";
            FormService.setForm({})
            ErrorService.setError({})
            $scope.form = {};
            $location.path("/category");
        };

        $scope.load = () => {
            return $http
                .get(CATEGORY_LIST_URL)
                .then((resp) => {
                    //Lấy dữ liệu từ server và gán vào DataService
                    DataService.setData(resp.data);
                    //Gán dữ liệu vào $scope.data trên biến toàn cục
                    $scope.data = DataService.getData();
                    $scope.data.sort((a,b) => b.active - a.active);
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

        $scope.edit = async (id) => {
            $scope.isLoading = true; // Đánh dấu là phần xử lý bất đồng bộ đang được thực hiện
            try {
                const resp = await $http.get(CATEGORY_URL + "/" + id);
                FormService.setForm(resp.data);
                $scope.form = FormService.getForm();
                console.log($scope.form);
                $timeout(() => {
                    $scope.isLoading = false; // Đánh dấu là phần xử lý bất đồng bộ đã hoàn thành
                    $location.path("/category-update");
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

        $scope.create = () => {
            var category = angular.copy($scope.form);
            $http
                .post(CATEGORY_URL + "/create", category)
                .then((resp) => {
                    $scope.data.push(category);
                    $scope.errors = {};
                    notification("Thêm thành công", 3000, "right", "top", "success");
                    console.log("Thêm thành công", resp);
                })
                .catch((err) => {
                    console.log(err);
                    ErrorService.setError(err.data.errors);
                    $scope.errors = ErrorService.getError();
                    notification(
                        "ERROR " + err.status + ": " + (err.data.message != null ? err.data.message : 'Thêm thất bại'),
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
        };

        $scope.update = () => {
            var item = angular.copy($scope.form);
            const url = `${CATEGORY_URL}/${$scope.form.id}`;
            $http
                .put(url, item)
                .then((resp) => {
                    var index = $scope.data.findIndex(
                        (item) => item.id === $scope.form.id,
                    );
                    $scope.errors = {};
                    $scope.data[index] = resp.data;
                    $scope.load();
                    console.log(resp.data);
                    notification("Cập nhật thành công", 3000, "right", "top", "success");
                })
                .catch((err) => {
                    console.log(err);
                    ErrorService.setError(err.data.errors);
                    $scope.errors = ErrorService.getError();
                    notification(
                        "ERROR " + err.status + ": Cập nhật thất bại ",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
        };

        $scope.delete = (id) => {
            $("#category").modal("hide");
            const url = `${CATEGORY_URL}/delete/` + id;
            $http
                .delete(url)
                .then((resp) => {
                    const index = $scope.data.findIndex(
                        (item) => item.id === id,
                    );
                    $scope.data[index].active = 0;
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


        $scope.restore = (id) => {
            $("#category-restore").modal("hide");
            const url = `${CATEGORY_URL}/restore/` + id;
            $http
                .put(url)
                .then((resp) => {
                    const index = $scope.data.findIndex(
                        (item) => item.id === id,
                    );
                    console.log(resp.data)
                    $scope.data[index].active = 1;
                    $scope.updateTable($scope.data);
                    notification("Khôi phục thành công", 3000, "right", "top", "success");
                })
                .catch((err) => {
                    notification(
                        "ERROR " + err.status + ": Khôi phục thất bại",
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
                            name: "Tên Thương Hiệu",
                        },
                        {
                            id: "active",
                            name: "Trạng Thái",
                            formatter: (cell, row) => {
                                // cell là nội dụng trong một ô ở cột 'Trạng thái' (cell sẽ auto duyệt qua bằng data được truyền vào)
                                if (cell === 1) {
                                    return gridjs.h(
                                        "button",
                                        {
                                            className: "btn btn-success text-white",
                                            onClick: () => {
                                                $scope.id = row.cells[0].data;
                                                $scope.$evalAsync(() => {
                                                    console.log($scope.id);
                                                    //Xử lí khi click vào thì gọi thằng modal ra
                                                    //Modal nằm ở bên trang html
                                                    $("#category").modal("show");
                                                });
                                            },
                                        },
                                        'Hoạt động',
                                    );
                                } else if (cell === 0) {
                                    return gridjs.h(
                                        "button",
                                        {
                                            className: "btn btn-danger text-white",
                                            onClick: () => {
                                                $scope.id = row.cells[0].data;
                                                $scope.$evalAsync(() => {
                                                    console.log($scope.id);
                                                    //Xử lí khi click vào thì gọi thằng modal ra
                                                    //Modal nằm ở bên trang html
                                                    $("#category-restore").modal("show");
                                                });
                                            },
                                        },
                                        'Vô hiệu hóa',
                                    );
                                } else {
                                    return "";
                                }
                            },
                        },
                        {
                            name: "Action",
                            sort: false,
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
            $scope.grid.updateConfig({data: data}).forceRender();
        };

        //Call Function
        $scope.load().then(() => {
            $scope.initGrid();
        });
    },
);
