
const tweetInput = document.getElementById('userInput');

class TweetPage{

    constructor() {
    }

    PostTweet (){
        var content = tweetInput.value;

        fetch ('http://localhost:8080/api', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "userId": sessionStorage.getItem('userId'),
                "body": content
            })
        })
            .then(response => {
                window.location.href='/Pages/TweetsPage/ListTweets.html';
                
        });

        
    }


}
