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

public interface GetFriends {
    static Response apply(UserInteractor interactor, User user) {
        try {
            ArrayList<UserPreview> friends = new ArrayList<>();

            // Loop over the friend ids and get the user preview objects
            for (String friendId : user.getFriendIds()) {
                ArrayList<User> friend = interactor.findUserById(friendId);
                if (friend != null && friend.size() == 1) {
                    friends.add(new UserPreview(friend.get(0)));
                } else {
                    OuiLogger.log(
                        Level.ERROR,
                        "Unable to find friend with id: " + friendId
                    );
                    return ExceptionResponses.ServerExceptionResponse();
                }
            }

            return UserResponses.GetFriendsResponse(friends);
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to get friends: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
