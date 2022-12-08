package com.ouieat.models.user;

public class UserPreview {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String profilePictureLink;

    public UserPreview(
        String id,
        String firstName,
        String lastName,
        String username,
        String email,
        String profilePictureLink
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.profilePictureLink = profilePictureLink;
    }

    public UserPreview(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profilePictureLink = user.getProfilePictureLink();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }
}
