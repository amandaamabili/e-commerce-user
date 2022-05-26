package com.santander.user.controller;

import com.santander.user.model.User;
import com.santander.user.model.UserDTO;
import com.santander.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    void getById(@PathVariable String id) {
        User user = new User(id, "User", "user@gmail.com");
        User save = userRepository.save(user);
        System.out.println(save);
        System.out.println("save");
    }

    @PostMapping
    ResponseEntity<Object> create(@RequestBody UserDTO userDTO) {
        try {
            User user = new User(userDTO);
            User response = userRepository.save(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}