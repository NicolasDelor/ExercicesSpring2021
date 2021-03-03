package org.hld.hugold.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hld.hugold.NotFoundException;
import org.hld.hugold.entity.Sujet;
import org.springframework.stereotype.Service;

@Service
public class SujetService {

	private final List<Sujet> sujets = new ArrayList<>();

	public Sujet addSujet(String name) {
		final Sujet sujet = new Sujet(name);
		sujets.add(sujet);
		return sujet;
	}

	public long deleteSujet(String id) {
		final long found = sujets.stream().filter(s -> s.getId().equals(id)).count();
		sujets.removeIf(s -> s.getId().equals(id));
		return found;
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
		final Optional<Sujet> found = sujets.stream().filter(s -> s.getId().equals(sujet.getId())).findAny();
		if (found.isEmpty()) {
			throw new NotFoundException("L'objet n'existe pas");
		} else {
			final Sujet foundSujet = found.get();
			sujets.remove(foundSujet);
			foundSujet.setName(sujet.getName());
			sujets.add(foundSujet);
			return foundSujet;
		}
	}

}
