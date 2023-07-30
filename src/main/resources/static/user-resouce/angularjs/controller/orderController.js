app.controller('OrderController', function ($scope, $http, $location) {
    $scope.cancelOrder = function (orderId) {
        console.log(orderId);
        Swal.fire({
            title: 'Hủy đơn hàng',
            text: "Đơn hàng bị hủy sẽ không thể khôi phục",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Hủy đơn'
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/user/orders/cancel/' + orderId;
                var data = "Người dùng hủy đơn";
                var config = {
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                };

                $http.post(url, data, config)
                    .then(function (response) {
                        console.log(response.data);
                        Swal.fire(
                            'Đã hủy',
                            'Đơn hàng của bạn đã được hủy',
                            'success'
                        )
                        document.getElementById("btn-cancel").classList.add("d-none");
                        document.getElementById("span_state").innerHTML = "Đã hủy";
                        document.getElementById("orderNoteLi").classList.remove("d-none");
                        document.getElementById("orderNote").innerHTML = "Người dùng hủy đơn";

                    })
                    .catch(function (error) {
                        console.log(error);
                        alert("Hủy đơn không thành công");
                        Swal.fire(
                            'Có lỗi xảy ra!',
                            'Không thể hủy đơn hàng này',
                            'error'
                        )
                    });
              
            }
        })

    }
});