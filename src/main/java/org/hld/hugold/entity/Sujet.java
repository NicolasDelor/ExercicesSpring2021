package org.hld.hugold.entity;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sujet {
	private String id = UUID.randomUUID().toString();
	private String name;
	private String zipCode;
	private String city;

	public Sujet(String name, String zipCode) {
		this.name = name;
		this.zipCode = zipCode;
	}
}
