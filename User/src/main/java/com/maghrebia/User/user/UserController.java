package com.maghrebia.User.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/protected")
    public ResponseEntity<String> protectedRoute() {
        return ResponseEntity.ok("Access granted to ADMIN or MANAGER!");
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
         return new UserDto(
                id,
                "user" + id,                // username
                "user" + id + "@example.com", // email
                "pass" + id                  // password (mock)
        );
    }

    @GetMapping("/{id}/history")
    public List<ServiceUsageDTO> getUserHistory(@PathVariable Long id) {
        return List.of(
                new ServiceUsageDTO("Service A", 10, 5),
                new ServiceUsageDTO("Service B", 3, 30),
                new ServiceUsageDTO("Service C", 1, 90)
        );
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
/*
    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(email, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }*/
}
