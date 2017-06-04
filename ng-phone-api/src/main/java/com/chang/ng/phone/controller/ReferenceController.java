package com.chang.ng.phone.controller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chang.ng.phone.data.model.Gender;
import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.service.GroupsService;
import com.chang.ng.phone.data.service.ReferenceService;
import com.chang.ng.phone.response.GroupsVO;
import com.chang.ng.phone.response.Response;
import com.chang.ng.phone.response.ResponseHelper;
import com.chang.ng.phone.response.UserResponse;
import com.chang.ng.phone.utils.CommonUtils;
import com.chang.ng.phone.utils.ServletUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


@RequestMapping(value = "/reference")
@RestController
@CrossOrigin()	// for Cross-Origin Resource Sharing (CORS) in the response
public class ReferenceController {

	private static final Logger logger = LogManager.getLogger(ReferenceController.class);

	@Autowired
	ReferenceService referenceService;

	@Autowired
	GroupsService groupsService;

    public ReferenceController() {
    	//logger.info("ReferenceController consrtructor is called");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response getAll(){
    	Response response  = new Response();
    	try {
    		List<Gender> genders = referenceService.getGenders();
    		List<GroupsVO> groups = groupsService.getGroupsWithUserCounts();
    		HashMap<String, Object>  results = new HashMap<>();
    		results.put("genders", genders);
    		results.put("groups", groups);
        	response.setResult(results);
    	} catch ( Exception ex ) {
    		logger.error("error in getAll : ", ex);
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	
        return response;
    }
    
    @RequestMapping(value = "/gender/list", method = RequestMethod.GET)
    public Response getGenders(){
    	Response response  = new Response();
    	try {
        	response.setRecords(referenceService.getGenders());
    	} catch ( Exception ex ) {
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	
        return response;
    }

    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public Response getRoles(){
    	Response response  = new Response();
    	try {
    		List<GroupsVO> groups = groupsService.getGroupsWithUserCounts();
    	} catch ( Exception ex ) {
			response = ResponseHelper.buildExceptionResponse(ex);
    	}
    	
        return response;
    }


}
