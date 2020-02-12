package org.crypto.service;

import org.crypto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CryptoServiceImpl implements CryptoService {
	
	private static final String API_KEY = "64cbdfbcd4038d7f34925c926b1fd24606d62168dc43497bb43111cc289f854e";
	
	private MessageSource messageSource;
	
	@Autowired
	public CryptoServiceImpl(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public List<CurrencyDto> getAllCryptoCurrencies () throws IOException {
		List<CurrencyDto> currencyDtos = new ArrayList<>();
		URL url = new URL("https://min-api.cryptocompare.com/data/blockchain/list&api_key" + API_KEY);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK) {
			String message = messageSource.getMessage(connection.getResponseMessage(), null, LocaleContextHolder.getLocale());
			/*StreamSupport
					.stream(message, false)
					.map(currency -> new CurrencyDto(currency.getId(), currency.getSymbol(), currency.getPartnerSymbol(),
							currency.getDataAvailableFrom))
					.collect(Collectors.toList());*/
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
