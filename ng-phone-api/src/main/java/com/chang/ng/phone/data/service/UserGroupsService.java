package com.chang.ng.phone.data.service;

import java.util.List;

import com.chang.ng.phone.data.model.UserGroups;

public interface UserGroupsService { 

	public void save(UserGroups userGroups);
	public UserGroups getUserGroups(Long id);
	public void delete(long id);
	public void delete(Integer groupsId, List<Long> userIds);
	
	public List<UserGroups> getUserGroupsByUserId(Long userId);
	public List<UserGroups> getUserGroupsAllByUserId(Long userId);
	public List<UserGroups> getUserGroupsByGroupsId(Integer groupsId);
	
	public Object[] getUserGroupsObjectByUserId(Long userId);
	
}
