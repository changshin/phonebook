package com.chang.ng.phone.test;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chang.ng.phone.controller.ReferenceController;
import com.chang.ng.phone.controller.UserController;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.service.GroupsService;
import com.chang.ng.phone.data.service.ReferenceService;
import com.chang.ng.phone.data.service.TokenService;
import com.chang.ng.phone.data.service.UserGroupsService;
import com.chang.ng.phone.data.service.UserService;
import com.chang.ng.phone.response.Response;
import com.chang.ng.phone.response.UserResponse;

@RunWith(SpringRunner.class)

// BOth have 'AUTO-CONFIGURATION REPORT' error
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class UserControllerTest {
	
	private static final Logger log = LogManager.getLogger(UserControllerTest.class);
	
	@Autowired
	UserController userController;
	
	@Autowired
	ReferenceController referenceController;
	
	@Autowired
	private MockHttpServletRequest request;
	
	@Autowired
	GroupsService groupsService;
	  
    @Autowired
    UserService userService;

    @Autowired
	ReferenceService referenceService;
    
    @Autowired
    TokenService tokenService;
    
    @Autowired
	UserGroupsService userGroupsService;
    
    @Test
	public void getUsersByGroupsId() {
    	Integer groupsId = 7;
		try {
			List<User> records = userService.getUsersByGroupId(groupsId);
			if  ( records != null ) {
				for ( User user: records ) {
					System.out.println(user.toString());
				}
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

	@Test
	public void get4Users() {
		try {
			Response response = userController.get4Users(request);
			if  ( response != null ) {
				HashMap<String, Object> result = (HashMap<String, Object>)response.getResult();
				List<UserResponse> records= (List<UserResponse>)result.get("users");
				for ( UserResponse user: records ) {
					System.out.println(user.getGroupsIds()+":"+user.getGroupNames());
				}
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	
	@Test
	public void getRefAll() {
		try {
			Response response = referenceController.getAll();
			if  ( response != null ) {
				
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

	@Test
	public void contextLoads() throws Exception {
		System.out.println("testing");
	}
	
	
	
	
	@Test
	public void getUsers() {
		try {
			Response response = userController.getList(request);
			if  ( response != null ) {
				List<User> list = (List<User>)response.getRecords();
				for ( User user: list ) {
					System.out.println(user.toString());
				}
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void getListName() {
		try {
			//request.addHeader("REMOTE_ADDR", "127.0.0.1");
			//request.setParameter("firstName", "chang");
			Response response = userController.getListByColumnValue("firstName","Chang");
			if  ( response != null ) {
				List<User> list = (List<User>)response.getRecords();
				 if  ( list != null ) {
					 System.out.println(list.toString());
				 } else {
					 System.out.println("no found name");
				 }
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
