package org.hld.hugold.controller;

import org.hld.hugold.entity.Sujet;
import org.hld.hugold.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

	@Autowired
	private SujetService service;

	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("sujets", service.getSujets());
		model.addAttribute("newSujet", new Sujet());
		return "view";
	}

	@GetMapping("/view/{id}")
	public String viewById(@PathVariable String id, Model model) {
		model.addAttribute("sujet", service.getSujet(id));
		return "view-by-id";
	}
}