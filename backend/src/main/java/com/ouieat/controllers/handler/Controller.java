package com.ouieat.controllers.handler;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.RequestHandler;
import com.ouieat.requests.handler.UnauthenticatedRequest;

public abstract class Controller<
    T extends Interactor<?, ?>,
    J extends AuthenticatedRequest<T, ?>,
    K extends UnauthenticatedRequest<T, ?>
>
    extends RequestHandler<T, J, K> {

    public Controller(
        UserInteractor userInteractor,
        T interactor,
        J authenticatedRequest,
        K unauthenticatedRequest
    ) {
        super(
            userInteractor,
            interactor,
            authenticatedRequest,
            unauthenticatedRequest
        );
    }
}
