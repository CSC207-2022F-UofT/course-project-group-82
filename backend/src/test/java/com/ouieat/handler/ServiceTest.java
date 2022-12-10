package com.ouieat.handler;

import static org.mockito.Mockito.when;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest<
    L extends Interactor<?, ?>,
    J extends AuthenticatedRequest<L>,
    K extends UnauthenticatedRequest<L>
> {

    public UserInteractor userInteractor;

    public L interactor;

    public J authenticatedRequest;
    public K unauthenticatedRequest;

    public ServiceTest(
        Class<L> interactorClass,
        Class<? extends J> authClass,
        Class<? extends K> unAuthClass
    ) {
        userInteractor = Mockito.mock(UserInteractor.class);
        when(userInteractor.findById(getTestUserDefault().getId()))
            .thenReturn(getTestUserDefault());
        interactor = Mockito.mock(interactorClass);
        try {
            authenticatedRequest =
                authClass
                    .getConstructor(UserInteractor.class, interactorClass)
                    .newInstance(userInteractor, interactor);
            unauthenticatedRequest =
                unAuthClass
                    .getConstructor(interactorClass)
                    .newInstance(interactor);
        } catch (
            InstantiationException
            | IllegalAccessException
            | InvocationTargetException
            | NoSuchMethodException e
        ) {
            e.printStackTrace();
        }
    }

    public static User getTestUserDefault() {
        User newUser = new User(
            "TEST_FIRST_NAME",
            "TEST_LAST_NAME",
            "",
            "##123TEST_USERNAME123##",
            "TEST_PASSWORD",
            "##123TEST_EMAIL123##"
        );
        newUser.setId("TEST_USER_ID");
        newUser.setFriendIds(new ArrayList<>(List.of("TEST_FRIEND_ID")));
        return newUser;
    }

    public static User getTestUserFromID(String id) {
        User newUser = new User(
            "TEST_FIRST_NAME",
            "TEST_LAST_NAME",
            "",
            "##123TEST_USERNAME123##" + id.toString(),
            "TEST_PASSWORD",
            "##123TEST_EMAIL123##" + id.toString()
        );
        newUser.setId(id);
        return newUser;
    }

    public static <K> User getTestUserSeeded(K seed) {
        User newUser = new User(
            "TEST_FIRST_NAME",
            "TEST_LAST_NAME",
            "",
            "##123TEST_USERNAME123##" + seed.toString(),
            "TEST_PASSWORD",
            "##123TEST_EMAIL123##" + seed.toString()
        );
        newUser.setId(
            "TEST_USER_ID" + seed + Math.random() * seed.toString().length()
        );
        return newUser;
    }

    public J getAuthenticatedRequest() {
        return authenticatedRequest;
    }

    public K getUnauthenticatedRequest() {
        return unauthenticatedRequest;
    }
}
