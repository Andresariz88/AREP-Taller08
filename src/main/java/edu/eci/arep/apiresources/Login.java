package edu.eci.arep.apiresources;

import edu.eci.arep.entities.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

@Path("/login")
public class Login {

    private Map<String, User> users = Map.ofEntries(
            entry("admin", new User(1, "admin", "21232F297A57A5A743894A0E4A801FC3")));

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(User user) throws NoSuchAlgorithmException {
        if (users.containsKey(user.getName())) {
            return toMD5(user.getPassword()).equals(users.get(user.getName()).getPassword());
        } else {
            return false;
        }
    }

    private String toMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
}
