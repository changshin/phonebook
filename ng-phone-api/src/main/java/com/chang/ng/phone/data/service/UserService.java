package com.chang.ng.phone.data.service;

import java.util.Date;
import java.util.List;

import com.chang.ng.phone.data.model.User;

public interface UserService { 

	public List<User> getUsers();
	public User getUser(long id);
	public List<User> getUsersByFirstname(String firstName);
	public List<User> getUsersByGroupId(Integer groupId);
	public boolean isEmailExist(String email);

	public void save(User User);
	public void save(User User, String groupsIds);
	public void delete(long id) ;
	public void delete(List<Long> ids) ;
	
	public User validateLogin(String email, String password);
	public Long getUserId(String email);
	
	public User getUserByEmail(String email);
	
}
