package org.crypto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDto<T> implements Serializable {
	
	private boolean success;
	private List<String> messages = new ArrayList<>();
	private T payload;
	
	public BaseDto(){ this.success = false; }
	
	public BaseDto(boolean success) { this.success = success; }
	
	public T getPayload() {
		return payload;
	}
	
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String msg) {
		this.messages.add(msg);
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
