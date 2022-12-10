package com.ouieat.requests.handler;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;

public abstract class AuthenticatedRequest<
    T extends Interactor<?, ?>, J extends Implementation<T>
>
    extends Request<T, J> {

    public AuthenticatedRequest(
        UserInteractor userInteractor,
        T interactor,
        J implementation
    ) {
        super(userInteractor, interactor, implementation);
    }

    public Response handle(String userID, Function<User, Response> function) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(userInteractor.findById(userID));
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }

    public <K> Response handle(
        String userID,
        K data,
        FunctionalInterfaces.Function2<User, K, Response> function
    ) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(userInteractor.findById(userID), data);
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }

    public <K, L> Response handle(
        String userID,
        K data,
        L data2,
        FunctionalInterfaces.Function3<User, K, L, Response> function
    ) {
        if (userInteractor.findById(userID) != null) {
            return function.apply(userInteractor.findById(userID), data, data2);
        } else {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }
    }
}
