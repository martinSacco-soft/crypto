package org.crypto.service;

import org.crypto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CryptoServiceImpl implements CryptoService {
	
	private static final String API_KEY = "64cbdfbcd4038d7f34925c926b1fd24606d62168dc43497bb43111cc289f854e";
	
	private MessageSource messageSource;

	private RestTemplate restTemplate;

	@Autowired
	public CryptoServiceImpl(MessageSource messageSource, RestTemplateBuilder builder) {
		this.messageSource = messageSource;
		this.restTemplate = builder.build();
	}
	
	public List<CurrencyDto> getAllCryptoCurrencies() {


		String currencyDtos = restTemplate.exchange("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR",
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}).getBody();

		return null;
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
