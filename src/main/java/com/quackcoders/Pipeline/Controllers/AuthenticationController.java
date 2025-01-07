package com.quackcoders.Pipeline.Controllers;

import com.quackcoders.Pipeline.Model.User;
import com.quackcoders.Pipeline.Security.JwtUtil;
import com.quackcoders.Pipeline.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        // Find user by email
        User user = userService.getUserByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new RuntimeException("Invalid email or password " + loginRequest.getEmail());
        }

        System.out.println("Hashed password retrieved for login: " + user.getPassword());

        // Validate password using BCryptPasswordEncoder
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password " + loginRequest.getPassword());
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

        // Return token
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);
        return response;
    }
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User registerRequest, @RequestHeader("Authorization")String token) {
        String role = jwtUtil.extractRole(token.substring(7)); //
        if (!"MANAGER".equals(role)) {
            throw new RuntimeException("Only managers can create accounts");
        }

        // Check if the email already exists
        if (userService.emailExists(registerRequest.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Hash the password before saving
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Save the new user
        User newUser = userService.createUser(registerRequest);

        // Return a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", newUser.getId());
        return response;

    }

}
