package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crypto.model.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CryptoService {
	
	Map<String, CurrencyDto> getAllCryptoCurrencies ();
	
	WalletCreatedDto createWallet () throws JSONException, JsonProcessingException;
	
	WalletDto getWallet (Long id) throws JSONException, JsonProcessingException;
	
	void updateWallet();
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
