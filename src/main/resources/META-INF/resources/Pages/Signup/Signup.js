
const usernameInput = document.getElementById('usernameInput');
const emailInput = document.getElementById('emailInput');
const passwordInput = document.getElementById('passwordInput');


class Signup{

    constructor(props){
    }

    signUp(){
        var username = usernameInput.value;
        var email = emailInput.value;
        var password = passwordInput.value;

        let url = "http://localhost:8080/api/signup";
        fetch( url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "id": 1,
                "name": username,
                "email": email,
                "password": password
            })
            })
            .then(() => {
                window.location.href='/Pages/Login/Login.html';
            }
        );
    }
}