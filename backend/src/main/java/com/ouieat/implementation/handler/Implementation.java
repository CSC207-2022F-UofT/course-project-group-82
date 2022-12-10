package com.ouieat.implementation.handler;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;

public abstract class Implementation<J extends Interactor<?, ?>> {

    public final UserInteractor userInteractor;

    public final J interactor;

    public Implementation(UserInteractor userInteractor, J interactor) {
        this.userInteractor = userInteractor;
        this.interactor = interactor;
    }
}
