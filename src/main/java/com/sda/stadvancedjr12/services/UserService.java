package com.sda.stadvancedjr12.services;

import com.sda.stadvancedjr12.exceptions.ServiceUnavailableException;
import com.sda.stadvancedjr12.models.User;

/**
 * Service for user related operations
 *
 * @author Vinod John
 */
public interface UserService {
  /**
   * To get user from a remote server
   * @return User
   * @throws ServiceUnavailableException
   */
  User getUserFromRemoteServer() throws ServiceUnavailableException;
}
