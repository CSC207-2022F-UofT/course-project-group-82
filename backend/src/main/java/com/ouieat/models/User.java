package com.ouieat.models;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    private String profilePictureLink;

    private String username;
    private String password;

    private String email;

    private ArrayList<String> postIds;

    private ArrayList<String> friendIds;

    public User(
        String firstName,
        String lastName,
        String profilePictureLink,
        String username,
        String password,
        String email
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureLink = profilePictureLink;
        this.username = username;
        this.password = password;
        this.email = email;
        this.postIds = new ArrayList<String>();
        this.friendIds = new ArrayList<String>();
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(ArrayList<String> postIds) {
        this.postIds = postIds;
    }

    public ArrayList<String> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(ArrayList<String> friendIds) {
        this.friendIds = friendIds;
    }
}
