package org.hld.nicolasd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private String id;
	private String name;
	private String zipCode;
	private String city;
	private String authorName;
}
