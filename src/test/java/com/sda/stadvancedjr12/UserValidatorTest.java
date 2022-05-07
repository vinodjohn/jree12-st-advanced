package com.sda.stadvancedjr12;

import com.sda.stadvancedjr12.exceptions.UserValidationException;
import com.sda.stadvancedjr12.models.User;
import com.sda.stadvancedjr12.models.UserType;
import com.sda.stadvancedjr12.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing exceptions
 *
 * @author Vinod John
 */
public class UserValidatorTest {

  //Junit5 test examples
  @Test
  public void givenUser_whenIsAdminUserCalled_shouldExpectException() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    UserValidator userValidator = new UserValidator();

    try {
      userValidator.isAdminUser(user);
    }
    catch (UserValidationException e) {
      String expectedMessage = "User validation failed for user:Vinod John, Error:User admin check has failed!";
      Assertions.assertEquals(expectedMessage, e.getLocalizedMessage());
    }
  }

  @Test
  public void givenUser_whenIsAdminUserCalledFunctionally_shouldExpectException() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    UserValidationException userValidationException = Assertions.assertThrows(UserValidationException.class, () -> new UserValidator().isAdminUser(user));

    String expectedMessage = "User validation failed for user:Vinod John, Error:User admin check has failed!";
    Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());
  }


  //AssertJ examples
  @Test
  public void givenUserWithoutUserType_whenIsAdminUserCalled_shouldExpectNPE() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");

    org.assertj.core.api.Assertions.assertThatThrownBy(() -> new UserValidator().isAdminUser(user))
      .isExactlyInstanceOf(NullPointerException.class)
      .hasNoCause()
      .hasMessageContaining("null");
  }

  @Test
  public void givenUser_whenAdminUserCalled_shouldExpectExceptionAssert() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    org.assertj.core.api.Assertions.assertThatExceptionOfType(UserValidationException.class)
      .isThrownBy(() -> new UserValidator().isAdminUser(user))
      .withNoCause()
      .withMessageContaining("User validation failed for user:Vinod John, Error:User admin check has failed!");
  }

  @Test
  public void givenUserWithoutUserType_whenIsAdminUserCalled_shouldExpectException() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");

    Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() -> new UserValidator().isAdminUser(user));
    Assertions.assertEquals("The user type is null for user:Vinod John", exception.getLocalizedMessage());
  }

  @Test
  public void givenUser_whenIsAdminUserCalled_shouldExpectUserValidationException() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    UserValidationException userValidationException = org.assertj.core.api.Assertions.catchThrowableOfType(
      () -> new UserValidator().isAdminUser(user), UserValidationException.class);

    Assertions.assertEquals("User validation failed for user:Vinod John, Error:User admin check has failed!",
                            userValidationException.getLocalizedMessage());
  }

  //Junit4 example
  /*@Test(expect = UserValidationException.class)
  public void givenUser_whenIsAdminUserCalledFunctionally_shouldExpectException_Junit4() {
    User user = new User();
    user.setFirstName("Vinod");
    user.setLastName("John");
    user.setPassword("123456");
    user.setUserType(UserType.STANDARD);

    new UserValidator().isAdminUser(user);
  }*/
}
