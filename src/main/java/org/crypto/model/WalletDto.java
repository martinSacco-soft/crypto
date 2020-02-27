package org.crypto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class WalletDto {
	
	private Long id;
	private String name;
	private Map<String, BigDecimal> currencies;
}
