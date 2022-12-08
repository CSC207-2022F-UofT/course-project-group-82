package com.ouieat.controllers;

import com.ouieat.interactor.Interactor;

public abstract class Controller<T extends Interactor<?, ?>> {

    public T interactor;

    public Controller(T interactor) {
        this.interactor = interactor;
    }
}
