


app.controller("ConfirmOtpEmailController", function ($scope, $http, $routeParams, $location) {

    function countdown() {
        var count = 60;
        var countdownElement = document.getElementById("countdown");
        var reloadButton = document.getElementById("reloadButton");

        // Hiển thị đếm ngược ban đầu
        countdownElement.innerHTML = count;

        // Đếm ngược
        var countdownInterval = setInterval(function () {
            count--;
            countdownElement.innerHTML = count;

            // Kiểm tra khi đếm ngược kết thúc
            if (count === 0) {
                clearInterval(countdownInterval);
                reloadButton.style.display = "block"; // Hiển thị nút "Reload"
                document.getElementById("reloadButton").style.display = "none";
            }
        }, 1000);
    }


    function sendOTP(email) {
        var request = {
            url: "/auth/sendConfirmOtpEma",
            method: "POST",
            headers: {
                'Content-Type': 'text/plain'
            },
            data: email
        }
        $http(request).then(
            function (response) {
                console.log(response);
                document.getElementById("mess").innerHTML = "Kiểm tra email " + email + "của bạn";
                document.getElementById("otp").removeAttribute("disabled");
                document.getElementById("btn-confirm").removeAttribute("disabled");
                countdown();

            }
        ).catch(function (response) {
            console.log(response);
            document.getElementById("err_message").innerHTML = "Có lỗi xảy ra trong quá trình gửi mail"
            Swal.fire({
                icon: 'error',
                title: 'Có lỗi xảy ra',
                text: 'Có lỗi xảy ra trong quá trình gửi mail Vui lòng thử lại',
            })

        });
    }



    function confirmOTP() {
        return new Promise(function (resolve, reject) {
            let email = $routeParams.email;
            let otp = $scope.otp;

            var request = {
                url: "/auth/confirmOTP",
                method: "POST",
                params: {
                    email: email,
                    otp: otp
                }
            };

            $http(request).then(function (response) {
                console.log(response);
                resolve(true);
            }).catch(function (response) {
                console.log(response);
                setInvalid('otp', $scope.confirmOTPForm.otp, false);
                showInvalidMess('mess-otp', "Mã OTP không hợp lệ");
                document.getElementById('otp').addEventListener("click", () => {
                    setValid('otp', $scope.confirmOTPForm.otp, true);
                    hideInvalidMess('mess-otp');
                });
                resolve(false);
            });
        });
    }


    $scope.resend = function () {
        sendOTP($routeParams.email);
    }


    $scope.confirm = function () {
        let action = $routeParams.action;
        let email = $routeParams.email;
        if ($scope.confirmOTPForm.$valid) {
            if (action == "register") {
                var userData = JSON.parse(sessionStorage.getItem('registerData'));
                console.log(userData);
                if (userData != null) {
                    confirmOTP().then(function (rs) {
                        if (rs) {
                            console.log("TRUE");
                            var request = {
                                url: "/auth/register",
                                method: "POST",
                                data: userData
                            }
                            $http(request).then(
                                function (response) {
                                    console.log(response);
                                    $scope.success_message = "Đăng ký thành công";
                                    $scope.err_message = "";
                                    sessionStorage.removeItem("registerData");
                                    setTimeout(function () {
                                        $scope.$apply(function () {
                                            $location.path("login")
                                        });
                                    }, 1500);

                                }
                            ).catch(function (response) {
                                if (response.status == 400) {
                                    document.getElementById("err_message").innerHTML = "Thông tin đăng ký không hợp lệ. Vui lòng đăng ký lại"
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Có lỗi xảy ra',
                                        text: 'Thông tin đăng ký không hợp lệ. Vui lòng đăng ký lại',
                                    })
                                } else {
                                    document.getElementById("err_message").innerHTML = "Đăng ký không thành công. Vui lòng đăng ký lại"
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Có lỗi xảy ra',
                                        text: 'Đăng ký không thành công. Vui lòng đăng ký lại',
                                    })
                                }
                            });
                        }
                    });


                }
            } else if (action == "forget") {
                confirmOTP().then(function (rs) {
                    if (rs) {
                        $location.path("forget/confirmed/" + email)
                    }
                });
            }
        }

    }

    let email = $routeParams.email;
    sendOTP(email);

});


