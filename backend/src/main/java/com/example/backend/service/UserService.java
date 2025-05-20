package com.example.backend.service;

import com.example.backend.model.LoginResponse;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        System.out.println("Registering user: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // hasha lösenord
        System.out.println("Hashed password: " + user.getPassword());
        return userRepository.save(user);
    }

    public LoginResponse login(String username, String rawPassword) {
        System.out.println("Logging in user: " + username);
        System.out.println("Raw password: " + rawPassword);     

        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            String realPassword = userOptional.get().getPassword();
            boolean passwordMatches = passwordEncoder.matches(rawPassword, realPassword);

            if (passwordMatches) {
                // all good
                return new LoginResponse(LoginResponse.LoginResult.SUCCESS, userOptional.get());
            } else {
                // password does not match but user exists
                return new LoginResponse(LoginResponse.LoginResult.WRONG_PASSWORD, userOptional.get());
            }
        }
        // user does not exist
        return new LoginResponse(LoginResponse.LoginResult.USER_NOT_FOUND, null);
        //return userRepository.findByUsername(username).filter(user -> passwordEncoder.matches(rawPassword, user.getPassword())); // jämför hash
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Long id) {
        return userRepository.findById(id).orElse(null); // returnera null om inte hittad
        //return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
