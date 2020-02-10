package org.crypto.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDto {
	
	private Long walletFrom;
	private Long walletTo;
	private Long currencyFrom;
	private Long currencyTo;
	private Double quantityFrom;
	private Double quantityTo;
}
