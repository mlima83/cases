package br.com.aurum.base.dto;

public class ErrorItemDtoV1 {
	
	private String key;
	
	private String message;
	
	public ErrorItemDtoV1() {}

	public ErrorItemDtoV1(String key, String message) {
		this.key = key;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
