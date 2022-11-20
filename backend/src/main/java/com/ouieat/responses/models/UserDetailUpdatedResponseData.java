package com.ouieat.responses.models;

import com.ouieat.responses.ResponseData;

public class UserDetailUpdatedResponseData extends ResponseData {

    public final UserInfo currentUserInfo;

    public UserDetailUpdatedResponseData(UserInfo currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }
}

