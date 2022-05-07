package com.sda.stadvancedjr12.models;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Model for User
 *
 * @author Vinod John
 */
@Data
public class User {
  private String firstName;
  private String lastName;
  private String password;

  @Enumerated(EnumType.STRING)
  private UserType userType;
}
