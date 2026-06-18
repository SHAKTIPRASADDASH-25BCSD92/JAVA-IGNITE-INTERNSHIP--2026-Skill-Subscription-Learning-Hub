package com.skills.hub.service;

import com.skills.hub.model.User;

public interface UserService {

    // Register new user
    User registerUser(User user);

    // Login user
    User login(String email, String password);
}


package com.skills.hub.service;

import com.skills.hub.model.User;
import com.skills.hub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        User existing = userRepository.findByEmail(user.getEmail());
        if (existing != null) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}
