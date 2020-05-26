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

    if (uname.length >= 5 && uname.length <= 20){
        elemUNError.style.display = 'none';
        isUsernameValid = true;
    }else {
        elemUNError.style.display = 'inline';
    }

    if (pswd.length >= 8 && pswd.length <= 15){
        elemPswdError.style.display = 'none';
        isPswdValid = true;
    }else {
        elemPswdError.style.display = 'inline';
    }

    if (pswd2 === pswd){
        elemPswdEquals.style.display = 'none';
        isPswdsEquals = true
    }else {
        elemPswdEquals.style.display = 'inline';
    }

    if (email.length >= 5){
        isEmailValid = true;
    }

    if(isUsernameValid && isPswdValid && isPswdsEquals && isEmailValid) {
        $('#submit').removeAttr('disabled');
    } else {
        $('#submit').attr('disabled', 'disabled');
    }
}