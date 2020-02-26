package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.crypto.entity.Wallet;
import org.crypto.model.*;
import org.crypto.repository.WalletRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class CryptoServiceImpl implements CryptoService {
	
	private static final String API_KEY = "64cbdfbcd4038d7f34925c926b1fd24606d62168dc43497bb43111cc289f854e";
	
	private MessageSource messageSource;
	
	private WalletRepository walletRepository;

	private RestTemplate restTemplate;

	@Autowired
	public CryptoServiceImpl(MessageSource messageSource, RestTemplateBuilder builder, WalletRepository walletRepository) {
		this.messageSource = messageSource;
		this.restTemplate = builder.build();
		this.walletRepository = walletRepository;
	}
	
	public Map<String, CurrencyDto> getAllCryptoCurrencies() {
		
		String currencies = restTemplate.exchange("https://min-api.cryptocompare.com/data/blockchain/list?extraParams=crypto&api_key=" + API_KEY,
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}).getBody();
		Map<String, CurrencyDto> map = new HashMap<>();
		
		try {
			JSONObject jsonObject = new JSONObject(currencies);
			JSONObject data = jsonObject.getJSONObject("Data");
			ObjectMapper mapper = new ObjectMapper();
			if(currencies != null) {
				map = mapper.readValue(data.toString(), new TypeReference<Map<String, CurrencyDto>>(){});
			}
		} catch (JSONException | JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		
		return map;
	}
	
	public WalletDto createWallet (WalletDto walletDto) throws JSONException {
		Wallet wallet = mapWallet(walletDto);
		walletRepository.save(wallet);
		walletDto.setId(wallet.getId());
		return walletDto;
	}
	
	public WalletDto getWallet (Long id) throws JSONException {
		WalletDto walletDto = mapWalletDto(walletRepository.getOne(id));
		return walletDto;
	}
	
	public WalletDto updateWallet(WalletDto walletDto) throws JSONException {
		walletDto = mapWalletDto(walletRepository.save(mapWallet(walletDto)));
		return walletDto;
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
	
	private WalletDto mapWalletDto(Wallet wallet) throws JSONException {
		WalletDto walletDto = new WalletDto();
		
		walletDto.setId(wallet.getId());
		walletDto.setUrl(wallet.getUrl());
		walletDto.setLogoUrl(wallet.getLogoUrl());
		walletDto.setName(wallet.getName());
		walletDto.setSecurity(wallet.getSecurity());
		walletDto.setAnonimity(wallet.getAnonimity());
		walletDto.setEaseOfUse(wallet.getEaseOfUse());
		walletDto.setHasTradingFacilities(wallet.getHasTradingFacilities());
		walletDto.setHasVouchersAndOffers(wallet.getHasVouchersAndOffers());
		walletDto.setSourceCodeUrl(wallet.getSourceCodeUrl());
		walletDto.setValidationType(wallet.getValidationType());
		walletDto.setIsUsingOurApi(wallet.getIsUsingOurApi());
		walletDto.setAffiliateUrl(wallet.getAffiliateUrl());
		walletDto.setRecommended(wallet.getRecommended());
		walletDto.setSponsored(wallet.getSponsored());
		walletDto.setMoreCoins(wallet.getMoreCoins());
		walletDto.setSortOrder(wallet.getSortOrder());
		walletDto.setCurrency(wallet.getCurrency());
		walletDto.setAmount(wallet.getAmount());
		
		return walletDto;
	}
	
	private Wallet mapWallet(WalletDto walletDto) throws JSONException {
		Wallet wallet = new Wallet();
		
		wallet.setId(walletDto.getId());
		wallet.setUrl(walletDto.getUrl());
		wallet.setLogoUrl(walletDto.getLogoUrl());
		wallet.setName(walletDto.getName());
		wallet.setSecurity(walletDto.getSecurity());
		wallet.setAnonimity(walletDto.getAnonimity());
		wallet.setEaseOfUse(walletDto.getEaseOfUse());
		wallet.setHasTradingFacilities(walletDto.getHasTradingFacilities());
		wallet.setHasVouchersAndOffers(walletDto.getHasVouchersAndOffers());
		wallet.setSourceCodeUrl(walletDto.getSourceCodeUrl());
		wallet.setValidationType(walletDto.getValidationType());
		wallet.setIsUsingOurApi(walletDto.getIsUsingOurApi());
		wallet.setAffiliateUrl(walletDto.getAffiliateUrl());
		wallet.setRecommended(walletDto.getRecommended());
		wallet.setSponsored(walletDto.getSponsored());
		wallet.setMoreCoins(walletDto.getMoreCoins());
		wallet.setSortOrder(walletDto.getSortOrder());
		wallet.setCurrency(walletDto.getCurrency());
		wallet.setAmount(walletDto.getAmount());
		
		return wallet;
	}
}
