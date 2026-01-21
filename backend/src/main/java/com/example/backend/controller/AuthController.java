/* package com.example.backend.controller;

import com.example.backend.model.LoginResponse;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // tillåt React att anropa API:t
public class AuthController {

    private final UserService userService;

    // instantiera klassen
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        LoginResponse lr = userService.login(user.getUsername(), user.getPassword());

        if (lr.getResult() == LoginResponse.LoginResult.SUCCESS) {
            return ResponseEntity.ok(lr.getUser());
        } else if (lr.getResult() == LoginResponse.LoginResult.WRONG_PASSWORD) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User exists but password does not match");
        } else if (lr.getResult() == LoginResponse.LoginResult.USER_NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Något gick fel");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Registering user i http: " + user.getUsername());
        System.out.println("Password i http: " + user.getPassword());

        try {
            User registered = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registered);
        } catch (DataIntegrityViolationException e) {
            // Om användarnamnet redan finns
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Användarnamnet finns redan: " + user.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("illegal arg exc S" + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Något gick fel: " + e.getMessage());
        }
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(null); // Returnera 404 om användaren inte hittas
        }
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        List<User> list = userService.getAll();
        return list;
    }

    @GetMapping("/testGetOne/{id}")
    public User testGetOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

} */