package com.learning.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.scm.entities.User;

// this interface is used used to interact with database
@Repository
public interface UserRepo extends JpaRepository<User, String> {

    // method define karne ki zarurat nahi hai, spring khud bana dega, bas standard
    // me likhna hai,
    // jaise findBy[name_of_field], starting me findBy rahega, then add the name of
    // the attribute by which you want to find
    Optional<User> findByEmail(String email);
}
