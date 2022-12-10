package com.ouieat.requests.handler;

import com.ouieat.interactor.handler.Interactor;

public abstract class Request<T extends Interactor<?, ?>> {

    public T interactor;

    public Request(T interactor) {
        this.interactor = interactor;
    }
}
