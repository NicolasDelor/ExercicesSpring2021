package org.hld.hugold.controller;

import java.util.List;

import org.hld.hugold.NotFoundException;
import org.hld.hugold.dto.SujetDTO;
import org.hld.hugold.service.SujetService;
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
@RequestMapping("/sujet")
public class SujetController {

	@Autowired
	private SujetService service;

	@PostMapping
	public SujetDTO addSujet(@RequestBody SujetDTO sujet) {
		return service.addSujet(sujet);
	}

	@DeleteMapping("/{id}")
	public long deleteSujet(@PathVariable("id") String id) {
		return service.deleteSujet(id);
	}

	@GetMapping("/city")
	public List<SujetDTO> getByCityEndWith(@RequestParam(name = "endWith") String endWidth) {
		return service.getByCityEndWith(endWidth);
	}

	@GetMapping("/{id}")
	public SujetDTO getSujet(@PathVariable("id") String id) throws NotFoundException {
		return service.getSujet(id);
	}

	@GetMapping
	public List<SujetDTO> getSujets(@RequestParam(name = "name", required = false) String name) {
		return name == null ? service.getSujets() : service.findByName(name);
	}

	@PutMapping
	public SujetDTO updateSujet(@RequestBody SujetDTO sujet) throws NotFoundException {
		return service.updateSujet(sujet);
	}

}
