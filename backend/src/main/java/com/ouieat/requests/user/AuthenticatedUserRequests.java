package com.ouieat.requests.user;

import com.ouieat.implementation.user.UserImplementation;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserRequests
    extends AuthenticatedRequest<UserInteractor> {

    public FunctionalInterfaces.Function2<UserInteractor, User, Response> getDashboard =
        UserImplementation::getDashboard;

    public FunctionalInterfaces.Function2<UserInteractor, User, Response> getFriends =
        UserImplementation::getFriends;

    public FunctionalInterfaces.Function3<UserInteractor, User, String, Response> removeFriend = (
        UserInteractor interactor,
        User loggedInUser,
        String friendID
    ) -> {
        if (interactor.findById(friendID) != null) {
            return UserImplementation.removeFriend(
                interactor,
                loggedInUser,
                interactor.findById(friendID)
            );
        } else {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
    };

    public FunctionalInterfaces.Function3<UserInteractor, User, UpdatedUser, Response> updateUserDetails = (
        UserInteractor interactor,
        User loggedInUser,
        UpdatedUser updatedUser
    ) -> {
        if (
            updatedUser.userId == null &&
            updatedUser.email == null &&
            updatedUser.firstName == null &&
            updatedUser.lastName == null &&
            updatedUser.profilePictureLink == null &&
            updatedUser.username == null
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return UserImplementation.updateUser(
            interactor,
            loggedInUser,
            updatedUser
        );
    };
}
