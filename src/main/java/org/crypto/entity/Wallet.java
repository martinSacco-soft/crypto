package org.crypto.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Wallet {
	
	private @Id @GeneratedValue	Long id;
	private String url;
	private String logoUrl;
	private String name;
	private String security;
	private String anonimity;
	private String easeOfUse;
	private Boolean hasAttachedCard;
	//private AttachedCard attachedCard;
	private Boolean hasTradingFacilities;
	private Boolean hasVouchersAndOffers;
	//private List<String> walletFeatures;
	//private List<String> coins;
	//private List<String> platforms;
	private String sourceCodeUrl;
	private String validationType;
	private Boolean isUsingOurApi;
	private String affiliateUrl;
	private Boolean recommended;
	private Boolean sponsored;
	private Integer moreCoins;
	//private List<String> coinsToDisplay;
	//private Rating rating;
	private Integer sortOrder;
	private Long currency;
	private Double amount;
}
