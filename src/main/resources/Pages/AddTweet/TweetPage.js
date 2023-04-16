
const tweetInput = document.getElementById('usernameInput');

class TweetPage{

    constructor() {
    }

    PostTweet (){
        var content = tweetInput.value;

        console.log(content)
        window.location.href='/Pages/TweetsPage/ListTweets.html';
    }


}
