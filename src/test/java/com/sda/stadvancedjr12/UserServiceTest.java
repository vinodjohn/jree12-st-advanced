package com.sda.stadvancedjr12;

import com.sda.stadvancedjr12.exceptions.ServiceUnavailableException;
import com.sda.stadvancedjr12.models.User;
import com.sda.stadvancedjr12.models.UserType;
import com.sda.stadvancedjr12.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Tests for UserService
 *
 * @author Vinod John
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private RestTemplate restTemplate;

  @Test
  public void whenGetUserFromRemoteCalled_ReturnUser() throws ServiceUnavailableException {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    Mockito.when(restTemplate.getForEntity("https://www.google.com/user", User.class))
           .thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

    User resultUser = userService.getUserFromRemoteServer();
    Assertions.assertEquals(user.getFirstName(), resultUser.getFirstName());
    Assertions.assertEquals(user.getUserType(), resultUser.getUserType());
  }

  @Test
  public void whenGetUserFromRemoteCalled_ReturnException() {
    Mockito.when(restTemplate.getForEntity("https://www.google.com/user", User.class))
           .thenThrow(HttpServerErrorException.class);

    org.assertj.core.api.Assertions.assertThatExceptionOfType(ServiceUnavailableException.class)
                                   .isThrownBy(() -> userService.getUserFromRemoteServer())
                                   .withNoCause()
                                   .withMessageContaining("unavailable");
  }
}
