package org.hld.nicolasd.controller;

import java.util.List;

import org.hld.nicolasd.NotFoundException;
import org.hld.nicolasd.dto.UserDTO;
import org.hld.nicolasd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public UserDTO addUser(@RequestBody UserDTO user) {
		return service.addUser(user);
	}

	@DeleteMapping("/{id}")
	public long deleteUser(@PathVariable("id") String id) {
		return service.deleteUser(id);
	}

	@GetMapping("/city")
	public List<UserDTO> getByCityEndWith(@RequestParam(name = "endWith") String endWidth) {
		return service.getByCityEndWith(endWidth);
	}

	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable("id") String id) throws NotFoundException {
		return service.getUser(id);
	}

	@GetMapping
	public List<UserDTO> getUsers(@RequestParam(name = "name", required = false) String name) {
		return name == null ? service.getUsers() : service.findByName(name);
	}

	@PutMapping
	public UserDTO updateUser(@RequestBody UserDTO user) throws NotFoundException {
		return service.updateUser(user);
	}

}
