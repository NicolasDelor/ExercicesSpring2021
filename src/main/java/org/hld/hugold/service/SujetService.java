package org.hld.hugold.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

import org.hld.hugold.NotFoundException;
import org.hld.hugold.dao.SujetRepository;
import org.hld.hugold.dto.FoundCity;
import org.hld.hugold.dto.SujetDTO;
import org.hld.hugold.entity.Sujet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SujetService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SujetRepository repository;
	@Autowired
	private AuthorService authorService;

	public SujetDTO addSujet(SujetDTO sujet) {
		if (sujet.getAuthorName() == null) {
			throw new InvalidParameterException("No author given");
		} else {
			final Sujet newSujet = new Sujet(sujet.getName(), sujet.getZipCode(),
					authorService.addAuthorIfNotExist(sujet.getAuthorName()));
			newSujet.setCity(findCity(newSujet.getZipCode()));

			return getDtoFromEntity(repository.save(newSujet));
		}
	}

	public long deleteSujet(String id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return 1;
		} else {
			return 0;
		}
	}

	public List<SujetDTO> findByName(String name) {
		return repository.findByName(name).stream().map(this::getDtoFromEntity).collect(Collectors.toList());
	}

	private String findCity(String zipCode) {
		return restTemplate.exchange(
				"https://geo.api.gouv.fr/communes?codePostal=" + zipCode + "&fields=nom&format=json&geometry=centre",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<FoundCity>>() {
				})
				.getBody()
				.stream()
				.findFirst()
				.map(FoundCity::getNom)
				.orElseThrow(() -> new NotFoundException(
						"La ville correspondant au code " + zipCode + " n'a pas été trouvée"));
	}

	public List<SujetDTO> getByCityEndWith(String endWidth) {
		return repository.findByCityEndWith(endWidth).stream().map(this::getDtoFromEntity).collect(Collectors.toList());
	}

	private SujetDTO getDtoFromEntity(Sujet sujet) {
		return SujetDTO.builder()
				.authorName(sujet.getAuthor().getName())
				.city(sujet.getCity())
				.id(sujet.getId())
				.name(sujet.getName())
				.zipCode(sujet.getZipCode())
				.build();
	}

	public SujetDTO getSujet(String id) throws NotFoundException {
		return getDtoFromEntity(
				repository.findById(id).orElseThrow(() -> new NotFoundException("L'objet n'existe pas")));
	}

	public List<SujetDTO> getSujets() {
		return repository.findAll().stream().map(this::getDtoFromEntity).collect(Collectors.toList());
	}

	public SujetDTO updateSujet(SujetDTO sujet) throws NotFoundException {
		return getDtoFromEntity(repository.save(repository.findById(sujet.getId()).map(foundSujet -> {
			foundSujet.setName(sujet.getName());
			foundSujet.setZipCode(sujet.getZipCode());
			foundSujet.setCity(findCity(sujet.getZipCode()));
			return foundSujet;
		}).orElseThrow(() -> new NotFoundException("L'objet n'existe pas"))));
	}

}
