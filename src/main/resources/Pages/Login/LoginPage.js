
const usernameInput = document.getElementById('usernameInput');
const passwordInput = document.getElementById('passwordInput');

class LoginPage{

    constructor() {
    }


    signIn() {
    var username = usernameInput.value;
    var password = passwordInput.value;


    console.log(username, password);

    window.location.href='/Pages/TweetsPage/ListTweets.html'
}

}
