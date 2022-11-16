package com.ouieat.responses.models;

import com.ouieat.models.User;
import com.ouieat.responses.ResponseData;

public class DashboardResponseData extends ResponseData {

    public final UserInfo currentUserInfo;

    public DashboardResponseData(User user) {
        this.currentUserInfo = new UserInfo(user);
    }
}
