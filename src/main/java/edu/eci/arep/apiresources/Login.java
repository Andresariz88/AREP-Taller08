package edu.eci.arep.apiresources;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.google.gson.Gson;
import edu.eci.arep.entities.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import io.vertx.ext.web.RoutingContext;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

@Path("/login")
public class Login {

    String accessKey = "ASIAZLK5DHJ2CVXMEZ5T";
    String secretKey = "WGlEjgBpypaLBzDxIj7YoefZCWLMSGgFOnQ1PwP/";
    String clientId = "7eniknu6hm1qggm03pdvg2ae7l";
    String sessionToken= "FwoGZXIvYXdzED4aDIskGx7yAJKXRMtdISLXAQ9HU7WH1SOIKGn+n/bNOL3boyywBKVWMp8CmoD+oJ0QJGHGsJKmaNNk1eRwMJ36iV290FqUr4XZDVJx+To+oO0ZosI1kgsKGu/OLbICCjlIb86ztcrDNcxDnepf61oL68x3xrrZkq2NBpIMmN21qJib/Pb0+QNbhmCtJJx+u5nl+ujhTXigOxE/E6hI5GZMw9GB3i4gdzpXSCPfOEdmWmXH3O4I9udD0lolu07ebWGbBrBUbKLUtwDoiIw9Fv/QG5NiNMHOh9boOG3/TB1Zpzg1QGpWcPrkKLiLpqIGMi1dDNz4UpVcPcAZnf5f+DvY7S5sWivSBu2gqmA5fT+F39Rnke5ATBM7a89jKxg=";


    private final Map<String, User> users = Map.ofEntries(
            entry("admin", new User(1, "admin", "21232F297A57A5A743894A0E4A801FC3", "admin@mail.com")),
            entry("andres", new User(2, "andres", "231BADB19B93E44F47DA1BD64A8147F2", "andres@mail.com")));

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user) throws Exception {
        if (users.containsKey(user.getName())) {
            if (toMD5(user.getPassword()).equals(users.get(user.getName()).getPassword())) {
                return generateCognitoJWT(users.get(user.getName()));
            } else {
                return "false";
            }
        } else {
            return "false";
        }
    }

    private String toMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    private String generateCognitoJWT(User authuser) throws Exception {
        AWSSessionCredentials sessionCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(sessionCredentials);


        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_1)
                .build();
        System.out.println(cognitoClient);
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", authuser.getName());
            put("PASSWORD", "Admin*123");
        }};
        System.out.println(authParams);
        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withAuthParameters(authParams);

        InitiateAuthResult authResult = cognitoClient.initiateAuth(authRequest);
        System.out.println("Token: " + authResult.getAuthenticationResult().getAccessToken());
        return authResult.getAuthenticationResult().getAccessToken();
    }


}
