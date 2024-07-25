package com.learning.scm.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.scm.entities.User;
import com.learning.scm.helpers.AppConstants;
import com.learning.scm.helpers.ResourceNotFoundException;
import com.learning.scm.repositories.UserRepo;
import com.learning.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void DeleteUser(String id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user2);
    }

    @Override
    public Optional<User> UpdateUser(User user) {
        User user2 = userRepo.findById(user.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());

        User save = userRepo.save(user2);

        return Optional.ofNullable(save);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean isUserExists(String userID) {
        User user2 = userRepo.findById(userID).orElse(null);
        return user2 != null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null;
    }

    @Override
    public User saveUser(User user) {
        // userID has to be generated
        String userId = UUID.randomUUID().toString();
        user.setUserID(userId);

        // password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

}
