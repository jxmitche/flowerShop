package org.johnm.flower.shop.flowers;


public enum FlowerType {
	ROSE("R12"),
	LILY("L09"),
	TULIP("T58"),
	UNKNOWN("");
	
	private final String code;

	private FlowerType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
