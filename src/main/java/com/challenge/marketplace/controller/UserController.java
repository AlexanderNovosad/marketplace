package com.challenge.marketplace.controller;

import com.challenge.marketplace.entity.User;
import com.challenge.marketplace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @GetMapping(value = "/")
    public ResponseEntity<User> getUser(@RequestParam(value = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity deleteUser(@RequestParam(value = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("delete completed successfully");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

}
