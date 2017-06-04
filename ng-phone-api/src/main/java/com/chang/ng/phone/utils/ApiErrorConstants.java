package com.chang.ng.phone.utils;

public class ApiErrorConstants {

	// invalid/missing params

	/**
	 * Reference to ERROR_CODE_MISSING_PARAMETER
	 */
	public static final String ERROR_CODE_MISSING_PARAMETER = "FS1001";

	/**
	 * Reference to ERROR_CODE_UNKNOWN_EXCEPTION
	 */
	public static final String ERROR_CODE_UNKNOWN_EXCEPTION = "FS1002";
	
	/**
	 * Reference to ERROR_MESSAGE_UNKNOWN_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_UNKNOWN_EXCEPTION = "Unknown Exception";

	/**
	 * Reference to ERROR_SQLEXCEPTION
	 */
	public static final String ERROR_SQLEXCEPTION = "Server Error";
	/**
	 * Reference to ERROR_MESSAGE_TYPE_SQL_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_TYPE_SQL_EXCEPTION = "Database Exception";
	/**
	 * Reference to ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION = "Validation Exception";
	/**
	 * Reference to ERROR_MESSAGE_TYPE_APPLICATION_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_TYPE_APPLICATION_EXCEPTION = "Application Exception";
	/**
	 * Reference to ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION = "Unknown Cloud Exception";
	/**
	 * Reference to ERROR_MESSAGE_NO_PERMISSION
	 */
	public static final String ERROR_MESSAGE_NO_PERMISSION = "No Method Permission Exception";
	/**
	 * Reference to ERROR_MESSAGE_UNEXPECTED_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_UNEXPECTED_EXCEPTION = "Unexpected Exception";
	/**
	 * Reference to ERROR_MESSAGE_DUPLICATE_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_DUPLICATE_EXCEPTION = "Data Already Exist Exception";
	/**
	 * Reference to ERROR_MESSAGE_AUTHENTICATION_EXCEPTION
	 */
	public static final String ERROR_MESSAGE_AUTHENTICATION_EXCEPTION = "Authentication Exception";

	// New Error Codes--------------------------------

	/**
	 * Reference to ERROR_TYPE_AUTHENTICATION_EXCEPTION
	 */
	public static final String ERROR_TYPE_AUTHENTICATION_EXCEPTION = "Authentication Exception";
	
	/**
	 * Reference to ERROR_TYPE_AUTHORIZATION_EXCEPTION
	 */
	public static final String ERROR_TYPE_AUTHORIZATION_EXCEPTION = "Authorization Exception";

	/**
	 * Reference to ERROR_TYPE_AUTHENTICATION_EXCEPTION
	 */
	public static final String ERROR_TYPE_BUSSINESS_EXCEPTION = "Bussiness Exception";

	/** ERROR CODES **/

	/**
	 * Reference to ERROR_CODE_BAD_CREDENTIALS_EXCEPTION
	 */
	public static final String ERROR_CODE_BAD_CREDENTIALS_EXCEPTION = "RCMVAL01";

	/**
	 * Reference to ERROR_CODE_TOKEN_VALIDATION_EXCEPTION
	 */
	public static final String ERROR_CODE_TOKEN_VALIDATION_EXCEPTION = "RCMVAL02";

	/**
	 * Reference to ERROR_CODE_INVALID_ID_EXCEPTION
	 */
	public static final String ERROR_CODE_INVALID_ID_EXCEPTION = "RCMBL01";
	
	public static final String ERROR_CODE_INVALID_PLAN_EXCEPTION = "RCMSP01";

	/** ERROR MESSAGES **/

	/**
	 * Reference to ERROR_MESSAGE_TOKEN_EMPTY
	 */
	public static final String ERROR_MESSAGE_BAD_CREDENTIALS = "Provide valid username and password";
	
	/**
	 * Reference to ERROR_MESSAGE_TOKEN_EMPTY
	 */
	public static final String ERROR_USER_PENDING_STATUS = "Email verification is pending";

	/**
	 * Reference to ERROR_MESSAGE_TOKEN_EMPTY
	 */
	public static final String ERROR_MESSAGE_TOKEN_EMPTY = "Your session is expired, please login again";// "Provide valid token value in AUTHORIZATION header";

	/**
	 * Reference to ERROR_MESSAGE_TOKEN_INVALID
	 */
	public static final String ERROR_MESSAGE_TOKEN_INVALID = "Your session is expired, please login again";

	/**
	 * Reference to ERROR_MESSAGE_TOKEN_EMPTY
	 */
	public static final String ERROR_MESSAGE_INVALID_ID = "Invalid Id";

