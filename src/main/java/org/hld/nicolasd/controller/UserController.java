package org.hld.nicolasd.controller;

import org.hld.nicolasd.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{user}")
public class UserController {

    @GetMapping(path = "/user")
    public List<User> getUser() {
        List<User> userList = new ArrayList<>();
        return userList;
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return new User(123, "user123", "user123@gmail.fr", "1234");
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteUser(@PathVariable("id") int id, @RequestBody User user) {
        return id;
    }


}

