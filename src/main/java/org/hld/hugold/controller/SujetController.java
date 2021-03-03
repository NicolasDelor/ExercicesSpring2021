package org.hld.hugold.controller;

import java.util.List;

import org.hld.hugold.NotFoundException;
import org.hld.hugold.entity.Sujet;
import org.hld.hugold.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sujet")
public class SujetController {

	@Autowired
	private SujetService service;

	@PostMapping("/{name}")
	public Sujet addSujet(@PathVariable("name") String name) {
		return service.addSujet(name);
	}

	@DeleteMapping("/{id}")
	public long deleteSujet(@PathVariable("id") String id) {
		return service.deleteSujet(id);
	}

	@GetMapping("/{id}")
	public Sujet getSujet(@PathVariable("id") String id) throws NotFoundException {
		return service.getSujet(id);
	}

	@GetMapping
	public List<Sujet> getSujets() {
		return service.getSujets();
	}

	@PutMapping
	public Sujet updateSujet(@RequestBody Sujet sujet) throws NotFoundException {
		return service.updateSujet(sujet);
	}

}
