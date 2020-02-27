package org.crypto.web;

import org.assertj.core.util.Lists;
import org.crypto.model.CurrencyDto;
import org.crypto.model.WalletDto;
import org.crypto.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CryptoService cryptoService;
	
	@Test
	public void getCurrencies() throws Exception {
		Map<String, CurrencyDto> currencies = new LinkedHashMap<>();
		currencies.put("", new CurrencyDto());
		when(cryptoService.getAllCryptoCurrencies()).thenReturn(currencies);
		
		mockMvc.perform(MockMvcRequestBuilders
		.get("/crypto/currencies"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value(Lists.newArrayList()));
		
		verify(cryptoService).getAllCryptoCurrencies();
	}
	
	@Test
	public void createWallet_Success() throws Exception {
		WalletDto walletDto = new WalletDto();
		Map<String, BigDecimal> currencies = new HashMap<>();
		walletDto.setName("Testing Wallet");
		currencies.put("BTC", new BigDecimal(5));
		walletDto.setCurrencies(currencies);
		walletDto.setId(1L);
		
		when(cryptoService.createWallet(any())).thenReturn(walletDto);
		
		mockMvc.perform(MockMvcRequestBuilders
		.post("/crypto/wallets")
				.content("{\n" +
						"\t\"name\": \"Testing Wallet\",\n" +
						"\t\"currencies\": {\n" +
						"        \"BTC\": 5\n" +
						"\t}\n" +
						"}")
				.characterEncoding("utf-8")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value(Lists.newArrayList()))
				.andExpect(jsonPath("$.payload.id").value(1L));
		
		verify(cryptoService).createWallet(any(WalletDto.class));
	}
	
	@Test
	public void createWallet_Error() throws Exception {
		when(cryptoService.createWallet(any())).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/crypto/wallets")
				.content("{\n" +
						"\t\"name\": \"Testing Wallet\",\n" +
						"\t\"currencies\": {\n" +
						"        \"BTC\": 5\n" +
						"\t}\n" +
						"}")
				.characterEncoding("utf-8")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value("Error creating wallet"));
		
		verify(cryptoService).createWallet(any(WalletDto.class));
	}
	
	@Test
	public void getWallet_Success() throws Exception {
		WalletDto walletDto = new WalletDto();
		Map<String, BigDecimal> currencies = new HashMap<>();
		walletDto.setName("Testing Wallet");
		currencies.put("BTC", new BigDecimal(5));
		walletDto.setCurrencies(currencies);
		walletDto.setId(1L);
		
		when(cryptoService.getWallet(anyLong())).thenReturn(walletDto);
		
		mockMvc.perform(MockMvcRequestBuilders
		.get("/crypto/wallets/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value(Lists.newArrayList()))
				.andExpect(jsonPath("$.payload.id").value(1L))
				.andExpect(jsonPath("$.payload.name").value("Testing Wallet"));
		
		verify(cryptoService).getWallet(anyLong());
	}
}
