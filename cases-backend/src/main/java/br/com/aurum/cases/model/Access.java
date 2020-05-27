package br.com.aurum.cases.model;

public enum Access {
	
	PUBLIC("Public"), 
	PRIVATE("Private");
	
	private String label;
	
	private Access(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
