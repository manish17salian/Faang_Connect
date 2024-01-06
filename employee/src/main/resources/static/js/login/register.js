$(document).ready(function () {
    $('#signup').click(function () {
        var email = $('#email').val();
        var username = $('#username').val();
        var password = $('#password').val();
        var code = $('#code').val();


        $.ajax({
            url: "http://localhost:8090/api/auth/register",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            data: {
                email: email,
                username: username,
                password: password,
            },
            success: function (response) {
                console.log("Response:", response);
                window.location.href = 'index.html';

            },
            error: function (xhr, status, error) {
                console.error("Error:", status, error);
            }
        });
    });
});
