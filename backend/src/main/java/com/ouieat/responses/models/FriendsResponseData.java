package com.ouieat.responses.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ouieat.models.UserPreview;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class FriendsResponseData extends ResponseData {

    public final ArrayList<UserPreview> friends;

    public FriendsResponseData(
        @JsonProperty("friends") ArrayList<UserPreview> friends
    ) {
        this.friends = friends;
    }
}