	public static final String ERROR_MESSAGE_INVALID_EMAIL_ID = "Invalid password reset request, please provide correct email address";

	public static final String ERROR_MESSAGE_TOKEN_USED = "Your account is already activated";

	public static final String ERROR_MESSAGE_ACTIVATION_TOKEN_INVALID = "Unable to activate email address, Please check the email and try again";

	public static final String INVALID_EMAIL_ID = "Invalid Email Id";
	/**
	 * duplicate email id
	 */
	public static final String ERROR_MESSAGE_DUPLICATE_EMAIL_ID = "Email id is in use, please use another one";

	public static final String ERROR_CODE_VALIDATION_ERROR = "Invalid parameters present";

	public static final String ERROR_MESSAGE_NO_VALID_UPLOAD_FOUND = "Please upload valid file extension";

	public static final String ERROR_MESSAGE_IMAGE_FILE_EXTENSION = "Please upload valid file extension like jpg,jpeg,bmp,png,gif";

	public static final String ERROR_MESSAGE_VIDEO_FILE_EXTENSION = "Please upload valid file extension like mp4,flv,webm,mpg,ogv,mov,m4v,3gpp,quicktime";

	public static final String ERROR_MESSAGE_DOC_FILE_EXTENSION = "Please upload valid file extension like txt,doc,docx,xls,pdf";

	public static final String ERROR_MESSAGE_IMAGE_FILE_SIZE = "File size should not exceeded more than 1 MB";

	public static final String ERROR_MESSAGE_VIDEO_FILE_SIZE = "Video size should not exceeded more than 25 MB";

	public static final String ERROR_MESSAGE_DOC_FILE_SIZE = "Doc size should not exceeded more than 5 MB";

	public static final String NO_FRAME_COUNT_ERROR = "No valid frames are available in your account";

	public static final String ERROR_CODE_SUCCESS = "Success";
	public static final String ERROR_CODE_FAILURE = "Failure";
	
	public static final String ERROR_MESSAGE_DUPLICATE_KEYNAME = "Keyname already exists, please enter different";


	public static final String ERROR_CODE_CARD_EXCEPTION = "RCMCC01";
	public static final String ERROR_MESSAGE_CARD_EXCEPTION = "The transaction has been not completed due to unexpected error.";

	public static final String ERROR_CODE_CARD_INVALID_DATE = "RCMCC02";
	public static final String ERROR_MESSAGE_CARD_INVALID_DATE = "Expiration Date is invalid.";

	public static final String ERROR_CODE_CARD_DECLINE = "RCMCC03";
	public static final String ERROR_MESSAGE_CARD_DECLINE = "This transaction has been declined.";

	public static final String ERROR_CODE_CARD_INVALID = "RCMCC04";
	public static final String ERROR_MESSAGE_CARD_INVALID = "The credit card number is invalid.";

	public static final String INVALID_CAPTCHA_ERROR = "Invalid captcha error. Please enter correct text as displayed in the Image"; 
	
	/** ADMIN ERROR CODES **/

	/**
	 * Reference to ADMIN_ERROR_CODE_UNAUTHORIZED_EXCEPTION
	 */
	public static final String ADMIN_ERROR_CODE_UNAUTHORIZED_EXCEPTION = "RCMVAL401";
	public static final String ADMIN_ERROR_MESSAGE_UNAUTHORIZED = "Unauthorized Operation For This User.";

	public static final String ERROR_CODE_CART_INVALID_COUNT_EXCEPTION = "RCMCART01";
 
	public static final String ERROR_MESSAGE_CODE_CART_INVALID_COUNT_EXCEPTION = "Invalid quantity error. Quantity should be more than 0"; ;
	
	public static final String ERROR_CODE_INAVLID_ADDRESS_EXCEPTION = "RCMADDR01";
	
	public static final String ERROR_MESSAGE_CODE_INAVLID_ADDRESS_EXCEPTION = "Invalid Address and Zip Code";

	public static final String ERROR_EMAIL_ID_IN_USE = "Email Id already in use. Please login using existing credentials"; 
	
	public static final String ERROR_CODE_INVALID_IMAG_EXCEPTION = "RCMIMG01";
	public static final String ERROR_MESSAGE_IMAGE_SIZE="Image width and height should be minimum of 300 pixels to create a frame";
	
	public static final String ERROR_NO_SUBSCRIPTION_PLAN = "Invalid subscription configuration";

	public static final String ERROR_CODE_INVALID_TXN_ID_EXCEPTION = "Invalid transaction id"; 
	public static final String ERROR_CODE_INVALID_TXN_REQUEST = "Invalid refunds request. Original transaction not found"; 

}
