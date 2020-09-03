package com.square.health.service;

import com.square.health.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    User saveAdmin(User user);
}
