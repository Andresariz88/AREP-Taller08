package edu.eci.arep.apiresources;

import edu.eci.arep.entities.Tweet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/api")
public class APIGateway {
    private static final String USER_AGENT = "Mozilla/5.0";

    @GET
    public String getTweets() throws IOException {
        URL obj = new URL("http://localhost:8080/tweets");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response + "\n");
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return "";
        }
    }

    @POST
    public void addTweet(String tweet) throws IOException {
        URL obj = new URL("http://localhost:8080/tweets");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(tweet.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }

    @POST
    @Path("signup")
    public String signup(String user) throws IOException {
        URL obj = new URL("http://localhost:8080/signup");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(user.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response + "\n");
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return "";
        }
    }

    @POST
    @Path("/login")
    public String login(String user) throws IOException {
        URL obj = new URL("http://localhost:8080/login");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(user.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response + "\n");
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return "";
        }
    }

}
