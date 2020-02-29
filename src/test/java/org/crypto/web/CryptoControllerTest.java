package org.crypto.web;

import org.assertj.core.util.Lists;
import org.crypto.model.BuyCurrencyDto;
import org.crypto.model.CurrencyDto;
import org.crypto.model.TransferDto;
import org.crypto.model.WalletDto;
import org.crypto.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
		WalletDto walletDto = createMockWallet();
		
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
		WalletDto walletDto = createMockWallet();
		
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
	
	@Test
	public void updateWallet_Success() throws Exception {
		WalletDto walletDto = createMockWallet();
		walletDto.setName("Testing Wallet Updated");
		
		when(cryptoService.updateWallet(any())).thenReturn(walletDto);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/crypto/wallets")
				.content("{\n" +
						"\t\"name\": \"Testing Wallet Updated\",\n" +
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
				.andExpect(jsonPath("$.payload.id").value(1L))
				.andExpect(jsonPath("$.payload.name").value("Testing Wallet Updated"));
		
		verify(cryptoService).updateWallet(any());
	}
	
	@Test
	public void removeWallet_Success() throws Exception {
		WalletDto walletDto = createMockWallet();
		
		when(cryptoService.removeWallet(any())).thenReturn(walletDto);
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/crypto/wallets")
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
				.andExpect(jsonPath("$.payload.id").value(1L))
				.andExpect(jsonPath("$.payload.name").value("Testing Wallet"));
		
		verify(cryptoService).removeWallet(any());
	}
	
	@Test
	private void buyCurrency_Success() throws Exception {
		BuyCurrencyDto buyCurrencyDto = createMockBuyCurrencyDto();
		when(cryptoService.buyCurrency(any())).thenReturn(buyCurrencyDto);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/crypto/currencies")
				.content("{\n" +
						"\t\"walletName\": \"Testing Wallet\",\n" +
						"\t\"currency\": \"BTC\",\n" +
						"\t\"amount\": 2.5\n" +
						"}")
				.characterEncoding("utf-8")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value(Lists.newArrayList()))
				.andExpect(jsonPath("$.payload.walletName").value("Testing Wallet"))
				.andExpect(jsonPath("$.payload.currency").value("BTC"))
				.andExpect(jsonPath("$.payload.amount").value(2.5));
		
		verify(cryptoService).buyCurrency(any());
	}
	
	@Test
	private void transfer_Success() throws Exception {
		TransferDto transferDto = createMockTransferDto();
		when(cryptoService.transferValues(any())).thenReturn(transferDto);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/crypto/wallets/transfer")
				.content("{\n" +
						"\t\"walletFrom\": \"Testing Wallet\",\n" +
						"\t\"walletTo\": \"Testing Wallet Update\",\n" +
						"\t\"currencyFrom\": \"BTC\",\n" +
						"\t\"currencyTo\": \"ETH\",\n" +
						"\t\"amountFrom\": 2\n" +
						"}")
				.characterEncoding("utf-8")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.messages").value(Lists.newArrayList()))
				.andExpect(jsonPath("$.payload.walletFrom").value("Testing Wallet"))
				.andExpect(jsonPath("$.payload.walletTo").value("Testing Wallet Update"))
				.andExpect(jsonPath("$.payload.currencyFrom").value("BTC"))
				.andExpect(jsonPath("$.payload.currencyTo").value("ETH"))
				.andExpect(jsonPath("$.payload.amountFrom").value(2));
		
		verify(cryptoService).transferValues(any());
	}
	
	private BuyCurrencyDto createMockBuyCurrencyDto() {
		BuyCurrencyDto buyCurrencyDto = new BuyCurrencyDto();
		buyCurrencyDto.setWalletName("Testing Wallet");
		buyCurrencyDto.setCurrency("BTC");
		buyCurrencyDto.setAmount(new BigDecimal(2.5));
		return buyCurrencyDto;
	}
	
	private TransferDto createMockTransferDto() {
		TransferDto transferDto = new TransferDto();
		transferDto.setWalletFrom("Testing Wallet");
		transferDto.setWalletTo("Testing Wallet Update");
		transferDto.setCurrencyFrom("BTC");
		transferDto.setCurrencyTo("ETH");
		transferDto.setAmountFrom(new BigDecimal(2));
		transferDto.setAmountTo(new BigDecimal(96.60));
		return transferDto;
	}
	
	private WalletDto createMockWallet() {
		WalletDto walletDto = new WalletDto();
		Map<String, BigDecimal> currencies = new HashMap<>();
		walletDto.setName("Testing Wallet");
		currencies.put("BTC", new BigDecimal(5));
		walletDto.setCurrencies(currencies);
		walletDto.setId(1L);
		
		return walletDto;
	}
}
