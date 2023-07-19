$(document).ready(function() {
    $("#show-alert").fadeTo(3000, 500).slideUp(500, function(){
        $("#show-alert").alert('close');
    });
});