package org.crypto.service;

import org.crypto.model.BuyCurrencyDto;
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
	
	WalletDto removeWallet(WalletDto walletDto) throws JSONException;
	
	BuyCurrencyDto buyCurrency(BuyCurrencyDto buyCurrencyDto) throws JSONException;
	
	TransferDto transferValues(TransferDto transferDto) throws JSONException;
}
