package org.crypto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rating {
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	private Double average;
	private Integer totalUsers;
}
