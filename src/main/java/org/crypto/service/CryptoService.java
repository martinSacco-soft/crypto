package org.crypto.service;

import org.crypto.model.*;

import java.io.IOException;
import java.util.List;

public interface CryptoService {
	
	List<CurrencyDto> getAllCryptoCurrencies () throws IOException;
	
	WalletCreatedDto createWallet ();
	
	WalletDto getWallet ();
	
	void updateWallet();
	
	void removeWallet();
	
	BoughtCurrencyDto buyCurrency();
	
	TransferDto transferValues();
}
