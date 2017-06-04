/**
 *
 */
package com.chang.ng.phone;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(AuthorizationInterceptor.class);
	public static final String TOKEN = "token";

  	@Override
  	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
  		HttpSession session = request.getSession();
  		String requestURI = request.getRequestURI();
  		if  ( true ) {
  			return true;
  		}
//  		if (requestURI.contains("/newsletter") ) {
//  			return true; // it doesn't need authentication
//  		}
    	if (requestURI.indexOf("logout") > 0) {
    		session.invalidate();
    	} else if (requestURI.indexOf("login") == -1) {
    		String token = getTokenFromRequest(request);
    		if (token == null) {
    			//logger.info("token is null,requestURI="+requestURI);
    			//writeResponse(response, ResponseHelper.TOKEN_EMPTY_EXCEPTION_RESPONSE);
    			try {
        			ResourceBundle bundle = ResourceBundle.getBundle("messages",new Locale(request.getHeader("Language").toString()));
//        		    String message = CommonUtils.toUTF8(bundle.getString("ERROR_MESSAGE_TOKEN_EMPTY"));
//        		    Response errorResponse = new Response("fail","SERVERVAL02", message,"Authentication Exception");
//        		    writeResponse(response, errorResponse);
    			} catch (Exception ex ) {
    				//writeResponse(response, ResponseHelper.TOKEN_EMPTY_EXCEPTION_RESPONSE);
    			}
    			return false;
    		}

    		String tokenFromSession = (String) session.getAttribute(TOKEN);
    		if (!token.equalsIgnoreCase(tokenFromSession)) {
    			//logger.info("token is not same seesion,requestURI="+requestURI);
    			try {
        			ResourceBundle bundle = ResourceBundle.getBundle("messages",new Locale(request.getHeader("Language").toString()));
        		    //String message = CommonUtils.toUTF8(bundle.getString("ERROR_MESSAGE_TOKEN_EMPTY"));
        		    //Response errorResponse = new Response("fail","SERVERVAL02", message,"Authentication Exception");
        		    //writeResponse(response, errorResponse);
    			} catch (Exception ex ) {
    				//writeResponse(response, ResponseHelper.TOKEN_INVALID_EXCEPTION_RESPONSE);
    			}
    			return false;
    		}

    	}

    	// TODO: Authorisation needs implement based on resource url
    	return super.preHandle(request, response, handler);

  	}

	private String getTokenFromRequest(final HttpServletRequest request) {
	
		String token = null;
		if (request.getHeader("Authorization") != null) {
			// get User token value from header Authorization
			token = request.getHeader("Authorization").toString();
		}
		return token;
	}

	private void writeResponse(final HttpServletResponse response, final Object exceptionResp) {
	    OutputStream out;
	    try {
	    	out = response.getOutputStream();
	    	ObjectMapper objectMapper = new ObjectMapper();
		    String responseJson = objectMapper.writeValueAsString(exceptionResp);
		    out.write(responseJson.getBytes());
	    } catch (IOException e) {
	    	logger.error(e);
	    }
	}
}
