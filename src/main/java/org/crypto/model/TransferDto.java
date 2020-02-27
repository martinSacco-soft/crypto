package org.crypto.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDto {
	
	private String walletFrom;
	private String walletTo;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal amountFrom;
	private BigDecimal amountTo;
}
