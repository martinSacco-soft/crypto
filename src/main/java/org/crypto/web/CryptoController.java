package org.crypto.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crypto.model.*;
import org.crypto.service.CryptoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
	
	
	private CryptoService cryptoService;
	
	@Autowired
	public CryptoController(CryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}
	
	@GetMapping("/currencies")
	public ResponseEntity<BaseDto> getAllCryptoCurrencies (HttpServletRequest request) throws IOException {
			BaseDto<Map<String, CurrencyDto>> baseDto = new BaseDto<>(true);
			Map<String, CurrencyDto> currencies = cryptoService.getAllCryptoCurrencies();
			baseDto.setPayload(currencies);
			return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@PostMapping("/createwallet")
	public ResponseEntity<BaseDto> createWallet (HttpServletRequest request) {
		BaseDto<WalletCreatedDto> baseDto = new BaseDto<>(true);
		WalletCreatedDto wallet;
		try {
			wallet = cryptoService.createWallet();
		} catch (JSONException | JsonProcessingException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		if (wallet == null || wallet.getId().compareTo(0L) == 0) {
			baseDto.addMessage("Error creating wallet");
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@GetMapping("wallet/{id}")
	public ResponseEntity<BaseDto> getWallet (@PathVariable("id") long id,
											  HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.getWallet(id);
		} catch (JSONException | JsonProcessingException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	public void updateWallet(HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		
	}
	
	public void removeWallet(HttpServletRequest request) {
	
	
	}
	
	public ResponseEntity<BaseDto> buyCurrency(HttpServletRequest request) {
		BaseDto<BoughtCurrencyDto> baseDto = new BaseDto<>(true);
		BoughtCurrencyDto currency = cryptoService.buyCurrency();
		baseDto.setPayload(currency);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	public ResponseEntity<BaseDto> transferValues(HttpServletRequest request) {
		BaseDto<TransferDto> baseDto = new BaseDto<>(true);
		TransferDto transfer = cryptoService.transferValues();
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
}
