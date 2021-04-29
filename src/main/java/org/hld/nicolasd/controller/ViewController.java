package org.hld.nicolasd.controller;

import org.hld.nicolasd.entity.User;
import org.hld.nicolasd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

	@Autowired
	private UserService service;

	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("users", service.getUsers());
		model.addAttribute("newUser", new User());
		return "view";
	}

	@GetMapping("/view/{id}")
	public String viewById(@PathVariable String id, Model model) {
		model.addAttribute("user", service.getUser(id));
		return "view-by-id";
	}
}
