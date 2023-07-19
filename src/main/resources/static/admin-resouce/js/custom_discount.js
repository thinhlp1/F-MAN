// Trong page hiện tại
var submitButton = document.getElementById('submitButton');
submitButton.addEventListener('click', function () {
    var selectedDate = document.getElementById('start-date').value;
    var dateInput = window.opener.document.getElementById('start-date');
    dateInput.value = selectedDate;
});
