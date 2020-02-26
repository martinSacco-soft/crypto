package org.crypto.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Rating {
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	private Double average;
	private Integer totalUsers;
}
