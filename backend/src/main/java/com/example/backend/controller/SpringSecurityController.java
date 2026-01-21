package com.example.backend.controller;

import com.example.backend.model.LoginRequest;
import com.example.backend.model.LoginResponse;
import com.example.backend.model.User;
import com.example.backend.service.JwtService;
import com.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class SpringSecurityController {

    private final UserService userService;
    private final JwtService jwtService;

    public SpringSecurityController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var userOpt = userService.login(request.getUsername(), request.getPassword());
        System.out.println("sebbeX");

        if (userOpt.isPresent()) {
            var user = userOpt.get();
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getEmail()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Fel användarnamn eller lösenord");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User created = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Användarnamnet finns redan");
        }
    }

    @GetMapping("/test")
    public String test() {
        return "API fungerar!";
    }
}
