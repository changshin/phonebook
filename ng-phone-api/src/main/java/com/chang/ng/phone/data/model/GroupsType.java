package com.chang.ng.phone.data.model;

public enum GroupsType {

	FAMILY((byte)1, "Family"), 
	RELATIVE((byte)2, "Relative"), 
	FRIENDS((byte)3, "Friends"),
	COWORKERS((byte)3, "Co-Workders");

	private GroupsType(byte id, String roleName) {
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
