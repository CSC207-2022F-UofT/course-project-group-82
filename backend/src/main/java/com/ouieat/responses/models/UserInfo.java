package com.ouieat.responses.models;

import com.ouieat.models.User;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class UserInfo extends ResponseData {

    public final String id;

    public final String firstName;
    public final String lastName;

    public final String email;

    public final String profilePictureLink;

    public final String username;

    public final ArrayList<String> friendIds;

    public UserInfo(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.profilePictureLink = user.getProfilePictureLink();
        this.username = user.getUsername();
        this.friendIds = user.getFriendIds();
    }
}
