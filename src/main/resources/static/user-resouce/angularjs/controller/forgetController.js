app.controller("ForgetController", function ($scope, $http, $routeParams, $location) {
    $scope.isShowEmail = $routeParams.action == "no-confirm";

    $scope.changePassword = function () {
        let password = $scope.password;
        let confirmPassword = $scope.confirmPassword;

        let isValidForm = true;

        let email = $routeParams.email;


        if (password != confirmPassword) {
            setInvalid('confirmPassword', $scope.passwordForm.confirmPassword, false);
            showInvalidMess('mess-confirmPassword', "Mật khẩu không trùng khớp");
            isValidForm = false;

        }

        if (isValidForm) {

            var request = {
                method: 'POST',
                url: "/auth/forget",
                data: {
                    email: email,
                    newPassword: password
                }
            };

            console.log("REQUEST");
            console.log(request);

            $http(request)
                .then(function (response) {
                    console.log(response.data);
                    $scope.success_message = "Mật khẩu đã được đổi";
                    Swal.fire({
                        icon: 'success',
                        title: 'OK',
                        text: 'Mật khẩu đã được đổi',
                    })
                    $scope.err_message = "";
                    setTimeout(function () {
                        $scope.$apply(function () {
                            window.location = "/auth/account#!login"
                        });
                    }, 1500);
                })
                .catch(function (error) {
                    console.error(error);
                    $scope.err_message = "Đổi mật khẩu không thành công. Vui lòng thử lại";
                    Swal.fire({
                        icon: 'error',
                        title: 'Có lỗi xảy ra',
                        text: 'Vui lòng thử lại',
                    })
                });
        }


    }

    $scope.confirmEmail = function () {
        let email = $scope.email;
        if ($scope.emailForm.$valid) {
            $scope.checkEmailUser = function (email) {
                $http({
                    method: "POST",
                    url: "/auth/checkEmailExist",

                    data: email
                }).then(function (response) {
                    console.log(response)
                    $scope.err_message = "Email này chưa đăng ký tài khoản";

                }).catch(function (response) {
                    console.log(response)
                    $location.path("confirmOTP/forget/" + email)
                })
            }

            $scope.checkEmailUser(email);

        }
    }
});