package com.chang.ng.phone.data.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chang.ng.phone.data.dao.GenderRepository;
import com.chang.ng.phone.data.dao.GroupsRepository;
import com.chang.ng.phone.data.model.Gender;
import com.chang.ng.phone.data.model.Groups;


	
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ReferenceServiceImpl implements ReferenceService {

	Logger logger = LogManager.getLogger(ReferenceServiceImpl.class);

	@Autowired
	GroupsRepository groupDao;
	
	@Autowired
	GenderRepository genderDao;
	
	@Override
	public List<Groups> getGroups() {
		return groupDao.getGroups();
	}
	
	@Override
	public List<Gender> getGenders() {
		return genderDao.getGenders();
	}


}
