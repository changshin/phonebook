package com.chang.ng.phone.data.service;

import java.util.List;

import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.response.GroupsVO;

public interface GroupsService { 

	public void save(Groups groups);
	public Groups getGroups(Long id);
	public void delete(long id);
	public List<GroupsVO> getGroupsWithUserCounts();
	
}
