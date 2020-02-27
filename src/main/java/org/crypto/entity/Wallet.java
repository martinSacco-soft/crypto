package org.crypto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

@Data
@Entity
public class Wallet {
	
	private @Id @GeneratedValue	Long id;
	private String name;
	@ElementCollection
	private Map<String, BigDecimal> currencies;
}
