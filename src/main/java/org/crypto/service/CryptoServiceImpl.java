package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.crypto.model.*;
import org.json.JSONArray;
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

	private RestTemplate restTemplate;

	@Autowired
	public CryptoServiceImpl(MessageSource messageSource, RestTemplateBuilder builder) {
		this.messageSource = messageSource;
		this.restTemplate = builder.build();
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
	
	public WalletCreatedDto createWallet () throws JSONException, JsonProcessingException {
		WalletCreatedDto wallet = new WalletCreatedDto();
		String wallets = restTemplate.exchange("https://min-api.cryptocompare.com/data/wallets/general?extraParams=crypto&api_key=" + API_KEY,
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}).getBody();
		Map<String, JSONArray> map = new HashMap<>();
		JSONObject jsonObject = new JSONObject(wallets);
		JSONObject data = jsonObject.getJSONObject("Data");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Long newId = 0L;
		if(wallets != null) {
			WalletDto walletDto;
			map = mapper.readValue(data.toString(), new TypeReference<Map<String, JSONArray>>() {});
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry pair = (Map.Entry) iterator.next();
				//walletDto = (WalletDto) pair.getValue();
				walletDto = mapWallet(pair);
				if (walletDto.getId() != null && newId.compareTo(walletDto.getId()) > 0) {
					newId = walletDto.getId();
				}
			}
		}
		if (newId.compareTo(0L) != 0) {
			wallet.setId(newId);
		}
		return wallet;
	}
	
	public WalletDto getWallet (Long id) throws JSONException, JsonProcessingException {
		WalletDto wallet = new WalletDto();
		String wallets = restTemplate.exchange("https://min-api.cryptocompare.com/data/wallets/general?extraParams=crypto&api_key=" + API_KEY,
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}).getBody();
		Map<String, WalletDto> map = new HashMap<>();
		JSONObject jsonObject = new JSONObject(wallets);
		JSONObject data = jsonObject.getJSONObject("Data");
		ObjectMapper mapper = new ObjectMapper();
		if(wallets != null) {
			WalletDto walletDto;
			map = mapper.readValue(data.toString(), new TypeReference<Map<String, WalletDto>>() {});
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry pair = (Map.Entry) iterator.next();
				walletDto = (WalletDto) pair.getValue();
				if (id.compareTo(walletDto.getId()) > 0) {
					wallet = walletDto;
				}
			}
		}
		
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
	
	private WalletDto mapWallet(Map.Entry pair) throws JSONException {
		WalletDto walletDto = new WalletDto();
		JSONObject jsonWallet = new JSONObject(pair.getValue().toString());
		
		walletDto.setId(jsonWallet.getLong("Id"));
		walletDto.setUrl(jsonWallet.getString("Url"));
		walletDto.setLogoUrl(jsonWallet.getString("LogoUrl"));
		/*private String name;
		private String security;
		private String anonimity;
		private String easeOfUse;
		private Boolean hasAttachedCard;
		private AttachedCard attachedCard;
		private Boolean hasTradingFacilities;
		private Boolean hasVouchersAndOffers;
		private List<String> walletFeatures;
		private List<String> coins;
		@JsonIgnore
		private List<String> platforms;
		private String sourceCodeUrl;
		private String validationType;
		private Boolean isUsingOurApi;
		private String affiliateUrl;
		private Boolean recommended;
		private Boolean sponsored;
		private Integer moreCoins;
		private List<String> coinsToDisplay;
		private Rating rating;
		private Integer sortOrder;*/
		
		return walletDto;
	}
}
