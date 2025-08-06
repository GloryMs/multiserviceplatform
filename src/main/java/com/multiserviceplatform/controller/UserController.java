package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.UserDTO;
import com.multiserviceplatform.dto.ProfileDTO;
import com.multiserviceplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<ProfileDTO> createProfile(@PathVariable Integer userId,
                                                    @Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO createdProfile = userService.createProfile(userId, profileDTO);
        return ResponseEntity.status(201).body(createdProfile);
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ProfileDTO> getProfileByUserId(@PathVariable Integer userId) {
        ProfileDTO profile = userService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}
