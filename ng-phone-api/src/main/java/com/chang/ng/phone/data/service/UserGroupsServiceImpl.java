package com.chang.ng.phone.data.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chang.ng.phone.data.dao.UserGroupsRepository;
import com.chang.ng.phone.data.model.UserGroups;
import com.chang.ng.phone.utils.CommonUtils;


	
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserGroupsServiceImpl implements UserGroupsService {

	Logger logger = LogManager.getLogger(UserGroupsServiceImpl.class);

	@Autowired
	UserGroupsRepository dao;
	
	public UserGroups getUserGroups(Long id) {
		return dao.getUserGroups(id);
	};
	
	public void save(UserGroups userGroups) {
		dao.save(userGroups);
	}
	
	public void delete(long id) {
		dao.delete(id);
	}
	public void delete(Integer groupsId, List<Long> userIds) {
		dao.delete(groupsId,userIds);
	}
	
	public List<UserGroups> getUserGroupsByUserId(Long userId) {
		return dao.getUserGroupsByUserId(userId);
	};

	public List<UserGroups> getUserGroupsAllByUserId(Long userId) {
		return dao.getUserGroupsAllByUserId(userId);
	};

	public List<UserGroups> getUserGroupsByGroupsId(Integer groupsId) {
		return dao.getUserGroupsByGroupsId(groupsId);
	};
	
	public Object[] getUserGroupsObjectByUserId(Long userId) {
		List<Object[]> records = dao.getUserGroupsObjectByUserId(userId);
		if  ( records != null && records.size() > 0 ) {
			return (Object[])records.get(0);
		}
		return null;
	};
	
}
