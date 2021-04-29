package org.hld.nicolasd.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "Author")
public class Author {
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<User> users;
}
