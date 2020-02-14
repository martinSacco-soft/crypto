package org.crypto.service;

import org.crypto.model.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CryptoService {
	
	Map<String, CurrencyDto> getAllCryptoCurrencies ();
	
	WalletCreatedDto createWallet ();
	
	WalletDto getWallet ();
	
	void updateWallet();
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
