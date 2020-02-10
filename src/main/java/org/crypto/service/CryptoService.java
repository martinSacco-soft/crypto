package org.crypto.service;

import org.crypto.model.*;

import java.util.List;

public interface CryptoService {
	
	List<CurrencyDto> getAllCryptoCurrencies ();
	
	WalletCreatedDto createWallet ();
	
	WalletDto getWallet ();
	
	WalletDto updateWallet();
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
