package com.ouieat.requests.handler;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;

public abstract class AuthenticatedRequest<T extends Interactor<?, ?>>
    extends Request<T> {

    public UserInteractor userInteractor;

    public AuthenticatedRequest(UserInteractor userInteractor, T interactor) {
        super(interactor);
        this.userInteractor = userInteractor;
    }

    public Response handle(
        T interactor,
        String userID,
        FunctionalInterfaces.Function2<T, User, Response> function
    ) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(interactor, userInteractor.findById(userID));
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }

    public <K> Response handle(
        T interactor,
        String userID,
        K data,
        FunctionalInterfaces.Function3<T, User, K, Response> function
    ) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(
                interactor,
                userInteractor.findById(userID),
                data
            );
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }

    public <K, L> Response handle(
        T interactor,
        String userID,
        K data,
        L data2,
        FunctionalInterfaces.Function4<T, User, K, L, Response> function
    ) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(
                interactor,
                userInteractor.findById(userID),
                data,
                data2
            );
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }
}
