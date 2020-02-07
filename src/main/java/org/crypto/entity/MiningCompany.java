package org.crypto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MiningCompany {

	private Long id;
	private String url;
	private String logoUrl;
	private String name;
	private Boolean recommended;
	private Boolean sponsored;
	private String affiliateURL;
	private String country;
	private Rating rating;
	private Integer sortOrder;
}
