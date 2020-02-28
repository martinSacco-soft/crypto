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
@ApiModel(value = "TransferDto", description = "Transfer DTO")
public class TransferDto {
	
	@ApiModelProperty(required = true, notes = "Name of the Wallet From")
	private String walletFrom;
	@ApiModelProperty(required = true, notes = "Name of the Wallet To")
	private String walletTo;
	@ApiModelProperty(required = true, notes = "Symbol of the Currency From")
	private String currencyFrom;
	@ApiModelProperty(required = true, notes = "Symbol of the Currency To")
	private String currencyTo;
	@ApiModelProperty(required = true, notes = "Amount to transfer")
	private BigDecimal amountFrom;
	@ApiModelProperty(required = false, notes = "Amount Transferred")
	private BigDecimal amountTo;
}
