package com.ouieat.responses.models;

import com.ouieat.models.User;
import com.ouieat.responses.ResponseData;

public class UserDetailResponseData extends ResponseData {

    public final UserInfo currentUserInfo;

    public UserDetailResponseData(User user) {
        this.currentUserInfo = new UserInfo(user);
    }
}