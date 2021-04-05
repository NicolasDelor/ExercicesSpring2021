package org.hld.hugold.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SujetDTO {
	private String id;
	private String name;
	private String zipCode;
	private String city;
	private String authorName;
}
