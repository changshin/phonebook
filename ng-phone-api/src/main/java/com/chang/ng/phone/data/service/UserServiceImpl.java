package com.chang.ng.phone.data.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chang.ng.phone.data.dao.UserGroupsRepository;
import com.chang.ng.phone.data.dao.UserRepository;
import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.model.UserGroups;
import com.chang.ng.phone.response.UserResponse;
import com.chang.ng.phone.utils.CommonUtils;


	
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository dao;
	
	@Autowired
	UserGroupsRepository userGroupsDao;
	
	
	@Override
	public List<User> getUsers() {
		return dao.getUsers();
	}
	public User getUser(long id) {
		return dao.getUser(id);
	};
		
	public User getUserByEmail(String email){
		return dao.getUserByEmail(email);
	}

	public void save(User user) {
		dao.save(user);
	}
	
	public void save(User user, String groupsIds) {
		dao.save(user);
		String[] ids = groupsIds.split(",");
		List<UserGroups> records = userGroupsDao.getUserGroupsAllByUserId(user.getId());

		// first time, add all selected groups
		if  ( records == null || records.size() == 0 ) {
			for ( String id: ids ) {
				UserGroups userGroups = new UserGroups();
				userGroups.setUserId( user.getId() );
				userGroups.setGroupsId( Integer.parseInt(id));
				userGroups.setDeleted( (byte)0 );
				userGroupsDao.save(userGroups);
			}
		} else {
			HashMap<String,String> groupKeys = new HashMap<String,String>();
			HashMap<String,String> recordKeys = new HashMap<String,String>();
			for ( String id: ids ) {
				groupKeys.put(id, null);
			}
			// exist usetGroups;
			for ( UserGroups userGroups: records ) {
				recordKeys.put(userGroups.getId()+"", null);
				// existing, do not create again
				if  ( groupKeys.containsKey(userGroups.getId()+"") ) {	// matched
					if  ( userGroups.getDeleted() != null && userGroups.getDeleted() == 1 ) {
						userGroups.setDeleted( (byte)0);
						userGroupsDao.save(userGroups);	// selected again, so restore it from deleted
					} else {
						// selected, and not deleted, no action
					}
				} else {	// if not deleted, should be deleted.
					if  ( userGroups.getDeleted() == null || userGroups.getDeleted() == 0 ) {
						userGroups.setDeleted( (byte)1);
						userGroupsDao.save(userGroups);	// not selected, should be deleted
					}
				}
			}
			// add new groups
			for ( String id: ids ) {
				if  ( !recordKeys.containsKey(id) ) {	// not created  
					UserGroups userGroups = new UserGroups();
					userGroups.setUserId( user.getId() );
					userGroups.setGroupsId( Integer.parseInt(id));
					userGroups.setDeleted( (byte)0 );
					userGroupsDao.save(userGroups);
				}
			}
		}
		
	}
	
	
	public void delete(long id) {
		dao.delete(id);
	}
	
	public void delete(List<Long> ids) {
		dao.delete(ids);
	};
	
	@Override
	public boolean isEmailExist(String email) {
		boolean isValid = false;
		try {
			if (email != null) {
				email = email.toLowerCase();
			}

			User user = dao.isEmailExist(email);
			if (user != null)
				isValid = true;
		} catch (Exception e) {
			logger.warn("unable to find email address in users:" + email, e);
		}
		return isValid;
	}

	@Override
	public User validateLogin(String username, String password) {
		User user = dao.validateLogin(username, password);
		return user;
	}

	@Override
	public Long getUserId(String email) {
		User user = dao.isEmailExist(email);
		if (user != null) {
			return user.getId();
		} 
		return (long)0;
	}
	@Override
	public List<User> getUsersByFirstname(String firstName) {
		return dao.getUsersByFirstname(firstName);
	}
	@Override
	public List<User> getUsersByGroupId(Integer groupId) {
		List<Object[]> records = dao.getUsersByGroupId(groupId);
		if  ( records == null || records.size() == 0 ) {
			return null;
		}
		//u.id,u.first_name,u.last_name,u.gender_id,u.email, u.created_date, u.updated_date
		List<User> list = new ArrayList<User>();
		for ( Object[] record: records ) {
			User user = new User();
			user.setId( ((Integer)record[0]).longValue() );
			user.setFirstName( (String)record[1] );
			user.setLastName( (String)record[2]);
			user.setGenderId( (Byte)record[3]);
			user.setEmail( (String)record[4] );
			Timestamp stamp = (Timestamp)record[5];
			 Date date = new Date(stamp.getTime());
			user.setCreatedDate( date );
			stamp = (Timestamp)record[6];
			date = new Date(stamp.getTime());
			user.setUpdatedDate( date );
			list.add(user);
		}
		return list;
	}
	
}
