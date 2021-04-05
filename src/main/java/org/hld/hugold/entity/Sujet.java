package org.hld.hugold.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "Sujet")
public class Sujet {
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name = "name")
	private String name;

	@Column(name = "zipcode")
	private String zipCode;

	@Column(name = "city")
	private String city;

	@ManyToOne(fetch = FetchType.LAZY)
	private Author author;

	public Sujet(String name, String zipCode, Author author) {
		this.name = name;
		this.zipCode = zipCode;
		this.author = author;
	}
}
