package ds.controller;

import ds.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        boolean success = authService.login(email, password);

        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Login failed, email or password incorrect");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        boolean success = authService.register(username, password, email);

        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("message", "Registration successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Registration failed, username or email may already be in use");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
