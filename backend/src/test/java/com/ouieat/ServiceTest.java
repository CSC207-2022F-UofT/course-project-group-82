package com.ouieat;

import static org.mockito.Mockito.when;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.interactor.handler.Interactor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ServiceTest<
    L extends Interactor<?, ?>,
    J extends AuthenticatedRequest<L, ?>,
    K extends UnauthenticatedRequest<L, ?>
> {

    @BeforeAll
    public void setup(TestInfo info) {
        OuiLogger.log(
            Level.INFO,
            "--------------------------------------------------"
        );
        OuiLogger.log(Level.INFO, "\n");
        Optional<String> className = info
            .getTestClass()
            .map(Class::getSimpleName);
        OuiLogger.log(
            Level.INFO,
            "Starting Testing " + className.orElse("---- ERROR ----")
        );
    }

    @BeforeEach
    public void beforeEach(TestInfo info) {
        OuiLogger.log(Level.DEBUG, "Testing - " + info.getDisplayName());
    }

    @AfterEach
    public void afterEach(TestInfo info) {
        OuiLogger.log(
            Level.DEBUG,
            "Finished Testing - " + info.getDisplayName()
        );
    }

    @AfterAll
    public void teardown(TestInfo info) {
        Optional<String> className = info
            .getTestClass()
            .map(Class::getSimpleName);
        OuiLogger.log(
            Level.INFO,
            "Finished Testing " + className.orElse("---- ERROR ----")
        );
        OuiLogger.log(Level.INFO, "\n");
        OuiLogger.log(
            Level.INFO,
            "--------------------------------------------------"
        );
    }

    public final UserInteractor userInteractor;

    public final L interactor;

    public J authenticatedRequest;
    public K unauthenticatedRequest;

    public <M extends Implementation<L>> ServiceTest(
        Class<L> interactorClass,
        Class<? extends J> authClass,
        Class<? extends K> unAuthClass,
        Class<M> implementationClass
    ) {
        userInteractor = Mockito.mock(UserInteractor.class);
        when(userInteractor.findById(getTestUserDefault().getId()))
            .thenReturn(getTestUserDefault());
        interactor = Mockito.mock(interactorClass);
        try {
            M implementation = implementationClass
                .getConstructor(UserInteractor.class, interactorClass)
                .newInstance(userInteractor, interactor);
            authenticatedRequest =
                authClass
                    .getConstructor(
                        UserInteractor.class,
                        interactorClass,
                        implementationClass
                    )
                    .newInstance(userInteractor, interactor, implementation);
            unauthenticatedRequest =
                unAuthClass
                    .getConstructor(
                        UserInteractor.class,
                        interactorClass,
                        implementationClass
                    )
                    .newInstance(userInteractor, interactor, implementation);
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
            "##123TEST_USERNAME123##" + id,
            "TEST_PASSWORD",
            "##123TEST_EMAIL123##" + id
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
            "##123TEST_EMAIL123##" + seed
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
