package com.chang.ng.phone.data.model;

public enum GenderType {

	MALE((byte)1, "Male"), 
	FEMALE((byte)2, "Female"), 
	OTHER((byte)3, "Other");

	private GenderType(byte id, String roleName) {
		this.id = id;
		this.role = roleName;
	}

	private final byte id;
	private final String role;

	public byte getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

}
