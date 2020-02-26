package org.crypto.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AttachedCard {
	
	private @Id	@GeneratedValue Long id;
	private String name;
	private String logoUrl;
	private String url;
}