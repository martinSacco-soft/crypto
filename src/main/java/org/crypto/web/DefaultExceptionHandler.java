package org.crypto.web;

import org.crypto.model.BaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DefaultExceptionHandler {
	
	private static final String UNEXPECTED_SERVER_ERROR = "UNEXPECTED_SERVER_ERROR";
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	BaseDto error(Exception e) {
		BaseDto baseDto = new BaseDto();
		baseDto.addMessage(UNEXPECTED_SERVER_ERROR);
		baseDto.addMessage(e.getMessage());
		return baseDto;
	}
}
