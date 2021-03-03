package org.hld.hugold.entity;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sujet {
	private String id = UUID.randomUUID().toString();
	private String name;

	public Sujet(String name) {
		this.name = name;
	}
}
