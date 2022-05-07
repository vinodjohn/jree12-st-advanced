package com.sda.stadvancedjr12.services.implementations;

import com.sda.stadvancedjr12.exceptions.ServiceUnavailableException;
import com.sda.stadvancedjr12.models.User;
import com.sda.stadvancedjr12.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of UserService
 *
 * @author Vinod John
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;

  public User getUserFromRemoteServer() throws ServiceUnavailableException {
    try {
      ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("https://www.google.com/user", User.class);
      return  userResponseEntity.getBody();
    }
    catch (HttpServerErrorException httpServerErrorException) {
      throw new ServiceUnavailableException("Server unavailable!");
    }
  }
}
