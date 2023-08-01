app.controller("ChangePasswordController", function ($scope, $http, $routeParams, $location) {

    $scope.changePassword = function () {
        if ($scope.changePassForm.$valid) {


            let password = $scope.password;
            let newPassword = $scope.newPassword;
            let confirmPassword = $scope.confirmPassword;

            let isValidForm = true;

            let email = getCookie("username");


            if (newPassword != confirmPassword) {
                setInvalid('confirmPassword', $scope.changePassForm.confirmPassword, false);
                showInvalidMess('mess-confirmPassword', "Mật khẩu không trùng khớp");
                isValidForm = false;

            }

            if (isValidForm) {

                var request = {
                    method: 'POST',
                    url: "/auth/change-password",
                    data: {
                        username: email,
                        oldPassword: password,
                        newPassword : newPassword
                    }
                };

                console.log("REQUEST");
                console.log(request);

                $http(request)
                    .then(function (response) {
                        console.log(response.data);
                        $scope.success_message = "Mật khẩu đã được đổi";
                        $scope.err_message = "";
                        Swal.fire({
                            icon: 'success',
                            title: 'OK',
                            text: 'Mật khẩu đã được đổi',
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
                            $scope.err_message = "Đổi mật khẩu không thành công. Vui lòng thử lại";
                            Swal.fire({
                                icon: 'error',
                                title: 'Có lỗi xảy ra',
                                text: 'Vui lòng thử lại',
                            })
                        }else if (error.status == 401){
                            $scope.err_message = "Mật khẩu không đúng";
                        }
                    });
            }
        }
    }
});