app.controller(
    "OrderController",
    function ($scope, $http, DataService, $routeParams) {
        const ODER_URL = "http://localhost:8080/admin/orders";
        const ORDER_LIST_URL = `${ODER_URL}/list`;
        const ORDER_APPROVE_LIST_URL = `${ODER_URL}/approve-list`;

        const ORDER_DETAILS_URL = `${ODER_URL}/details`;


        //Khởi tạo các biến toàn cục
        $scope.id = "";
        $scope.data = DataService.getData(); //Chứa danh sách tất cả đối tượng (Category)
        $scope.order;
        $scope.uri = "";
        /*NOTE: Mục đích của tạo Service là truyền dữ liệu qua lại giữa các Controller*/

        $scope.loadApproveList = () => {
            return $http
                .get(ORDER_APPROVE_LIST_URL)
                .then((resp) => {
                    console.log(resp);
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

        $scope.load = () => {
            return $http
                .get(ORDER_LIST_URL)
                .then((resp) => {
                    console.log(resp);
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

        $scope.loadDetails = () => {
            console.log(ORDER_DETAILS_URL);
            return $http
                .get(ORDER_DETAILS_URL + "/" + $routeParams.id)
                .then((resp) => {
                    console.log(resp);
                    $scope.order = resp.data;

                    if ($scope.order.orderState.id === "PENDING_APPROVAL" || $scope.order.orderState.id === "WAIT_PAYMENT") {

                    } else {
                        document.getElementById("btnGroup").remove();
                    }


                })
                .catch((err) => {
                    console.log(err);
                    notification(
                        "ERROR " + err.status + ": " + err.data.message,
                        3000,
                        "right",
                        "top",
                        "error",
                    );
                });
        };

        $scope.checkQuantity = function () {
            var listStockQuantity = document.querySelectorAll('p[id^="stockQuantity_"]');
            var rs;
            try {
                listStockQuantity.forEach(function (item) {

                    id = item.id.split('_')[1]
                    var stockQuantiy = parseInt(item.getAttribute("value"));
                    var itemQuantity = parseInt(document.getElementById("itemQuantity_" + id).getAttribute("value"));
                    console.log(stockQuantiy);
                    console.log(itemQuantity);
                    if (stockQuantiy < itemQuantity) {
                        rs = (confirm("Số lượng tồn kho không đủ cho đơn hàng này. Chắc chắn duyệt"))
                        throw new Error("Break the loop.")
                    }
                })
            } catch (error) {

            }
            if (rs === undefined) {
                return true;
            } else {
                return rs;
            }
        }


        $scope.loadTaskbar = function(){
            $http
            .get("/admin/componet-data")
            .then((resp) => {
                $scope.orderApproveQuantity = resp.data.orderApprove;
                document.getElementById("quantityOrderApprove").innerHTML =    $scope.orderApproveQuantity;
            })
            .catch((err) => {
                notification(
                    "ERROR " + err.status + ": Lỗi tải dữ liệu",
                    3000,
                    "right",
                    "top",
                    "error",
                );
            });
        }


        $scope.cancel = function () {
            var note = $scope.note;
            if (note === "") {
                note = "Không có lý do";
            }
            console.log(note);
            $http({
                method: 'POST',
                url: '/admin/orders/cancel/' + $routeParams.id,
                data: note,
                headers: {
                    'Content-Type': 'text/plain'
                }
            }).then(function successCallback(response) {
                // Xử lý phản hồi từ Controller (nếu có)
                console.log(response.data);

                $('#cancelModel').modal('hide');
                $scope.loadDetails();
                $scope.loadTaskbar();


            }).catch(function errorCallback(response) {
                // Xử lý lỗi (nếu có)
                console.log(response.data);
                notification(
                    "ERROR " + err.status + ": " + err.data.message,
                    3000,
                    "right",
                    "top",
                    "error",
                );
            });
        }

        $scope.approve = function () {
            // if (checkQuantity() == false) {
            //     return;
            // }

            $http({
                method: 'POST',
                url: '/admin/orders/approve/' + $routeParams.id
            }).then(function successCallback(response) {
                // Xử lý phản hồi từ Controller (nếu có)
                console.log(response.data);
                notification("Đơn hàng đã được duyệt", 3000, "right", "top", "success");

                $scope.loadDetails();
                $scope.loadTaskbar();

            }).catch(function errorCallback(err) {
                // Xử lý lỗi (nếu có)
                console.log(err.data);
                notification(
                    "ERROR " + err.status + ": " + err.data.message,
                    3000,
                    "right",
                    "top",
                    "error",
                );
            });
        };


        //Khởi tạo table GRIDJS
        $scope.initGrid = (data) => {
            //Kiểm tra có tồn tại thẻ gốc có id = 'grid' để đặt table hay không
            //Nếu tồn tại xóa tất cả element bên trong
            console.log(data);
            const container = document.getElementById("grid");
            container.innerHTML = "";
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
                            name: "Đơn hàng",
                        },
                        {
                            id: "createAtString",
                            name: "Ngày tạo",
                            sort: {
                                compare: (dateStr1, dateStr2) => {
                                    const date1 = parseDate(dateStr1);
                                    const date2 = parseDate(dateStr2);

                                    if (date1 < date2) {
                                        return -1;
                                    } else if (date1 > date2) {
                                        return 1;
                                    } else {
                                        return 0;
                                    }
                                }
                            }
                        },
                        {
                            id: "receiver",
                            name: "Người nhận",
                            data: (row) => row.user.name,
                            sort: false
                        },
                        {
                            id: "address",
                            name: "Địa chỉ",
                            // with: 
                            data: (row) => row.address.receiverName + " - " + row.address.numberPhone + " - " + row.address.address,
                            sort: false

                        },
                        {
                            id: "totalStringVND",
                            name: "Tổng tiền",
                            sort: {
                                compare: (a, b) => {
                                    a = convertCurrencyStringToInteger(a);
                                    b = convertCurrencyStringToInteger(b)
                                    if (a > b) {
                                        return 1;
                                    } else if (b > a) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                }
                            }
                        },
                        {
                            id: "orderState",
                            name: "Trạng thái",
                            data: (row) => row.orderState.name,
                            sort: false

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
                                            onClick: () => window.location = "/admin/index#!order-detail/" + $scope.uri + `${row.cells[0].data}`
                                        },
                                        gridjs.html('<i class="fa fa-eye" aria-hidden="true"></i>'),
                                    ),
                                );
                            },
                            sort: false,

                        },
                    ],
                    data: data, //Data được truyền vào (JSON, ARRAY)
                    sort: true, //cho phép thêm plugin sắp xếp vào bảng
                    pagination: {
                        //cho phép thêm plugin phân trang vào bảng
                        limit: 10, // Giới hạn số item trong bảng
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
            // $scope.grid.render();
            console.log($scope.grid);
        };



        $scope.insertFilterDataFilter = function (orderStateId) {
            let taskBar = document.getElementsByClassName("gridjs-search").item(0);
            // Tạo phần tử button với các thuộc tính và nội dung tương ứng
            const dropdownSpan = document.createElement("span");
            dropdownSpan.className = "dropdown";

            const button = document.createElement("button");
            button.className = "btn bg-transparent dropdown-toggle mx-4";
            button.type = "button";
            button.id = "btn-orderState";
            button.setAttribute("data-bs-toggle", "dropdown");
            button.setAttribute("aria-expanded", "false");
            button.setAttribute("style", "padding: 9px 20px; background-color: #fff !important; border: 1px solid #d2d6dc");
            button.innerHTML = "Trạng thái";

            // Tạo phần tử ul (unordered list) với class "dropdown-menu"
            const dropdownMenu = document.createElement("ul");
            dropdownMenu.className = "dropdown-menu";
            dropdownMenu.setAttribute("aria-labelledby", "sort");
            dropdownMenu.style.margin = "0px";

            // Tạo các phần tử li và a cho dropdownMenu
            const createDropdownItem = (onClickFunction, text, value) => {
                const li = document.createElement("li");
                li.setAttribute("value", value)
                const a = document.createElement("a");
                a.className = "dropdown-item";

                a.onclick = onClickFunction;
                a.textContent = text;
                li.appendChild(a);
                return li;
            };
            const li = createDropdownItem(() => { filterOrdersByOrderStateId("ALL") }, "Tất cả", "ALL");

            const li1 = createDropdownItem(() => { filterOrdersByOrderStateId("PENDING_APPROVAL") }, "Đang chờ duyệt", "PENDING_APPROVAL");
            const li2 = createDropdownItem(() => { filterOrdersByOrderStateId("WAIT_PAYMENT") }, "Đang chờ thanh toán", "WAIT_PAYMENT");
            const li3 = createDropdownItem(() => { filterOrdersByOrderStateId("ORDER_IS_SHIPPING") }, "Đang vận chuyển", "ORDER_IS_SHIPPING");
            const li4 = createDropdownItem(() => { filterOrdersByOrderStateId("ORDER_CANCEL") }, "Đơn đã hủy", "ORDER_CANCEL");

            // const li2 = createDropdownItem(()=>{filterOrdersByOrderStateId("ORDER_CANCEL")}, "Name");

            // Thêm các phần tử li vào phần tử ul (dropdownMenu)
            dropdownMenu.appendChild(li);

            dropdownMenu.appendChild(li1);
            dropdownMenu.appendChild(li2);

            dropdownMenu.appendChild(li3);

            dropdownMenu.appendChild(li4);


            // Thêm các phần tử con vào dropdownSpan
            dropdownSpan.appendChild(button);
            dropdownSpan.appendChild(dropdownMenu);

            dropdownMenu.setAttribute("value", orderStateId)


            taskBar.append(dropdownSpan)


        }


        function filterOrdersByOrderStateId(orderStateId) {
            // Sử dụng hàm filter để lọc các phần tử có id của orderState bằng với orderStateId cần tìm
            let filteredOrders;
            if (orderStateId === "ALL"){
                filteredOrders = $scope.data;
            }else{
                filteredOrders = $scope.data.filter((order) => order.orderState.id === orderStateId);

            }
            // $scope.initGrid(filteredOrders)
            $scope.updateTable(filteredOrders)
            $scope.insertFilterDataFilter(orderStateId);
        }

        function convertCurrencyStringToInteger(currencyString) {
            // Loại bỏ tất cả các ký tự không phải số từ chuỗi
            const numericString = currencyString.replace(/[^\d]/g, '');

            // Chuyển đổi chuỗi số thành số nguyên
            const integerValue = parseInt(numericString, 10);

            return integerValue;
        }

        function parseDate(str) {
            const [dateStr, timeStr] = str.split(" ");
            const [day, month, year] = dateStr.split("/");
            const [hours, minutes] = timeStr.split(":");
            return new Date(`${year}-${month}-${day}T${hours}:${minutes}`);
        }

        $scope.back = function () {
            let uri = $routeParams.approve;
            if (uri !== undefined) {
                uri = "-" + uri.replace("/", "");
            } else {
                uri = "";
            }
            window.location = "/admin/index#!order" + uri
        }
        //updateTable dùng để cập nhật lại dữ liệu cho bảng GRIDJS mỗi khi thêm, sửa hoặc xóa
        $scope.updateTable = (data) => {
            // Cập nhật lại dữ liệu trên Grid.js
            $scope.grid.config.plugin.remove("pagination");
            $scope.grid.config.plugin.remove("search");
            $scope.grid.updateConfig({ data: data }).forceRender();
        };

        $scope.loadAppList = function () {
            $scope.uri = "approve/"
            $scope.loadApproveList().then(() => {
                $scope.initGrid($scope.data);
            });
            
        }

        $scope.loadTaskbar();

        //Call Function
        $scope.loadList = function () {
            $scope.load().then(() => {
                $scope.initGrid($scope.data);
                $scope.insertFilterDataFilter();
            });
        }
    },
);
