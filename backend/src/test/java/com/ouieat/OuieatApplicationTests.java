package com.ouieat;

import static org.assertj.core.api.Assertions.assertThat;

import com.ouieat.controllers.RootController;
import com.ouieat.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OuieatApplicationTests {

  @LocalServerPort
  private int port;

  @Autowired
  private RootController controller;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  public void testUserFunctions() throws Exception {
    assertThat(userRepository).isNotNull();
    new UserTests(userRepository);
  }
}
