package org.crypto.service;

import org.crypto.model.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class CryptoServiceImpl {
	
	private static final String API_KEY = "64cbdfbcd4038d7f34925c926b1fd24606d62168dc43497bb43111cc289f854e";
	
	public List<CurrencyDto> getAllCryptoCurrencies () throws IOException {
		List<CurrencyDto> currencyDtos = new ArrayList<>();
		URL url = new URL("https://min-api.cryptocompare.com/data/blockchain/list?" + API_KEY);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK) {
			currencyDtos = (List<CurrencyDto>) new InputStreamReader(connection.getInputStream());
		}
		
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
