package org.crypto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Currency {

	private Long id;
	private String symbol;
	private String partnerSymbol;
	private Long dataAvailableFrom;
}
