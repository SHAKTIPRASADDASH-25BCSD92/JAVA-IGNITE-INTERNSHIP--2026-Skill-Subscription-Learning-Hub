package com.skills.hub.service;

import com.skills.hub.model.User;

public interface UserService {

    // Register new user
    User registerUser(User user);

    // Login user
    User login(String email, String password);
}


