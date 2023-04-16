package edu.eci.arep.entities;

import java.util.Date;

public class Tweet {
    private int userId;
    private Date timeStamp;
    private String body;

    public Tweet(int userId, String body) {
        this.userId = userId;
        this.timeStamp = new Date();
        this.body = body;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "userId=" + userId +
                ", timeStamp='" + timeStamp + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
