package org.crypto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "CurrencyDto", description = "Currency DTO")
public class CurrencyDto {
	
	@ApiModelProperty(required = false, notes = "ID of the currency")
	private Long id;
	@ApiModelProperty(required = false, notes = "Symbol")
	private String symbol;
	@ApiModelProperty(required = false, notes = "Partner Symbol")
	private String partner_symbol;
	@ApiModelProperty(required = false, notes = "Data available From")
	private Long data_available_from;
}
