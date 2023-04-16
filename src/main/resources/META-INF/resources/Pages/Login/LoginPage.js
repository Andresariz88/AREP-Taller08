
const usernameInput = document.getElementById('usernameInput');
const passwordInput = document.getElementById('passwordInput');

class LoginPage{

    constructor() {
    }


    signIn() {
    var username = usernameInput.value;
    var password = passwordInput.value;


    console.log(username, password);

    let url = "http://localhost:8080/api/login";
    fetch (url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
                    "id": 1,
                    "name": username,
                    "password": password
                    })
    })
        .then(response => response.json())
        .then(data => {
            if (data != 'false') {
                sessionStorage.setItem('userId', data);
                window.location.href='/Pages/TweetsPage/ListTweets.html';
            }
            
    });
    
    //window.location.href='/Pages/TweetsPage/ListTweets.html';
}

}
