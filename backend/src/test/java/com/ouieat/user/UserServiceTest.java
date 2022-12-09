package com.ouieat.user;

import com.ouieat.OuiLogger;
import com.ouieat.handler.ServiceTest;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.models.user.UserPreview;
import com.ouieat.requests.user.AuthenticatedUserRequests;
import com.ouieat.requests.user.UnauthenticatedUserRequests;
import com.ouieat.responses.handler.Response;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest extends ServiceTest<UserInteractor, AuthenticatedUserRequests, UnauthenticatedUserRequests> {


    public UserServiceTest() {
        super(UserInteractor.class, AuthenticatedUserRequests.class, UnauthenticatedUserRequests.class);
        OuiLogger.log(Level.DEBUG, "Testing User Service Requests");
    }

    @AfterAll
    public void teardown() {
        OuiLogger.log(Level.DEBUG, "Finished Testing User Service Requests");
    }


    //  Route: /register

    @Test
    public void createUserTestNullUserData(){
        Response response = unauthenticatedRequest.handle(interactor, null, unauthenticatedRequest.createUser);
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void createUserTestSuccess(){
        when(interactor.save(any(User.class))).thenReturn(getTestUserDefault());
        Response response = unauthenticatedRequest.handle(interactor, getTestUserDefault(), unauthenticatedRequest.createUser);
        // Valid user should return success
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isEqualTo(getTestUserDefault().getId());
    }

    // Route: /login

    @Test
    public void loginUserTestNullCredentials(){
        Response response = unauthenticatedRequest.handle(interactor, null, unauthenticatedRequest.loginUser);
        // Null user should throw an error
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void loginUserTestInvalidCredentials(){
        // Invalid user should throw an error
        Response response = unauthenticatedRequest.handle(interactor, new UserLogin("INVALID_USERNAME", "INVALID_PASSWORD"), unauthenticatedRequest.loginUser);
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void loginUserTestSuccess(){
        when(interactor.findUserByUsernameAndPassword(getTestUserDefault().getUsername(), getTestUserDefault().getPassword())).thenReturn(new ArrayList<>(List.of(getTestUserDefault())));
        Response response = unauthenticatedRequest.handle(interactor, new UserLogin(getTestUserDefault().getUsername(), getTestUserDefault().getPassword()), unauthenticatedRequest.loginUser);
        // Valid user should return success
        assertThat(response.status).isEqualTo("success");
    }

    // Route: /updateUserDetails

    @Test
    public void updateUserTestNullUpdateUser(){
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), null, authenticatedRequest.updateUserDetails);
        // Null user should throw an error
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void updateUserTestSuccess(){
        when(interactor.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), new UpdatedUser("UPDATE_USERNAME", null, null, null, null, getTestUserDefault().getId()), authenticatedRequest.updateUserDetails);
        // Valid user should return success
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isEqualTo("Updated user");
    }

    // Route: /dashboard

    @Test
    public void getDashboardTestInvalidID(){
        // Non existent user id dashboard request
        Response response = authenticatedRequest.handle(interactor, "INVALID_ID", authenticatedRequest.getDashboard);
        assertThat(response.status).isEqualTo("error");
        assertThat(response.responseData.data).isEqualTo("Invalid user credentials");
    }

    @Test
    public void getDashboardTestSuccess(){
        // Valid user id dashboard request
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), authenticatedRequest.getDashboard);
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isExactlyInstanceOf(UserPreview.class);
    }

    // Route: /getFriends

    @Test
    public void getUserFriendsTestInvalidLoginID(){

        // Non existent user id friends request
        Response response = authenticatedRequest.handle(interactor, "INVALID_ID", authenticatedRequest.getFriends);
        assertThat(response.status).isEqualTo("error");
        assertThat(response.responseData.data).isEqualTo("Invalid user credentials");

    }

    @Test
    public void getUserFriendsSuccess(){
        // Friend Request Query
        when(interactor.findUserById(getTestUserDefault().getFriendIds().get(0))).thenReturn(new ArrayList<>(List.of(getTestUserSeeded(Math.random()))));
        // Valid user id friends request
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), authenticatedRequest.getFriends);
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).asList().hasSize(1);
    }

    // Route: /removeFriend

    @Test
    public void removeFriendTestInvalidLoginID(){
        // Non existent user id friends request
        Response response = authenticatedRequest.handle(interactor, "INVALID_ID", "INVALID_FRIEND_ID", authenticatedRequest.removeFriend);
        assertThat(response.status).isEqualTo("error");
        assertThat(response.responseData.data).isEqualTo("Invalid user credentials");
    }

    @Test
    public void removeFriendTestInvalidFriendID(){
        // Valid user id but not a friend id
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), "INVALID_FRIEND_ID", authenticatedRequest.removeFriend);
        assertThat(response.status).isEqualTo("error");
        assertThat(response.responseData.data).isEqualTo("Friend ID is not a friend");
    }

    @Test
    public void removeFriendTestSuccess(){
        when(interactor.findById(getTestUserDefault().getFriendIds().get(0))).thenReturn(getTestUserFromID(getTestUserDefault().getFriendIds().get(0)));
        // Valid user id and friend id
        Response response = authenticatedRequest.handle(interactor, getTestUserDefault().getId(), getTestUserDefault().getFriendIds().get(0), authenticatedRequest.removeFriend);
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isEqualTo("removed friend");
    }
}
