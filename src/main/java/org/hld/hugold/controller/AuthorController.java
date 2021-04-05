package org.hld.hugold.controller;

import org.hld.hugold.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService service;

	@DeleteMapping("/{id}")
	public long deleteSujet(@PathVariable("id") String id) {
		return service.deleteAuthor(id);
	}

}
