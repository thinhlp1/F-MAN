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


