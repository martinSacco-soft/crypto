package org.crypto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Gambling {

	private Long id;
	private String url;
	private String logoUrl;
	private String name;
	private Boolean recommended;
	private Boolean sponsored;
	private String affiliateUrl;
	private List<String> gameTypes;
	private List<String> coins;
	private List<String> gamblingFeatures;
	private List<String> platforms;
	private String twitter;
	private String reddit;
	private String facebook;
	private String bettingLimits;
	private String averageFee;
	private String fees;
	private Rating rating;
	private Integer sortOrder;
}
