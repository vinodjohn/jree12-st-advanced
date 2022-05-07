package com.sda.stadvancedjr12.components;

import com.sda.stadvancedjr12.exceptions.UserValidationException;
import com.sda.stadvancedjr12.models.User;
import com.sda.stadvancedjr12.models.UserType;
import com.sda.stadvancedjr12.utils.UserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * To initialize data
 *
 * @author Vinod John
 */
@Component
public class DataInit {

  @PostConstruct
  public void initData() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("12345");
    user.setUserType(UserType.STANDARD);

    UserValidator userValidator = new UserValidator();

    try {
      if (userValidator.isAdminUser(user)) {
        System.out.printf("User: %s is an Admin user%n", user.getFirstName());
      }
    }
    catch (UserValidationException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
