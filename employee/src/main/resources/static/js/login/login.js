// login.js
$(document).ready(function() {
    localStorage.clear();
    $('.ad-login-member').click(function() {
        var email = $('#email').val();
        var password = $('#password').val();

        $.ajax({
            //here need to change the url
            url: "http://localhost:8090/api/auth/login",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            data: {
                email: email,
                password: password
            },
            success: function(response) {
                console.log(response);
                console.log("go to index!")
                localStorage.setItem('email', email);
                window.location.href = 'home.html';
            },
            error: function(xhr, status, error) {
                console.log('1111');
                console.error("Error: " + status + " " + error);
                window.location.href = 'index.html';
                window.alert("Wrong Email Id or Password!");
            }
        });
    });
});
