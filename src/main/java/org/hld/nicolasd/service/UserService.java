package org.hld.nicolasd.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

import org.hld.nicolasd.NotFoundException;
import org.hld.nicolasd.dao.UserRepository;
import org.hld.nicolasd.dto.FoundCity;
import org.hld.nicolasd.dto.UserDTO;
import org.hld.nicolasd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserRepository repository;
	@Autowired
	private AuthorService authorService;

	public UserDTO addUser(UserDTO user) {
		if (user.getAuthorName() == null) {
			throw new InvalidParameterException("No author given");
		} else {
			final User newUser = new User(user.getName(), user.getZipCode(),
					authorService.addAuthorIfNotExist(user.getAuthorName()));
			newUser.setCity(findCity(newUser.getZipCode()));

			return getDtoFromEntity(repository.save(newUser));
		}
	}

	public long deleteUser(String id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return 1;
		} else {
			return 0;
		}
	}

	public List<UserDTO> findByName(String name) {
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

	public List<UserDTO> getByCityEndWith(String endWidth) {
		return repository.findByCityEndWith(endWidth).stream().map(this::getDtoFromEntity).collect(Collectors.toList());
	}

	private UserDTO getDtoFromEntity(User user) {
		return UserDTO.builder()
				.authorName(user.getAuthor().getName())
				.city(user.getCity())
				.id(user.getId())
				.name(user.getName())
				.zipCode(user.getZipCode())
				.build();
	}

	public UserDTO getUser(String id) throws NotFoundException {
		return getDtoFromEntity(
				repository.findById(id).orElseThrow(() -> new NotFoundException("L'objet n'existe pas")));
	}

	public List<UserDTO> getUsers() {
		return repository.findAll().stream().map(this::getDtoFromEntity).collect(Collectors.toList());
	}

	public UserDTO updateUser(UserDTO user) throws NotFoundException {
		return getDtoFromEntity(repository.save(repository.findById(user.getId()).map(foundUser -> {
			foundUser.setName(user.getName());
			foundUser.setZipCode(user.getZipCode());
			foundUser.setCity(findCity(user.getZipCode()));
			return foundUser;
		}).orElseThrow(() -> new NotFoundException("L'objet n'existe pas"))));
	}

}
