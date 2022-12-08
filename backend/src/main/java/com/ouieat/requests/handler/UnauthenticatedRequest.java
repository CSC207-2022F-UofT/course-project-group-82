package com.ouieat.requests.handler;

import com.ouieat.interactor.Interactor;
import com.ouieat.responses.handler.Response;

public abstract class UnauthenticatedRequest<T extends Interactor<?, ?>>
    extends Request<T> {

    public <K> Response handle(
        T interactor,
        K data,
        FunctionalInterfaces.Function2<T, K, Response> function
    ) {
        return function.apply(interactor, data);
    }

    public <K, L> Response handle(
        T interactor,
        K data1,
        L data2,
        FunctionalInterfaces.Function3<T, K, L, Response> function
    ) {
        return function.apply(interactor, data1, data2);
    }
}
