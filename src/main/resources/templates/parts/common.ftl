<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Advertisements</title>
        <link rel="stylesheet" type="text/css" href="/static/style.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
    <#include "navbar.ftl">
    <div class="container mt-5">
        <#nested>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!-- JavaScript -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script>
        function checkParams() {
            const uname = $('#uname').val();
            const pswd = $('#pswd').val();
            const pswd2 = $('#pswd2').val();
            const email = $('#email').val();
            var elemUNError = document.getElementById("isUsernameError");
            var isUsernameValid = false;
            var elemPswdError = document.getElementById("isPasswordError");
            var isPswdValid = false;
            var elemPswdEquals = document.getElementById("isPswdEquals");
            var isPswdsEquals = false;
            var isEmailValid = false;

            if (uname.length >=5 && uname.length <=20){
                elemUNError.style.display = 'none';
                isUsernameValid = true;
            }else {
                elemUNError.style.display = 'inline';
            }

            if (pswd.length >=8 && pswd.length <=15){
                elemPswdError.style.display = 'none';
                isPswdValid = true;
            }else {
                elemPswdError.style.display = 'inline';
            }

            if (pswd2 == pswd){
                elemPswdEquals.style.display = 'none';
                isPswdsEquals = true
            }else {
                elemPswdEquals.style.display = 'inline';
            }

            if (email.length >=5){
                isEmailValid = true;
            }

            if(isUsernameValid && isPswdValid && isPswdsEquals && isEmailValid) {
                $('#submit').removeAttr('disabled');
            } else {
                $('#submit').attr('disabled', 'disabled');
            }
        }
    </script>
    <script>
        function checkNewEmail() {
            const email = $('#email').val();
            const newEmail = $('#newEmail').val();
            var elemEmailError = document.getElementById("isEmailError");
            var isEmailValid = false;
            var elemNewEmailError1 = document.getElementById("isNewEmailError1");
            var elemNewEmailError2 = document.getElementById("isNewEmailError2");
            var isNewEmailValid = false;
            var areEmailsEqual = false;

            if (email.length >= 5){
                elemEmailError.style.display = "none";
                isEmailValid = true;
            }else {
                elemEmailError.style.display = "inline";
            }

            if (newEmail.length >= 5){
                elemNewEmailError1.style.display = "none";
                isNewEmailValid = true;
            }else {
                elemNewEmailError1.style.display = "inline";
            }

            if (email === newEmail){
                elemNewEmailError2.style.display = 'inline';
            }else {
                elemNewEmailError2.style.display = 'none';
                areEmailsEqual = true;
            }

            if(isNewEmailValid && isEmailValid && areEmailsEqual) {
                $('#submit').removeAttr('disabled');
            } else {
                $('#submit').attr('disabled', 'disabled');
            }
        }
    </script>
    </body>
    </html>
</#macro>