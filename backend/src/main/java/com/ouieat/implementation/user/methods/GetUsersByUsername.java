package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserPreview;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.user.UserResponses;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;

public interface GetUsersByUsername {
    static Response apply(UserInteractor interactor, String username) {
        try {
            ArrayList<User> users = interactor.findAllArray();
            ArrayList<UserPreview> filteredUsers = new ArrayList<>();
            for (User user : users) {
                if (user.getUsername().contains(username)) {
                    filteredUsers.add(new UserPreview(user));
                }
            }
            return UserResponses.GetUsersByUsernameResponse(filteredUsers);
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to get users by username: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
