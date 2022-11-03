package com.ouieat;

import static org.assertj.core.api.Assertions.assertThat;

import com.ouieat.models.User;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import org.apache.logging.log4j.Level;

public class UserTests {

  private final UserRepository userRepository;

  public UserTests(UserRepository givenUserRepository) {
    userRepository = givenUserRepository;
    runTests();
  }

  public void runTests() {
    testRegister();
    testLogin();
  }

  public void testRegister() {
    testRegisterError();
    testRegisterSuccess();
    testRegisterFailure();
    OuiLogger.log(Level.INFO, "Completed Registration Tests");
  }

  public void testRegisterError() {
    OuiLogger.log(Level.INFO, "Testing register error");
    String response = UserRequests.doRegister(
      userRepository,
      new User("", "", "", "", "", "")
    );
    assertThat(response).contains("error");
    OuiLogger.log(Level.INFO, "Testing register error passed");
  }

  public void testRegisterSuccess() {
    OuiLogger.log(Level.INFO, "Testing register success");
    String response = UserRequests.doRegister(
      userRepository,
      new User(
        "TestFirstName",
        "TestLastName",
        "",
        "TestUsername",
        "TEST",
        "alsoTest@gmail.com"
      )
    );
    assertThat(response).contains("success");
    // Don't forget to delete the user from the database after testing
    OuiLogger.log(Level.INFO, "Testing register success passed");
  }

  public void testRegisterFailure() {
    OuiLogger.log(Level.INFO, "Testing register failure");
    // This fails because the username exists in the database
    String response = UserRequests.doRegister(
      userRepository,
      new User("test", "test", "test", "TonytheBony", "test", "test")
    );
    assertThat(response).contains("failure");
    OuiLogger.log(Level.INFO, "Testing register failure passed");
  }

  public void testLogin() {
    testLoginFailure();
    testLoginSuccess();
    testLoginError();
    OuiLogger.log(Level.INFO, "Completed Login Tests");
  }

  public void testLoginError() {
    OuiLogger.log(Level.INFO, "Testing login error");
    String response = UserRequests.doLogin(
      userRepository,
      new UserLogin("", "")
    );
    assertThat(response).contains("error");
    OuiLogger.log(Level.INFO, "Testing login error passed");
  }

  public void testLoginFailure() {
    OuiLogger.log(Level.INFO, "Testing login failure");
    String response = UserRequests.doLogin(
      userRepository,
      new UserLogin("test", "test")
    );
    assertThat(response).contains("failure");
    OuiLogger.log(Level.INFO, "Testing login failure passed");
  }

  public void testLoginSuccess() {
    OuiLogger.log(Level.INFO, "Testing login success");
    String response = UserRequests.doLogin(
      userRepository,
      new UserLogin("TestUsername", "TEST")
    );
    assertThat(response).contains("success");
    OuiLogger.log(Level.INFO, "Testing login success passed");
  }
}
