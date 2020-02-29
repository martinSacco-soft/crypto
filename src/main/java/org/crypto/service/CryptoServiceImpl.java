package org.crypto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.crypto.entity.Wallet;
import org.crypto.exception.AmountException;
import org.crypto.exception.CurrencyNotFoundException;
import org.crypto.exception.WalletNotFoundException;
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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
	
	public WalletDto removeWallet(WalletDto walletDto) throws JSONException {
		walletRepository.delete(mapWallet(walletDto));
		return walletDto;
	}
	
	public BuyCurrencyDto buyCurrency(BuyCurrencyDto buyCurrencyDto) throws JSONException {
		Optional<Wallet> walletOptional = walletRepository.findByName(buyCurrencyDto.getWalletName());
		if(!walletOptional.isEmpty()) {
			Wallet wallet = walletOptional.get();
			String currency = buyCurrencyDto.getCurrency();
			String result = restTemplate.exchange("https://min-api.cryptocompare.com/data/price?extraParams=crypto&" +
					"fsym=" + currency + "&tsyms=USD&api_key=" + API_KEY,HttpMethod.GET, null,
					new ParameterizedTypeReference<String>(){}).getBody();
			JSONObject jsonObject = new JSONObject(result);
			String response = "";
			if(jsonObject.length() > 1) {
				response = new String(jsonObject.getString("Response"));
			}
			if("Error".equalsIgnoreCase(response)) {
				throw new CurrencyNotFoundException("Currency not found");
			}
			BigDecimal amountToBuy = buyCurrencyDto.getAmount();
			if(wallet.getCurrencies().containsKey(currency)) {
				BigDecimal newAmount = wallet.getCurrencies().get(currency).add(amountToBuy);
				wallet.getCurrencies().put(currency, newAmount);
			} else {
				wallet.getCurrencies().put(currency, amountToBuy);
			}
			walletRepository.save(wallet);
		} else {
			throw new WalletNotFoundException("Wallet not found");
		}
		return buyCurrencyDto;
	}
	
	public TransferDto transferValues(TransferDto transferDto) throws JSONException {
		Optional<Wallet> walletFromOptional = walletRepository.findByName(transferDto.getWalletFrom());
		Optional<Wallet> walletToOptional = walletRepository.findByName(transferDto.getWalletTo());
		if(!(walletFromOptional.isEmpty() || walletToOptional.isEmpty())) {
			String currencyFrom = transferDto.getCurrencyFrom();
			String currencyTo = transferDto.getCurrencyTo();
			String result = restTemplate.exchange("https://min-api.cryptocompare.com/data/price?extraParams=crypto" +
							"&fsym=" + currencyFrom + "&tsyms=" + currencyTo + "&api_key=" + API_KEY,
					HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
					}).getBody();
			JSONObject jsonObject = new JSONObject(result);
			BigDecimal rate = new BigDecimal(jsonObject.getDouble(currencyTo)).setScale(2, RoundingMode.HALF_DOWN);
			Wallet walletFrom = walletFromOptional.get();
			Wallet walletTo = walletToOptional.get();
			BigDecimal originalAmount = walletFrom.getCurrencies().get(currencyFrom);
			if(originalAmount.compareTo(transferDto.getAmountFrom()) >= 0) {
				walletFrom.getCurrencies().put(currencyFrom, originalAmount.subtract(transferDto.getAmountFrom()));
				BigDecimal amountTo = transferDto.getAmountFrom().multiply(rate).setScale(2, RoundingMode.HALF_DOWN);
				walletRepository.save(walletFrom);
				if(walletTo.getCurrencies().containsKey(currencyTo)) {
					amountTo = amountTo.add(walletTo.getCurrencies().get(currencyTo));
				}
				transferDto.setAmountTo(amountTo);
				walletTo.getCurrencies().put(currencyTo, amountTo);
				walletRepository.save(walletTo);
			} else {
				throw new AmountException("Origin wallet does not have enough funds");
			}
		} else {
			throw new WalletNotFoundException("Wallet not found");
		}
		return transferDto;
	}
	
	private WalletDto mapWalletDto(Wallet wallet) throws JSONException {
		WalletDto walletDto = new WalletDto();
		
		walletDto.setId(wallet.getId());
		walletDto.setName(wallet.getName());
		walletDto.setCurrencies(wallet.getCurrencies());
		
		return walletDto;
	}
	
	private Wallet mapWallet(WalletDto walletDto) throws JSONException {
		Wallet wallet = new Wallet();
		
		wallet.setId(walletDto.getId());
		wallet.setName(walletDto.getName());
		wallet.setCurrencies(walletDto.getCurrencies());
		
		return wallet;
	}
}
