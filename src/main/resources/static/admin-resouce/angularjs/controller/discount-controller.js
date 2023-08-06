app.controller("DiscountController",
    function ($scope, $http, DataService, FormService,ErrorService, $location, $timeout) {
        const DISCOUNT_URL = "http://localhost:8080/admin/discounts";
        const DISCOUNT_LIST_URL = `${DISCOUNT_URL}/list`;

        //Khởi tạo các biến toàn cục
        $scope.id = "";
        $scope.data = DataService.getData(); //Chứa danh sách tất cả đối tượng (Discount)
        $scope.form = FormService.getForm(); //Chưa đối tượng được chỉ định (Update, Create)

        $scope.errors = ErrorService.getError();
        /*NOTE: Mục đích của tạo Service là truyền dữ liệu qua lại giữa các Controller*/


        $scope.reset = () => {
            $scope.id = "";
            $scope.form = {};
        };

        $scope.load = () => {
            return $http
                .get(DISCOUNT_LIST_URL)
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
                const resp = await $http.get(DISCOUNT_URL + "/" + id);

                // Chuyển đổi từ kiểu String thành kiểu Date bằng JavaScript Date
                resp.data.startAt = new Date(resp.data.startAt);
                resp.data.endAt = new Date(resp.data.endAt);
                FormService.setForm(resp.data);
                // var date = $scope.form.startAt;
                // $scope.setStartAtForm(date);
                $scope.form = FormService.getForm();


                $timeout(() => {
                    $scope.isLoading = false; // Đánh dấu là phần xử lý bất đồng bộ đã hoàn thành
                    $location.path("/discount-update");
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

        $scope.update = () => {
            var item = angular.copy($scope.form);
            const url = `${DISCOUNT_URL}/${$scope.form.id}`;
            $http
                .put(url, item)
                .then((resp) => {
                    var index = $scope.data.findIndex(
                        (item) => item.id === $scope.form.id,
                    );
                    $scope.data[index] = resp.data;
                    $scope.errors = {};
                    $scope.load();
                    console.log(resp.data);
                    notification("Cập nhật thành công", 3000, "right", "top", "success");
                })
                .catch((err) => {
                    ErrorService.setError(err.data.errors);
                    $scope.errors = ErrorService.getError();
                    notification(
                        "ERROR " + err.status + ": Cập nhật thất bại",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                    console.log(err);
                });
        };

        $scope.restore = () => {
            const url = `${DISCOUNT_URL}/restore/${$scope.form.id}`;
            $http
                .put(url)
                .then((resp) => {
                    var index = $scope.data.findIndex(
                        (item) => item.id === $scope.form.id,
                    );
                    $scope.data[index] = resp.data;
                    $scope.load();
                    console.log(resp.data);
                    notification("Hoàn tác thành công", 3000, "right", "top", "success");
                })
                .catch((err) => {
                    notification(
                        "ERROR " + err.status + ": Hoàn tác thất bại",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                    console.log(err);
                });
        };

        $scope.delete = (id) => {
            $("#discount").modal("hide");
            const url = `${DISCOUNT_URL}/delete/` + id;
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
                            name: "Tên Voucher",
                        },
                        {
                            id: "salePercent",
                            name: "Phần Trăm Giảm",
                        },
                        {
                            id: "startAt",
                            name: "Ngày khả dụng",
                            // Định dạng ngày bằng AngularJS filter hoặc JavaScript Date
                            formatter: (cell) => {
                                // Sử dụng AngularJS filter
                                // return $filter('date')(cell, 'dd/MM/yyyy HH:mm:ss');
                                if(cell == null) {
                                    return 'Chưa có ngày hủy bỏ';
                                }
                                // Hoặc sử dụng JavaScript Date
                                // return new Date(cell).toLocaleString("en-GB");
                                // Chuyển đổi ngày từ ISO-8601 đến đối tượng Date

                                // Hoặc sử dụng JavaScript Date
                                return new Date(cell).toLocaleString("en-GB");
                            },
                        },
                        {
                            id: "endAt",
                            name: "Ngày hết hạn",
                            // Định dạng ngày bằng AngularJS filter hoặc JavaScript Date
                            formatter: (cell) => {
                                // Sử dụng AngularJS filter
                                // return $filter('date')(cell, 'dd/MM/yyyy HH:mm:ss');
                                if(cell == null) {
                                    return 'Chưa có ngày hủy bỏ';
                                }
                                // Hoặc sử dụng JavaScript Date
                                // return new Date(cell).toLocaleString("en-GB");
                                // Chuyển đổi ngày từ ISO-8601 đến đối tượng Date

                                // Hoặc sử dụng JavaScript Date
                                return new Date(cell).toLocaleString("en-GB");
                            },
                        },
                        {
                            id: "createAt",
                            name: "Ngày khởi tạo",
                            formatter: (cell) => {
                                // Sử dụng AngularJS filter
                                // return $filter('date')(cell, 'dd/MM/yyyy HH:mm:ss');
                                if(cell == null) {
                                    return 'Chưa có ngày hủy bỏ';
                                }
                                // Hoặc sử dụng JavaScript Date
                                // return new Date(cell).toLocaleString("en-GB");
                                // Chuyển đổi ngày từ ISO-8601 đến đối tượng Date

                                // Hoặc sử dụng JavaScript Date
                                return new Date(cell).toLocaleString("en-GB");
                            },
                        },
                        {
                            id: "deleteAt",
                            name: "Ngày hủy bỏ",
                            formatter: (cell) => {
                                // Sử dụng AngularJS filter
                                // return $filter('date')(cell, 'dd/MM/yyyy HH:mm:ss');
                                if(cell == null) {
                                    return 'Chưa có ngày hủy bỏ';
                                }
                                // Hoặc sử dụng JavaScript Date
                                // return new Date(cell).toLocaleString("en-GB");
                                // Chuyển đổi ngày từ ISO-8601 đến đối tượng Date

                                // Hoặc sử dụng JavaScript Date
                                return new Date(cell).toLocaleString("en-GB");
                            },
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
                                // gridjs.h() là khởi tạo một element, 3 tham số chủ yếu (tên thẻ, {className, event}, tội dung thẻ)
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
                                                    $("#discount").modal("show");
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
                    resizable: true,
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

        $scope.create = () => {
            var voucher = angular.copy($scope.form);
            console.log(voucher)
            $http
                .post(DISCOUNT_URL + "/create", voucher)
                .then((resp) => {
                    $scope.data.push(voucher);
                    $scope.errors = {};
                    notification("Thêm thành công", 3000, "right", "top", "success");
                    console.log("Thêm thành công", resp);
                })
                .catch((err) => {
                    console.log(err);
                    ErrorService.setError(err.data.errors);
                    $scope.errors = ErrorService.getError();
                    notification(
                        "ERROR " + err.status + ": Thêm thất bại",
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
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
        });
    });