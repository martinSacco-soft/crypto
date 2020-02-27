package org.crypto.web;

import org.crypto.model.*;
import org.crypto.service.CryptoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
	
	@PostMapping("/wallets")
	public ResponseEntity<BaseDto> createWallet (@RequestBody WalletDto walletDto, HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.createWallet(walletDto);
		} catch (JSONException e) {
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
	
	@GetMapping("wallets/{id}")
	public ResponseEntity<BaseDto> getWallet (@PathVariable("id") long id,
											  HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.getWallet(id);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@PutMapping("wallets")
	public ResponseEntity<BaseDto> updateWallet(@RequestBody WalletDto walletDto, HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.updateWallet(walletDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity<>(baseDto, HttpStatus.OK);
	}
	
	@DeleteMapping("wallets")
	public ResponseEntity<BaseDto> removeWallet(@RequestBody WalletDto walletDto, HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.removeWallet(walletDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity<>(baseDto, HttpStatus.OK);
	}
	
	@PutMapping("currencies")
	public ResponseEntity<BaseDto> buyCurrency(@RequestBody BuyCurrencyDto buyCurrencyDto, HttpServletRequest request) {
		BaseDto<BuyCurrencyDto> baseDto = new BaseDto<>(true);
		BuyCurrencyDto currency = cryptoService.buyCurrency(buyCurrencyDto);
		baseDto.setPayload(currency);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@PostMapping("wallets/transfer")
	public ResponseEntity<BaseDto> transfer(@RequestBody TransferDto transferDto, HttpServletRequest request) {
		BaseDto<TransferDto> baseDto = new BaseDto<>(true);
		try {
			transferDto = cryptoService.transferValues(transferDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		}
		baseDto.setPayload(transferDto);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
}
