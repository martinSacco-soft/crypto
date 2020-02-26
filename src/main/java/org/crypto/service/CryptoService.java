package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crypto.model.BoughtCurrencyDto;
import org.crypto.model.CurrencyDto;
import org.crypto.model.TransferDto;
import org.crypto.model.WalletDto;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface CryptoService {
	
	Map<String, CurrencyDto> getAllCryptoCurrencies ();
	
	WalletDto createWallet (WalletDto walletDto) throws JSONException, JsonProcessingException;
	
	WalletDto getWallet (Long id) throws JSONException, JsonProcessingException;
	
	void updateWallet();
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
