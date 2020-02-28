package org.crypto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crypto.entity.AttachedCard;
import org.crypto.entity.Rating;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "WalletDto", description = "Wallet DTO")
public class WalletDto {
	
	@ApiModelProperty(required = false, notes = "ID of the wallet")
	private Long id;
	@ApiModelProperty(required = true, notes = "Name wallet")
	private String name;
	@ApiModelProperty(required = false, notes = "Currency-Amount collection")
	private Map<String, BigDecimal> currencies;
}
