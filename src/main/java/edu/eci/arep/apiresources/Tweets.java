package edu.eci.arep.apiresources;

import com.google.gson.Gson;
import edu.eci.arep.entities.Tweet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/tweets")
public class Tweets {

    private List<Tweet> cache = new ArrayList<>();

    @GET
    public String getTweets() {
        Gson gson = new Gson();
        if (cache.size() >= 10) {
            return gson.toJson(cache.subList(cache.size() - 10, cache.size()));
        }
        return gson.toJson(cache);
    }

    @POST
    public void createTweet(Tweet tweet) {
        cache.add(tweet);
    }
}
