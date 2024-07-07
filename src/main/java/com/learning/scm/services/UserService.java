package com.learning.scm.services;

import com.learning.scm.entities.User;
import java.util.*;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> UpdateUser(User user);

    void DeleteUser(String id);

    boolean isUserExists(String userID);

    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();
}
