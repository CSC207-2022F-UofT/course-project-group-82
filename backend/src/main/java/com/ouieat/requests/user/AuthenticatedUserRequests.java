package com.ouieat.requests.user;

import com.ouieat.implementation.user.UserImplementation;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserRequests
    extends AuthenticatedRequest<UserInteractor, UserImplementation> {

    public final Function<User, Response> getDashboard = implementation::getDashboard;

    public final Function<User, Response> getFriends = implementation::getFriends;

    public final FunctionalInterfaces.Function2<User, String, Response> removeFriend = (
        User loggedInUser,
        String friendID
    ) -> {
        if (this.interactor.findById(friendID) != null) {
            return implementation.removeFriend(
                loggedInUser,
                interactor.findById(friendID)
            );
        } else {
            return ExceptionResponses.UserErrorResponse(
                "Friend ID is not a friend"
            );
        }
    };

    public final FunctionalInterfaces.Function2<User, UpdatedUser, Response> updateUserDetails = (
        User loggedInUser,
        UpdatedUser updatedUser
    ) -> {
        if (
            updatedUser == null ||
            (
                updatedUser.userId == null &&
                updatedUser.email == null &&
                updatedUser.firstName == null &&
                updatedUser.lastName == null &&
                updatedUser.profilePictureLink == null &&
                updatedUser.username == null
            )
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return implementation.updateUser(loggedInUser, updatedUser);
    };

    @Autowired
    public AuthenticatedUserRequests(
        UserInteractor userInteractor,
        UserInteractor interactor,
        UserImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
