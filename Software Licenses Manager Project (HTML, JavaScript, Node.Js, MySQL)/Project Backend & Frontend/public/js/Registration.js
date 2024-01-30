document.getElementById('form').addEventListener('submit', function(event) {

    var fullName = document.querySelector('[name="fullName"]').value;
    var phoneNumber = document.querySelector('[name="phoneNumber"]').value;
    var email = document.querySelector('[name="email"]').value;
    var username = document.querySelector('[name="username"]').value;
    var password = document.querySelector('[name="password"]').value;
    var confirmPassword = document.querySelector('[name="confirmPassword"]').value;

    var pattern = /[0-9]/;
    if (!(pattern.test(password))) {
        alert("The password needs to include at least one number: ");
        event.preventDefault();
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        event.preventDefault();
        return;
    }
});