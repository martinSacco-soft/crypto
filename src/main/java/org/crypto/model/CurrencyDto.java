package org.crypto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyDto {
	
	private Long id;
	private String symbol;
	private String partner_symbol;
	private Long data_available_from;
}
