package com.ouieat.responses.models;

import com.ouieat.models.UserPreview;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class UsersByUsernameResponseData extends ResponseData {

    public ArrayList<UserPreview> users;

    public UsersByUsernameResponseData(ArrayList<UserPreview> users) {
        this.users = users;
    }
}
