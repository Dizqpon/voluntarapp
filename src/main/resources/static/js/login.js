$(document).ready(function() {
    $("#voluntario").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/login/login_vol.html",
            type: "GET",
            success: function(response) {
                $("#contenido").html(response);
            }
        });
    });

    $("#necesitado").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/login/login_nec.html",
            type: "GET",
            success: function(response) {
                $("#contenido").html(response);
            }
        });
    });
});