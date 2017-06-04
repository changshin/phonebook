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

import com.chang.ng.phone.data.dao.GroupsRepository;
import com.chang.ng.phone.data.dao.UserRepository;
import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.response.GroupsVO;
import com.chang.ng.phone.utils.CommonUtils;


	
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GroupsServiceImpl implements GroupsService {

	Logger logger = LogManager.getLogger(GroupsServiceImpl.class);

	@Autowired
	GroupsRepository dao;
	
	public Groups getGroups(Long id) {
		return dao.getGroups(id);
	};
	
	public void save(Groups groups) {
		dao.save(groups);
	}
	
	public void delete(long id) {
		dao.delete(id);
	}
	
	public List<GroupsVO> getGroupsWithUserCounts() {
		List<Object[]> records = dao.getGroupsWithUserCounts();
		List<GroupsVO> list = new ArrayList<GroupsVO>();
		for (Object[] record: records ) {
			GroupsVO groupsVO = new GroupsVO();
			groupsVO.setId( (Byte)record[0] );
			groupsVO.setName( (String)record[1] );
			groupsVO.setUserCount( ((BigDecimal)record[2]).intValue() );
			list.add(groupsVO);
		}
		return list;
	}
	
}
