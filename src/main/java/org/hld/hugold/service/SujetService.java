package org.hld.hugold.service;

import java.util.ArrayList;
import java.util.List;

import org.hld.hugold.NotFoundException;
import org.hld.hugold.dto.FoundCity;
import org.hld.hugold.entity.Sujet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SujetService {

	private final List<Sujet> sujets = new ArrayList<>();
	@Autowired
	private RestTemplate restTemplate;

	public Sujet addSujet(Sujet sujet) {
		sujet.setCity(findCity(sujet.getZipCode()));
		sujets.add(sujet);
		return sujet;
	}

	public long deleteSujet(String id) {
		final long found = sujets.stream().filter(s -> s.getId().equals(id)).count();
		sujets.removeIf(s -> s.getId().equals(id));
		return found;
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

	public Sujet getSujet(String id) throws NotFoundException {
		return sujets.stream()
				.filter(s -> s.getId().equals(id))
				.findAny()
				.orElseThrow(() -> new NotFoundException("L'objet n'existe pas"));
	}

	public List<Sujet> getSujets() {
		return sujets;
	}

	public Sujet updateSujet(Sujet sujet) throws NotFoundException {
		return sujets.stream().filter(s -> s.getId().equals(sujet.getId())).findAny().map(foundSujet -> {
			sujets.remove(foundSujet);
			foundSujet.setName(sujet.getName());
			foundSujet.setZipCode(sujet.getZipCode());
			foundSujet.setCity(findCity(sujet.getZipCode()));
			sujets.add(foundSujet);
			return foundSujet;
		}).orElseThrow(() -> new NotFoundException("L'objet n'existe pas"));
	}

}
