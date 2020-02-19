package org.crypto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachedCard {
	
	private Long id;
	private String name;
	private String logoUrl;
	private String url;
}