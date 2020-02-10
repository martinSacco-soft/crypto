package org.crypto.service;

import org.crypto.model.*;

import java.util.ArrayList;
import java.util.List;

public class CryptoServiceImpl {
	
	public List<CurrencyDto> getAllCryptoCurrencies () {
		List<CurrencyDto> currencyDtos = new ArrayList<>();
		
		return currencyDtos;
	}
	
	public WalletCreatedDto createWallet () {
		WalletCreatedDto wallet = new WalletCreatedDto();
	
		return wallet;
	}
	
	public WalletDto getWallet () {
		WalletDto wallet = new WalletDto();
		
		return wallet;
	}
	
	public void updateWallet() {
	
	}
	
	public void removeWallet() {
	
	}
	
	public BoughtCurrencyDto buyCurrency() {
		BoughtCurrencyDto boughtCurrencyDto = new BoughtCurrencyDto();
		
		return boughtCurrencyDto;
	}
	
	public TransferDto transferValues() {
		TransferDto transferDto = new TransferDto();
		
		return transferDto;
	}
}
