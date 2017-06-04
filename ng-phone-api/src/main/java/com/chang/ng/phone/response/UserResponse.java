package com.chang.ng.phone.response;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserResponse {

    
	private Long id;
    private String firstName;
    private String lastName;
    private String groupsIds;
    private Byte genderId;
    private String password;
    private String email;
    private String phone;
    private Date dob;
    private Byte deleted;
	private Byte active;
    private String note;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String avatarUrl;
    private String avatarThumbnail;
    private Date createdDate;
    private Date updatedDate;
	private Date lastLoggedIn;
	private String groupNames;
	    
        
		
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getGroupsIds() {
		return groupsIds;
	}
	public void setGroupsIds(String groupsIds) {
		this.groupsIds = groupsIds;
	}



	public Byte getGenderId() {
		return genderId;
	}



	public void setGenderId(Byte genderId) {
		this.genderId = genderId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public Byte getDeleted() {
		return deleted;
	}



	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}



	public Byte getActive() {
		return active;
	}



	public void setActive(Byte active) {
		this.active = active;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getAvatarUrl() {
		return avatarUrl;
	}



	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}



	public String getAvatarThumbnail() {
		return avatarThumbnail;
	}



	public void setAvatarThumbnail(String avatarThumbnail) {
		this.avatarThumbnail = avatarThumbnail;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}



	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}



	public String getGroupNames() {
		return groupNames;
	}



	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
   
   
}
