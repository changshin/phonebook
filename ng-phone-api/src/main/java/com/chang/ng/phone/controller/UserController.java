package com.chang.ng.phone.controller;



import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chang.crypto.CryptoUtil;
import com.chang.ng.phone.data.dao.UserGroupsRepository;
import com.chang.ng.phone.data.model.Gender;
import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.data.model.Token;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.model.UserGroups;
import com.chang.ng.phone.data.service.GroupsService;
import com.chang.ng.phone.data.service.ReferenceService;
import com.chang.ng.phone.data.service.TokenService;
import com.chang.ng.phone.data.service.UserGroupsService;
import com.chang.ng.phone.data.service.UserService;
import com.chang.ng.phone.request.GroupsRequest;
import com.chang.ng.phone.request.UserRequest;
import com.chang.ng.phone.response.Response;
import com.chang.ng.phone.response.ResponseHelper;
import com.chang.ng.phone.response.UserResponse;
import com.chang.ng.phone.utils.ApiConstants;
import com.chang.ng.phone.utils.AuthTokenConfig;
import com.chang.ng.phone.utils.CommonUtils;
import com.chang.ng.phone.utils.Constants;
import com.chang.ng.phone.utils.ImageUtil;
import com.chang.ng.phone.utils.ServletUtil;

import eu.bitwalker.useragentutils.UserAgent;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@RequestMapping(value = "/user")
@RestController
@CrossOrigin()	// for Cross-Origin Resource Sharing (CORS) in the response
public class UserController  extends BaseController{

	private static final Logger logger = LogManager.getLogger(UserController.class);

	private static final int RANDOM_LENGTH = 10;
	private static final Integer NEW_TOKEN = 0;
	private static final Integer USED_TOKEN = 1;
	private static final int MONTHS = -1;
	private static final int THUMBNAIL_SIZE = 60;

		
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
    
    public UserController() {
    	logger.info("UserController consrtructor is called");
    }

