package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crypto.model.BoughtCurrencyDto;
import org.crypto.model.CurrencyDto;
import org.crypto.model.TransferDto;
import org.crypto.model.WalletDto;
import org.json.JSONException;

import java.util.Map;

public interface CryptoService {
	
	Map<String, CurrencyDto> getAllCryptoCurrencies ();
	
	WalletDto createWallet (WalletDto walletDto) throws JSONException;
	
	WalletDto getWallet (Long id) throws JSONException;
	
	WalletDto updateWallet(WalletDto walletDto) throws JSONException;
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
