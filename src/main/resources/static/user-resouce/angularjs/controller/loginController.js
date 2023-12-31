


app.controller("LoginController", function ($scope, $http) {
    $scope.login = function () {
        console.log("LOGIN");
        let email = $scope.email;
        let password = $scope.password;

        console.log($scope.loginForm.password.$valid);
        console.log($scope.loginForm.email.$valid);
        if ($scope.loginForm.$valid) {
            console.log("VALID");

            var payload = {
                username: email,
                password: password,
                remember: $scope.rememberMe != null ? true : false
            };

            var request = {
                method: 'POST',
                url: "/auth/login",
                data: payload
            };

            console.log(payload);

            $http(request).then(
                function (response) {
                    console.log("Đăng nhập thành công");
                    console.log(response.data.roleAccess);
                    if (response.data.roleAccess === "User"){
                        
                        window.location = "/home";
                    }else{
                        window.location = "/admin/index";
                    }

                }
            ).catch(function (response) {
                console.log(response);
                if (response.status == 401) {
                    setInvalid('password', $scope.loginForm.password, false);
                    setInvalid('email', $scope.loginForm.email, false);
                    showInvalidMess('mess-password', "Vui lòng kiểm tra lại email hoặc tài khoản");

                    document.getElementById('password').addEventListener("click", () => {

                        setValid('password', $scope.loginForm.password, true)
                        hideInvalidMess('mess-password')
                        setValid('email', $scope.loginForm.email, true)
                        hideInvalidMess('mess-email')
                    })
                    document.getElementById('email').addEventListener("click", () => {

                        setValid('password', $scope.loginForm.password, true)
                        hideInvalidMess('mess-password')
                        setValid('email', $scope.loginForm.email, true)
                        hideInvalidMess('mess-email')
                    })
                } else if (response.status == 500) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Có lỗi xảy ra. Vui lòng thử lại',
                    })
                }

            });

        }

    }
});


