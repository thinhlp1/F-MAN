app.controller("ChangeEmailController", function ($scope, $http, $routeParams, $location) {

    $scope.changePassword = function () {
        if ($scope.changeEmailForm.$valid) {


            let newEmail = $scope.newEmail;

            let isValidForm = true;

            let email = getCookie("username");


            // if (newPassword != confirmPassword) {
            //     setInvalid('confirmPassword', $scope.changePassForm.confirmPassword, false);
            //     showInvalidMess('mess-confirmPassword', "Mật khẩu không trùng khớp");
            //     isValidForm = false;
            //
            // }

            if (isValidForm) {

                var request = {
                    method: 'POST',
                    url: "/auth/change-email",
                    data: {
                        username: email,
                        email: email
                    }
                };

                console.log("REQUEST");
                console.log(request);

                $http(request)
                    .then(function (response) {
                        console.log(response.data);
                        $scope.success_message = "Email đã được đổi";
                        $scope.err_message = "";
                        Swal.fire({
                            icon: 'success',
                            title: 'OK',
                            text: 'Email đã được đổi',
                        })
                        setTimeout(function () {
                            $scope.$apply(function () {
                                window.location = "/home"
                            });
                        }, 1500);
                    })
                    .catch(function (error) {
                        console.error(error);
                        if (error.status == 500){
                            $scope.err_message = "Đổi email không thành công. Vui lòng thử lại";
                            Swal.fire({
                                icon: 'error',
                                title: 'Có lỗi xảy ra',
                                text: 'Vui lòng thử lại',
                            })
                        }else if (error.status == 401){
                            $scope.err_message = "Email không hợp lệ";
                        }
                    });
            }
        }
    }
});