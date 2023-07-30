

app.controller("RegisterController", function ($scope, $http, $location) {


    $scope.checkEmailUser = function (email) {
        return $http({
            method: "POST",
            url: "/auth/checkEmailExist",

            data: email
        }).then(function (response) {
            console.log(response)
            return true;
        }).catch(function (response) {
            console.log(response)
            return false;
        })
    }

    $scope.checkNumberPhoneUser = function (numberPhone) {
        return $http({
            method: "POST",
            url: "/auth/checkNumberPhoneExist",
            data: numberPhone

        }).then(function (response) {
            console.log(response)
            return true;
        }).catch(function (response) {
            console.log(response)
            return false;
        })
    }



    $scope.register = function () {
        if ($scope.registerForm.$valid) {
            let name = $scope.name;
            let email = $scope.email;
            let numberPhone = $scope.numberPhone;
            let password = $scope.password;
            let confirmPassword = $scope.confirmPassword;

            let isValidForm = true;

            var promisesCheck = [];

            promisesCheck.push($scope.checkNumberPhoneUser(numberPhone));
            promisesCheck.push($scope.checkEmailUser(email));


            Promise.all(promisesCheck).then(function (results) {
                if (!results[0]) {
                    setInvalid('numberPhone', $scope.registerForm.numberPhone, false);
                    showInvalidMess('mess-numberPhone', "Số điện thoại đã được sử dụng");
                    isValidForm = false;
                };

                if (!results[1]) {
                    setInvalid('email', $scope.registerForm.email, false);
                    showInvalidMess('mess-email', "Email đã được sử dụng");
                    isValidForm = false;

                }


                if (password != confirmPassword) {
                    setInvalid('confirmPassword', $scope.registerForm.confirmPassword, false);
                    showInvalidMess('mess-confirmPassword', "Mật khẩu không trùng khớp");
                    isValidForm = false;

                }

                if (isValidForm) {

                    data = {
                        username: email,
                        name: name,
                        numberPhone: numberPhone,
                        password: password
                    }

                    var request = {
                        method: 'POST',
                        url: "/auth/check-register",
                        data: data
                    };

                    console.log(request);

                    $http(request)
                        .then(function (response) {
                            console.log(response.data);
                            sessionStorage.setItem('registerData', JSON.stringify(data));
                            $location.path("confirmOTP/register/" + email);


                        })
                        .catch(function (error) {
                            console.error(error);
                            $scope.err_message = "Có lỗi xảy ra. Vui lòng thử lại";
                            Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: 'Có lỗi xảy ra. Vui lòng thử lại',
                            })
                        });

                }

            }
            );

        }

    }
});
