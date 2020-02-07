package org.crypto.entity;

import java.util.List;

public class Exchange {

	private Long id;
	private String name;
	private String url;
	private String logoUrl;
	private List<String> itemType;
	private String centralizationType;
	private String internalName;
	private String gradePoints;
	private String grade;
	private GradePointsSplit gradePointsSplit;
	private String affiliateUrl;
	private String country;
	private Boolean orderBook;
	private Boolean trades;
	private String description;
	private String fullAddress;
	private String fees;
	private String depositMethods;
	private String withdrawalMethods;
	private Boolean sponsored;
	private Boolean recommended;
	private Rating rating;
	private Integer sortOrder;
	private TotalVolume24H totalVolume24H;
	private DisplayTotalVolume24H displayTotalVolume24H;
}
