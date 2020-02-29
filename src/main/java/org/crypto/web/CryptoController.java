package org.crypto.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.crypto.exception.WalletNotFoundException;
import org.crypto.model.*;
import org.crypto.service.CryptoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@Api(value = "Crypto Currency and Wallet Controller", produces = MediaType.APPLICATION_JSON_VALUE,
		tags = {"Crypto Currency and Wallet Management" })
@RequestMapping("/crypto")
public class CryptoController {
	
	private CryptoService cryptoService;
	
	@Autowired
	public CryptoController(CryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}
	
	@ApiOperation(value = "List all available currencies", notes = "Provides a list all operation for currencies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retrieved currency list")})
	@GetMapping("/currencies")
	public ResponseEntity<BaseDto> getAllCryptoCurrencies (HttpServletRequest request) throws IOException {
			BaseDto<Map<String, CurrencyDto>> baseDto = new BaseDto<>(true);
			Map<String, CurrencyDto> currencies = cryptoService.getAllCryptoCurrencies();
			baseDto.setPayload(currencies);
			return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a new wallet", notes = "Provides create new wallet operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created Wallet")})
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
	
	@ApiOperation(value = "Get an existing wallet", notes = "Provides a get wallet operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Wallet")})
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
		} catch (Exception e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.NOT_FOUND);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update an existing wallet", notes = "Provides an update wallet operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated Wallet")})
	@PutMapping("wallets")
	public ResponseEntity<BaseDto> updateWallet(@RequestBody WalletDto walletDto, HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.updateWallet(walletDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		} catch (Exception e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.NOT_FOUND);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity<>(baseDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an existing wallet", notes = "Provides a delete wallet operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted Wallet")})
	@DeleteMapping("wallets")
	public ResponseEntity<BaseDto> removeWallet(@RequestBody WalletDto walletDto, HttpServletRequest request) {
		BaseDto<WalletDto> baseDto = new BaseDto<>(true);
		WalletDto wallet;
		try {
			wallet = cryptoService.removeWallet(walletDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		} catch (Exception e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.NOT_FOUND);
		}
		baseDto.setPayload(wallet);
		return new ResponseEntity<>(baseDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Buy a currency for an existing wallet", notes = "Provides a buy currency operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully bought currency")})
	@PutMapping("currencies")
	public ResponseEntity<BaseDto> buyCurrency(@RequestBody BuyCurrencyDto buyCurrencyDto, HttpServletRequest request) {
		BaseDto<BuyCurrencyDto> baseDto = new BaseDto<>(true);
		BuyCurrencyDto currency;
		try {
			currency = cryptoService.buyCurrency(buyCurrencyDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		} catch (Exception e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.NOT_FOUND);
		}
		baseDto.setPayload(currency);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Make a transfer between two existing wallets", notes = "Provides transfer operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully transferred currencies between wallets")})
	@PostMapping("wallets/transfer")
	public ResponseEntity<BaseDto> transfer(@RequestBody TransferDto transferDto, HttpServletRequest request) {
		BaseDto<TransferDto> baseDto = new BaseDto<>(true);
		try {
			transferDto = cryptoService.transferValues(transferDto);
		} catch (JSONException e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.OK);
		} catch (Exception e) {
			baseDto.addMessage(e.getMessage());
			return new ResponseEntity<>(baseDto, HttpStatus.NOT_FOUND);
		}
		baseDto.setPayload(transferDto);
		return new ResponseEntity(baseDto, HttpStatus.OK);
	}
}
