/**
 * Created by Enver on 11/2/2017.
 */

//PATTERNS
var usernameRegex = /^[a-z]{4,20}$/;
var emailRegex = /^[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]+)*@[a-z0-9]+(\-[a-z0-9]+)*(\.[a-z0-9]+(\-[a-z0-9]+)*)*\.[a-z]{2,4}$/;
var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
var ddMMyyyyRegex = /^([0-9]{2})-([0-9]{2})-([0-9]{4})$/;

//IDs
var usernameId = "username";
var emailId = "email";
var passwordId = "password";
var passwordAgainId = "password_again";
var birthDateId = "birth_date";
var submitBtn = "signupSubmit";


function inputValidate(id) {
    var element = document.getElementById(id);

    if (id == usernameId) {
        validateUsername(element);
    }
    if (id == emailId) {
        validateEmail(element);
    }
    if (id == passwordId) {
        validatePassword(element);
    }
    if (id == passwordAgainId) {
        validatePasswordAgain(element);
    }
    if (id == birthDateId) {
        validateBirthDate(element);
    }

    checkAll();
}


function validateUsername(element) {
    if (checkExp(element.value, usernameRegex)) {
        inputSuccess(element);
    } else {
        inputError(element);
    }
}


function validateEmail(element) {
    if (checkExp(element.value, emailRegex)) {
        inputSuccess(element);
    } else {
        inputError(element);
    }
}


function validatePassword(element) {
    var password = element.value;
    if (checkExp(password, passwordRegex)) {
        inputSuccess(element);
    } else {
        setPlaceholder(passwordId, "[A-Za-z0-9]+-*/_");
        inputError(element);
        alert("Password must contains Uppercase letter, Lowrecasse letter, and special character +-/_.");
    }
    blankInput(passwordAgainId);
}


function validatePasswordAgain(element) {
    var passwordAgain = element.value;
    var password = document.getElementById(passwordId).value;
    if (checkExp(password, passwordRegex)) {
        if (passwordAgain == password) {
            inputSuccess(element);
        } else {
            blankInput(passwordAgainId);
            inputError(element);
            setPlaceholder(passwordAgainId, "Passwords are different");
        }
    } else {
        blankInput(passwordAgainId);
        document.getElementById(passwordId).focus();
    }
}


function checkPasswords() {
    var password = document.getElementById(passwordId).value;
    var passAgain = document.getElementById(passwordAgainId).value;
    if (checkExp(password, passwordRegex)) {
        if (passAgain == password) {
            return true;
        }
    }
    return false;
}

function validateBirthDate(element) {
    var birthDate = element.value;
    if (!isValidFormatDate) {
        inputError(element);
        setPlaceholder(birthDateId, "Valid format: dd-mm-yyyy");
    } else if (!isValidDate(birthDate)) {
        inputError(element);
        setPlaceholder(birthDateId, "Date not valid");
    } else {
        inputSuccess(element);
    }
}

/**
 * If all inputs Ok, function set submit button on enable.
 */
function checkAll() {
    var inputDisabled = false;

    if (!checkExp(document.getElementById(usernameId).value, usernameRegex)) {
        inputDisabled = true;
    }
    if (!checkExp(document.getElementById(emailId).value, emailRegex)) {
        inputDisabled = true;
    }

    if (!checkPasswords()) {
        inputDisabled = true;
    }
    var birthDateElement = document.getElementById(birthDateId);
    var birthDate = birthDateElement.value;
    if (!isValidDate(birthDate)) {
        inputDisabled = true;
    }

    if (inputDisabled) {
        document.getElementById(submitBtn).classList.remove("btn-success");
        document.getElementById(submitBtn).classList.add("btn-danger");
    } else {
        document.getElementById(submitBtn).classList.remove("btn-danger");
        document.getElementById(submitBtn).classList.add("btn-success");
    }
    document.getElementById(submitBtn).disabled = inputDisabled;
}

//HELP FUNCTIONS
function checkExp(exp, re) {
    return re.test(exp);
}


function inputSuccess(element) {
    element.classList.add("success-class");
    element.classList.remove("error-class");
}


function inputError(element) {
    element.classList.add("error-class");
    element.classList.remove("success-class");
}


function blankInput(id) {
    var element = document.getElementById(id);
    element.value = "";
    element.classList.remove("error-class");
    element.classList.remove("success-class");
}


function setPlaceholder(id, text) {
    document.getElementById(id).value = "";
    document.getElementById(id).placeholder = text;
}


function isValidFormatDate(birthDate) {
    if (checkExp(birthDate, ddMMyyyyRegex)) {
        return true;
    } else {
        return false;
    }
}


function isValidDate(birthDate) {
    var valid = true;
    if (!isValidFormatDate(birthDate)) {
        valid = false;
    }
    var bd = birthDate.split("-");
    var day = parseInt(bd[0]);
    var month = parseInt(bd[1]);
    var year = parseInt(bd[2]);
    if (day < 1 || month < 1 || year < 1) {
        valid = false;
    }
    if (month > 12) {
        valid = false;
    }
    if (year > new Date().getFullYear()) {
        valid = false;
    }
    if (year == new Date().getFullYear()) {
        if (month > new Date().getMonth() + 1) {
            valid = false;
        }
        if (month == new Date().getMonth() + 1) {
            if (day > new Date().getDate()) {
                valid = false;
            }
        }
    }
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
        if (day > 31) {
            valid = false;
        }
    }
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        if (day > 30) {
            valid = false;
        }
    }
    if (month == 2) {
        if (day > 29) {
            valid = false;
        }
    }
    return valid;
}