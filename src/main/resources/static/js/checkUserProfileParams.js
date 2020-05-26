function checkUserProfileParams() {
    const currentFirstName = $('#currentFirstName').val();
    const currentLastName = $('#currentLastName').val();
    const newFirstName = $('#newFirstName').val();
    const newLastName = $('#newLastName').val();

    var newFirstNameError = document.getElementById("isNewFirstNameError");
    var newLastNameError = document.getElementById("isNewLastNameError");

    var isFirstNamesChanged = true;
    var isLastNamesChanged = true;

    if (currentFirstName === newFirstName){
        newFirstNameError.style.display = 'inline';
        isFirstNamesChanged = false;
    }else {
        newFirstNameError.style.display = 'none';
    }

    if (currentLastName === newLastName){
        newLastNameError.style.display = 'inline';
        isLastNamesChanged = false;
    }else {
        newLastNameError.style.display = 'none';
    }

    if (isFirstNamesChanged || isLastNamesChanged){
        $('#submit').removeAttr('disabled');
    } else {
        $('#submit').attr('disabled', 'disabled');
    }
}