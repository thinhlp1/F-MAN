app.controller(
    "SizeController",
    function (
        $scope,
        $http,
        DataService,
        FormService,
        ErrorService,
        $location,
        $timeout,
    ) {
        const SIZE_URL = "http://localhost:8080/admin/sizes";
        const SIZE_LIST_URL = `${SIZE_URL}/list`;

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
            $location.path("/size");
        };

        $scope.load = () => {
            return $http
                .get(SIZE_LIST_URL)
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

        $scope.edit = async (id) => {
            $scope.isLoading = true; // Đánh dấu là phần xử lý bất đồng bộ đang được thực hiện
            try {
                const resp = await $http.get(SIZE_URL + "/" + id);
                FormService.setForm(resp.data);
                $scope.form = FormService.getForm();
                console.log($scope.form);
                $timeout(() => {
                    $scope.isLoading = false; // Đánh dấu là phần xử lý bất đồng bộ đã hoàn thành
                    $location.path("/size-update");
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
            const size = angular.copy($scope.form);
            console.log(size);
            $http
                .post(SIZE_URL + "/create", size)
                .then((resp) => {
                    $scope.data.push(size);
                    ErrorService.setError({});
                    $scope.errors = ErrorService.getError();
                    notification("Thêm thành công", 3000, "right", "top", "success");
                    console.log("Thêm thành công", resp);
                })
                .catch((err) => {
                    console.log(err);
                    ErrorService.setError(err.data.errors);
                    $scope.errors = ErrorService.getError();
                    notification(
                        "ERROR " + err.status + ": " + err.data.message,
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
        };

        $scope.update = () => {
            const item = angular.copy($scope.form);
            const url = `${SIZE_URL}/update-form/${$scope.form.id}`;
            $http
                .put(url, item)
                .then((resp) => {
                    var index = $scope.data.findIndex(
                        (item) => item.id === $scope.form.id,
                    );
                    ErrorService.setError({});
                    $scope.errors = ErrorService.getError();
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
                        "ERROR " + err.status + "Cập nhật thất bại ",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
        };

        $scope.delete = (id) => {
            $("#size").modal("hide");
            const url = `${SIZE_URL}/delete/` + id;
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
            $("#size-restore").modal("hide");
            const url = `${SIZE_URL}/restore/` + id;
            $http
                .put(url)
                .then((resp) => {
                    const index = $scope.data.findIndex(
                        (item) => item.id === id,
                    );
                    $scope.data[index].active = 1;
                    $scope.updateTable($scope.data);
                    notification("Phục hồi thành công", 3000, "right", "top", "success");
                })
                .catch((err) => {
                    notification(
                        "ERROR " + err.status + ": Phục hồi thất bại",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                    console.log("Fail", err);
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
                            id: "size",
                            name: "Size",
                        },
                        {
                            id: "length",
                            name: "Chiều dài",
                            formatter: (cell) => {
                                return gridjs.html(cell + "<sub> cm</sub>");
                            },
                        },
                        {
                            id: "width",
                            name: "Chiều rộng",
                            formatter: (cell) => {
                                return gridjs.html(cell + "<sub> cm</sub>");
                            },
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
                                                    $("#size").modal("show");
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
                                                    $("#size-restore").modal("show");
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
                            soft: false,
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
                    search: true,
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
