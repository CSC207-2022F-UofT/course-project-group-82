package com.ouieat.requests.handler;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;

public class UnauthenticatedRequest<
    T extends Interactor<?, ?>, J extends Implementation<T>
>
    extends Request<T, J> {

    public <K> Response handle(K data, Function<K, Response> function) {
        return function.apply(data);
    }

    public <K, L> Response handle(
        K data1,
        L data2,
        FunctionalInterfaces.Function2<K, L, Response> function
    ) {
        return function.apply(data1, data2);
    }

    public UnauthenticatedRequest(
        UserInteractor userInteractor,
        T interactor,
        J implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
