// Disable form submissions if there are invalid fields
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Get the forms we want to add validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();



// funtions to valid form
function setInvalid(name, input, isValid) {
    document.getElementById(name).classList.add('invalid');
    input.$setValidity(name, isValid);
    // document.getElementById('default-' + name).style = 'display: none';
    document.getElementById(name).onkeyup = function () {
        setValid(name, input, true);
        hideInvalidMess("mess-" + name);
    }
} 

function setValid(name, input, isValid) {
    document.getElementById(name).classList.remove('invalid');
    input.$setValidity(name, isValid);
}

function showInvalidMess(name, mess) {
    document.getElementById(name).style = "display: block";
    document.getElementById(name).innerHTML = mess;
}

function hideInvalidMess(name) {
    document.getElementById(name).style = "display: none";
    document.getElementById(name).innerHTML = "";
}




function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function showNotify(notiID) {
    var form = document.getElementById('form');
    form.classList.add('blur');
    var noti = document.getElementById(notiID);
    noti.classList.add('active');
}

function closeNotify(notiID) {
    var form = document.getElementById('form');
    form.classList.remove('blur');
    var noti = document.getElementById(notiID);
    noti.classList.remove('active');
}

function wait(ms) {
    var start = new Date().getTime();
    var end = start;
    while (end < start + ms) {
        end = new Date().getTime();
    }
}

function formatDate(inputDate) {
    var date = new Date(inputDate);
    var day = date.getDate();
    var month = date.getMonth() + 1; // Lưu ý: getMonth() trả về giá trị từ 0 đến 11
    var year = date.getFullYear();
    return day + "/" + month + "/" + year;
  }
  
  function checkAge(birthDate){
    var formattedDate = birthDate.getFullYear() + "-" + (birthDate.getMonth() + 1) + "-" + birthDate.getDate();
    var birthday = new Date(formattedDate);
    var today = new Date();
    var age = today.getFullYear() - birthday.getFullYear();
    var monthDiff = today.getMonth() - birthday.getMonth();
    
    // Nếu ngày sinh trước tháng hiện tại, hoặc cùng tháng nhưng ngày sinh trước ngày hiện tại,
    // thì số tuổi sẽ bị giảm xuống 1
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthday.getDate())) {
        age--;
    }
    
    return age >= 18;
}

function formatCurrencyToVND(amount) {
    // Sử dụng hàm toLocaleString để định dạng số nguyên
    const formattedAmount = amount.toLocaleString('vi-VN');
  
    // Thêm ký hiệu VNĐ vào sau số
    return formattedAmount + ' VNĐ';
  }
  

