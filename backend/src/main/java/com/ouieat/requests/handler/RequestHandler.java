package com.ouieat.requests.handler;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;

public abstract class RequestHandler<
    L extends Interactor<?, ?>,
    J extends AuthenticatedRequest<L>,
    K extends UnauthenticatedRequest<L>
> {

    public UserInteractor userInteractor;

    public L interactor;

    public J authenticatedRequest;
    public K unauthenticatedRequest;

    public RequestHandler(
        UserInteractor userInteractor,
        L interactor,
        J authenticatedRequest,
        K unauthenticatedRequest
    ) {
        this.userInteractor = userInteractor;
        this.interactor = interactor;
        this.authenticatedRequest = authenticatedRequest;
        this.unauthenticatedRequest = unauthenticatedRequest;
    }
}
