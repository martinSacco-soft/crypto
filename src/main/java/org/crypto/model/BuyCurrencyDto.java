package org.crypto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "BuyCurrencyDto", description = "Buy Currency DTO")
public class BuyCurrencyDto {
	
	@ApiModelProperty(required = false, notes = "Name of the Wallet")
	private String walletName;
	@ApiModelProperty(required = false, notes = "Symbol of the currency to transfer")
	private String currency;
	@ApiModelProperty(required = false, notes = "Amount To buy")
	private BigDecimal amount;
}
