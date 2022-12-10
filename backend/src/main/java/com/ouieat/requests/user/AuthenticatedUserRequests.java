package com.ouieat.requests.user;

import com.ouieat.implementation.user.UserImplementation;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserRequests
    extends AuthenticatedRequest<UserInteractor, UserImplementation> {

    public FunctionalInterfaces.Function2<UserInteractor, User, Response> getDashboard = (
            interactor1,
            user
        ) ->
        implementation.getDashboard(user);

    public FunctionalInterfaces.Function2<UserInteractor, User, Response> getFriends = (
            interactor1,
            user
        ) ->
        implementation.getFriends(user);

    public FunctionalInterfaces.Function3<UserInteractor, User, String, Response> removeFriend = (
        UserInteractor interactor,
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

    public FunctionalInterfaces.Function3<UserInteractor, User, UpdatedUser, Response> updateUserDetails = (
        UserInteractor interactor,
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
