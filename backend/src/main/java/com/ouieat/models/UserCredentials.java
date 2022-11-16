package com.ouieat.models;

public class UserCredentials {

    String userID;

    private UserCredentials(String userID) {
        this.userID = userID;
    }

    public static UserCredentials fromUserID(String userID) {
        return new UserCredentials(userID);
    }

    public String getUserID() {
        return userID;
    }
}
