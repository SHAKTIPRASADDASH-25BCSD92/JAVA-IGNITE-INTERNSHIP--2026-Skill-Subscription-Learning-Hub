
  package com.skills.hub.service.impl;

import com.skills.hub.model.User;
import com.skills.hub.repository.UserRepository;
import com.skills.hub.service.UserService;
import org.springframework.stereotype.Service;

/*
=========================================================
WHAT IS THIS CLASS?
=========================================================
This class contains BUSINESS LOGIC for users.
 Controller calls this
 This class talks to repository
=========================================================
*/

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) {

        // STEP 1: check if email already exists
        User existingUser = userRepo.findByEmail(user.getEmail());

        // STEP 2: if exists → stop process
        if (existingUser != null) {
            throw new RuntimeException("Email already registered: " + user.getEmail());
        }

        // STEP 3: if not → save user to DB
        User savedUser = userRepo.save(user);

        // STEP 4: return saved user
        return savedUser;
    }

    @Override
    public User login(String email, String password) {

        // STEP 1: find user by email
        User user = userRepo.findByEmail(email);

        // STEP 2: if user not found → return null
        if (user == null) {
            return null;
        }

        // STEP 3: check password match
        boolean passwordMatches = user.getPassword().equals(password);

        // STEP 4: if correct → return user
        if (passwordMatches) {
            return user;
        }

        // STEP 5: else → return null
        return null;
    }
}
