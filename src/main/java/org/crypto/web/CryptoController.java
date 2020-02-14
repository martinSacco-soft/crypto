package org.crypto.web;

import org.crypto.model.*;
import org.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<BaseDto> getAllCryptoCurrencies () throws IOException {
			BaseDto<Map<String, CurrencyDto>> baseDto = new BaseDto<>(true);
			Map<String, CurrencyDto> currencies = cryptoService.getAllCryptoCurrencies();
			baseDto.setPayload(currencies);
			return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@PostMapping("/createwallet")
	public ResponseEntity<BaseDto> createWallet () {
		BaseDto<WalletCreatedDto> baseDto = new BaseDto<>(true);
		WalletCreatedDto wallet = cryptoService.createWallet();
		if (wallet.getId().compareTo(0L) == 0) {
			baseDto.addMessage("Error creating wallet");
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	public ResponseEntity<BaseDto> getWallet () {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet = cryptoService.getWallet();
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	public void updateWallet() {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		
	}
	
	public void removeWallet() {
	
	
	}
	
	public ResponseEntity<BaseDto> buyCurrency() {
		BaseDto<BoughtCurrencyDto> baseDto = new BaseDto<>(true);
		BoughtCurrencyDto currency = cryptoService.buyCurrency();
		baseDto.setPayload(currency);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	public ResponseEntity<BaseDto> transferValues() {
		BaseDto<TransferDto> baseDto = new BaseDto<>(true);
		TransferDto transfer = cryptoService.transferValues();
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
}
