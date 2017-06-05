package com.chang.ng.phone.controller;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.chang.crypto.CryptoUtil;
import com.chang.ng.phone.data.model.User;
import com.chang.ng.phone.data.service.UserService;
import com.chang.ng.phone.response.Response;
import com.chang.ng.phone.response.ResponseHelper;
import com.chang.ng.phone.utils.CommonUtils;
import com.chang.ng.phone.utils.Constants;
import com.chang.ng.phone.utils.ImageUtil;


//import com.rcm.platform.web.util.LoggedInUserInfo;

public class BaseController {

	private static final Logger logger = LogManager.getLogger(BaseController.class);


	@Autowired
	protected UserService userService;

	public ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("en"));

	/**
	 * This method is used to get application path or get image upload path from
	 * properties
	 * 
	 * @param request
	 * @return
	 */
	public Response getFaildResponseMessage(String language, String key) {
		bundle = ResourceBundle.getBundle("messages", new Locale(language));
		String message = CommonUtils.toUTF8(bundle.getString(key));
		return ResponseHelper.buildFailureResponse(message);
	}

	public Response  getFaildResponseMessage(String language, String key,String value) {
		bundle = ResourceBundle.getBundle("messages",new Locale(language));
	    String message = CommonUtils.toUTF8(MessageFormat.format(bundle.getString(key), value));
	    return ResponseHelper.buildFailureResponse(message);
	}

	public String getMessage(String language, String key) {
		bundle = ResourceBundle.getBundle("messages", new Locale(language));
		return CommonUtils.toUTF8(bundle.getString(key));
	}
	
	public String getLanguage(HttpServletRequest request) {
		String language = request.getHeader("Language");
		if  ( language == null ) {
			language = "en";
		}
		return language;
	}

	public User getLoggedInUser(HttpServletRequest request) {
		User user = null;
		try {
			if (request.getSession().getAttribute("user") != null) {
				user = (User) request.getSession().getAttribute("user");
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return user;
	}
	
}
