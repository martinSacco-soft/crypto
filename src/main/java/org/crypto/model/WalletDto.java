package org.crypto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crypto.entity.AttachedCard;
import org.crypto.entity.Rating;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletDto {
	
	private Long id;
	private String url;
	private String logoUrl;
	private String name;
	private String security;
	private String anonimity;
	private String easeOfUse;
	private Boolean hasAttachedCard;
	private AttachedCard attachedCard;
	private Boolean hasTradingFacilities;
	private Boolean hasVouchersAndOffers;
	private List<String> walletFeatures;
	private List<String> coins;
	private List<String> platforms;
	private String sourceCodeUrl;
	private String validationType;
	private Boolean isUsingOurApi;
	private String affiliateUrl;
	private Boolean recommended;
	private Boolean sponsored;
	private Integer moreCoins;
	private List<String> coinsToDisplay;
	private Rating rating;
	private Integer sortOrder;
}
