package org.hld.nicolasd.controller;

import com.fasterxml.jackson.annotation.OptBoolean;
import javassist.NotFoundException;
import org.hld.nicolasd.dto.UserDTO;
import org.hld.nicolasd.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping
    public UserDTO update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable("id") String id) {
        return userService.delete(id);
    }
}
