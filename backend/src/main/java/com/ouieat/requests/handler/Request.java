package com.ouieat.requests.handler;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;

public abstract class Request<
    T extends Interactor<?, ?>, J extends Implementation<T>
> {

    public final UserInteractor userInteractor;

    public final J implementation;

    public final T interactor;

    public Request(
        UserInteractor userInteractor,
        T interactor,
        J implementation
    ) {
        this.interactor = interactor;
        this.userInteractor = userInteractor;
        this.implementation = implementation;
    }
}
