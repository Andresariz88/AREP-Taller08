package edu.eci.arep.apiresources;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import edu.eci.arep.entities.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/signup")
public class SignUp {

    String accessKey = "ASIAZLK5DHJ2CVXMEZ5T";
    String secretKey = "WGlEjgBpypaLBzDxIj7YoefZCWLMSGgFOnQ1PwP/";
    String sessionToken= "FwoGZXIvYXdzED4aDIskGx7yAJKXRMtdISLXAQ9HU7WH1SOIKGn+n/bNOL3boyywBKVWMp8CmoD+oJ0QJGHGsJKmaNNk1eRwMJ36iV290FqUr4XZDVJx+To+oO0ZosI1kgsKGu/OLbICCjlIb86ztcrDNcxDnepf61oL68x3xrrZkq2NBpIMmN21qJib/Pb0+QNbhmCtJJx+u5nl+ujhTXigOxE/E6hI5GZMw9GB3i4gdzpXSCPfOEdmWmXH3O4I9udD0lolu07ebWGbBrBUbKLUtwDoiIw9Fv/QG5NiNMHOh9boOG3/TB1Zpzg1QGpWcPrkKLiLpqIGMi1dDNz4UpVcPcAZnf5f+DvY7S5sWivSBu2gqmA5fT+F39Rnke5ATBM7a89jKxg=";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String signup (User user){
        AWSSessionCredentials sessionCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(sessionCredentials);

        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_1)
                .build();

        SignUpRequest request = new SignUpRequest().withClientId("7eniknu6hm1qggm03pdvg2ae7l")
                .withUsername(user.getName())
                .withPassword(user.getPassword())
                .withUserAttributes(
                        new AttributeType()
                                .withName("email")
                                .withValue(user.getEmail()));
        return cognitoClient.signUp(request).toString();
    }
}
