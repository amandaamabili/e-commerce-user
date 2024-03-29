package com.santander.user.controller;

import com.santander.user.exceptions.UserNotFoundException;
import com.santander.user.model.User;
import com.santander.user.model.UserDTO;
import com.santander.user.services.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userServiceInterface) {
        this.userService = userServiceInterface;
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.get(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteById(@PathVariable String id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Object> save(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.save(userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.update(id, userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}