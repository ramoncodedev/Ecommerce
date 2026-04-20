package com.ramondev.ecommerce.controller;

import com.ramondev.ecommerce.dto.request.UserRequest;
import com.ramondev.ecommerce.dto.response.UserResponse;
import com.ramondev.ecommerce.mapper.UserMapper;
import com.ramondev.ecommerce.model.entity.User;
import com.ramondev.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = UserMapper.toEntity(request);
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(UserMapper.toResponse(savedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = userService.findAll();
        List<UserResponse> response = users.stream()
                .map(UserMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        User user = UserMapper.toEntity(request);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(UserMapper.toResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