    @RequestMapping(value = "/4users", method = RequestMethod.GET)
    public Response get4Users(HttpServletRequest request){
    	Response response  = new Response();
    	try {
    		List<Gender> genders = referenceService.getGenders();
    		List<Groups> groupsList = referenceService.getGroups();
    		List<User> users = userService.getUsers();
    		List<UserResponse> records = new ArrayList<UserResponse>();
    		for (User user: users ) {
    			UserResponse userResp = new UserResponse();
    			BeanUtils.copyProperties(user, userResp);
    			Object[] idNames = userGroupsService.getUserGroupsObjectByUserId(user.getId());
    			if  ( idNames != null) {
    				userResp.setGroupsIds( (String)idNames[1] );
    				userResp.setGroupNames( (String)idNames[2] );
    			}
    			records.add(userResp);
    		}
    		HashMap<String, Object>  results = new HashMap<>();
    		results.put("genders", genders);
    		results.put("groups", groupsList);
    		results.put("users", records);
        	response.setResult(results);
    	} catch ( Exception ex ) {
    		logger.error("error in get4Users : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
        return response;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response getList(HttpServletRequest request){
    	Response response  = new Response();
    	try {
    		List<User> list = userService.getUsers();
        	response.setRecords(list);
    	} catch ( Exception ex ) {
    		logger.error("error in getList : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	
        return response;
    }

    @RequestMapping(value = "/list/{column}/{value}", method = RequestMethod.GET)
    public Response getListByColumnValue(@PathVariable String column,@PathVariable String value){
    	Response response  = new Response();
    	try {
    		List<User> list = null;
    		if  ( column.equalsIgnoreCase("firstName") ) {
    			list = userService.getUsersByFirstname(value);
    			response.setRecords(list);
    		} else if  ( column.equalsIgnoreCase("id") ) {
    			User user = userService.getUser( Long.parseLong(value) );
    			response.setResult(user);
    		} else if  ( column.equalsIgnoreCase("groups") ) {
    			list = userService.getUsersByGroupId( Integer.valueOf(value) );
    			response.setRecords(list);
    		}
    		
    	} catch ( Exception ex ) {
    		logger.error("error in getListByColumnValue : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
        return response;
    }

    @RequestMapping(value = "/exist", method = RequestMethod.POST)
	public Response isEmailExist(HttpServletRequest request, UserRequest userRequest) {
		try {

			boolean status = userService.isEmailExist(userRequest.getEmail());
			return ResponseHelper.buildResponse(status);

		} catch (Exception e) {
			logger.error("error in isEmailExist : ", e);
			return ResponseHelper.buildExceptionResponse(e);

		}

	}
  
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response saveUser(HttpServletRequest request, UserRequest userRequest) {
    	Response response  = new Response();
    	try {
    		
    		//String language = getLanguage(request);
    		logger.info(userRequest.toString());
    		
    		if (StringUtils.isEmpty(userRequest.getEmail())) {
				return ResponseHelper.INVALID_EMAIL_ID_RESPONSE;
			}
			boolean existEmail = userService.isEmailExist(userRequest.getEmail().toLowerCase());
			User user = new User();
			
			if ( existEmail && userRequest.getType().equalsIgnoreCase("add") ) {
				return ResponseHelper.DUPLICATE_EMAIL_RESPONSE;
			}
			// update existing user
			if  ( userRequest.getId() != null && userRequest.getId() > 0 ) {
				user = userService.getUser( userRequest.getId() );
				if  ( !existEmail && !user.getEmail().equalsIgnoreCase( userRequest.getEmail()) ) {
					user.setEmail( userRequest.getEmail() );
				}
			} else {
				user.setEmail( userRequest.getEmail() );
			}
			user.setFirstName( userRequest.getFirstName() );
			user.setLastName( userRequest.getLastName() );
			user.setGenderId( userRequest.getGenderId() );
		    

		    
		    user.setUpdatedDate( new Date() );
		    if  ( user.getCreatedDate() == null ) {
		    	user.setCreatedDate( new Date() );
		    }
		    user.setDeleted(( byte)0 );
		    userService.save( user, userRequest.getGroupsIds() );
		    UserResponse userResponse = new UserResponse();
		    BeanUtils.copyProperties(user, userResponse);
		    
        	response.setResult(userResponse);
    	} catch ( Exception ex ) {
    		logger.error("error in save : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	return response;
    	
    }
      
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Response userLogout(HttpServletRequest request) {
		try {
			String language = getLanguage(request);
			bundle = ResourceBundle.getBundle("messages", new Locale(language));
			String message = CommonUtils.toUTF8(bundle.getString("LOGOUT_SUCCESS"));
			HttpSession session = request.getSession();
			session.invalidate();
			return ResponseHelper.buildSuccessReesponse(message);
		} catch (Exception ex) {
			HttpSession session = request.getSession();
			session.invalidate();

		}
		return ResponseHelper.buildSuccessReesponse(ApiConstants.LOGOUT_SUCCESS);
	}
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(HttpServletRequest request, UserRequest userRequest) {
		Response response = new Response();
		try {
			if (userRequest.getIds() != null) {
				String[] ids= userRequest.getIds().split(",");
				List<Long> userIds = new ArrayList<Long>();
				for (String id : ids) {
					userIds.add( Long.parseLong( id ) ); 
				}
				if  (  userRequest.getGroupsIds() != null ) {	// delete userGroups
					Integer groupsId = Integer.parseInt( userRequest.getGroupsIds() );
					userGroupsService.delete(groupsId, userIds);
				} else {	// delete users
					userService.delete(userIds);
				}
				response.setStatus("Success");
			} else {
				response.setStatus("Fail");
				response.setErrorMessage("User Ids should not be null or empty");
			}
		} catch (Exception e) {
				logger.error("error in upload : ", e);
				response.setStatus("Failed");
				response.setErrorMessage("User Ids should not be null or empty");

		}
		return response;

	}
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public Response deleteUser(@PathVariable Long userId) {
		Response response = new Response();
		try {
			userService.delete(userId);
			response.setStatus("Success");
		} catch (Exception e) {
			response.setStatus("Fail");
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}
    
   
}
