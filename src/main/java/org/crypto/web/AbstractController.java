package org.crypto.web;

import org.crypto.model.BaseDto;

class AbstractController {
	BaseDto buildErrorDto(String err) {
		BaseDto errorDto = new BaseDto();
		errorDto.addMessage(err);
		return errorDto;
	}
}
