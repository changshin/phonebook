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
import com.chang.ng.phone.data.model.Gender;
import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.data.model.Token;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.service.GroupsService;
import com.chang.ng.phone.data.service.ReferenceService;
import com.chang.ng.phone.data.service.TokenService;
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


@RequestMapping(value = "/groups")
@RestController
@CrossOrigin()	// for Cross-Origin Resource Sharing (CORS) in the response
public class GroupsController  extends BaseController{

	private static final Logger logger = LogManager.getLogger(GroupsController.class);
		
    @Autowired
	GroupsService groupsService;
    
    @Autowired
	ReferenceService referenceService;
    
    public GroupsController() {
    	//logger.info("GroupsController consrtructor is called");
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
    	Response response  = new Response();
    	try {
    		List<Groups> groups = referenceService.getGroups();
        	response.setResult(groups);
    	} catch ( Exception ex ) {
    		logger.error("error in list : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
        return response;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(HttpServletRequest request, GroupsRequest groupsRequest) {
    	Response response  = new Response();
    	try {
    		logger.info(groupsRequest.toString());
    		if  ( groupsRequest.getName() != null ) {
        		
        		Groups groups = null;
        		if  ( groupsRequest.getId() != null && groupsRequest.getId() > 0 ) {
        			groups = groupsService.getGroups(groupsRequest.getId());
        		} else {
        			groups = new Groups();
        		}
        		groups.setName( groupsRequest.getName() );
        		groupsService.save(groups);
        		
    		} else {
    			logger.info("name is null.");
    		}
    	} catch ( Exception ex ) {
    		logger.error("error in save : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	return response;
    	
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable Byte id) {
		Response response = new Response();
		try {
			groupsService.delete(id);
			response.setStatus("Success");
		} catch (Exception e) {
			response.setStatus("Fail");
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}
    
}
