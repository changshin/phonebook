package com.chang.ng.phone.response;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.chang.ng.phone.utils.ApiConstants;
import com.chang.ng.phone.utils.ApiErrorConstants;

public class ResponseHelper {

	/**
	 * Reference to EXCEPTION_RESPONSE
	 */
	public static Response EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_UNKNOWN_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_UNKNOWN_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	

	/**
	 * Reference to TOKEN_EMPTY_EXCEPTION_RESPONSE
	 */
	public static Response TOKEN_EMPTY_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_TOKEN_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TOKEN_EMPTY,
			ApiErrorConstants.ERROR_TYPE_AUTHENTICATION_EXCEPTION);

	/**
	 * Reference to TOKEN_INVALID_EXCEPTION_RESPONSE
	 */
	public static Response TOKEN_INVALID_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_TOKEN_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TOKEN_INVALID,
			ApiErrorConstants.ERROR_TYPE_AUTHENTICATION_EXCEPTION);

	/**
	 * Reference to BAD_CREDENTIALS_EXCEPTION_RESPONSE
	 */
	public static Response BAD_CREDENTIALS_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_BAD_CREDENTIALS_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_BAD_CREDENTIALS,
			ApiErrorConstants.ERROR_TYPE_AUTHENTICATION_EXCEPTION);
	/**
	 * Reference to PENDING_STATUS_EXCEPTION_RESPONSE
	 */
	public static Response PENDING_STATUS_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_BAD_CREDENTIALS_EXCEPTION,
			ApiErrorConstants.ERROR_USER_PENDING_STATUS,
			ApiErrorConstants.ERROR_TYPE_AUTHENTICATION_EXCEPTION);
	
	/**
	 * Reference to BAD_CREDENTIALS_EXCEPTION_RESPONSE
	 */
	public static Response UNAUTHORIZED_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ADMIN_ERROR_CODE_UNAUTHORIZED_EXCEPTION,
			ApiErrorConstants.ADMIN_ERROR_MESSAGE_UNAUTHORIZED,
			ApiErrorConstants.ERROR_TYPE_AUTHORIZATION_EXCEPTION);

	/**
	 * Reference to INVALID_ID_EXCEPTION_RESPONSE
	 */
	public static Response INVALID_ID_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_INVALID_ID_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_INVALID_ID,
			ApiErrorConstants.ERROR_TYPE_BUSSINESS_EXCEPTION);
/**
 * No valid subscripton plan found
 */
	public static Response NO_SUBSCRIPTION_PLAN_ERROR_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_INVALID_PLAN_EXCEPTION,
			ApiErrorConstants.ERROR_NO_SUBSCRIPTION_PLAN,
			ApiErrorConstants.ERROR_TYPE_BUSSINESS_EXCEPTION);
	
	/**
	 * Reference to VALIDATION_EXCEPTION
	 */
	public static Response VALIDATION_EXCEPTION_FOR_INVALID_EMAIL = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_INVALID_EMAIL_ID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	/**
	 * Reference to
	 */
	public static Response DUPLICATE_EMAIL_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_DUPLICATE_EMAIL_ID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static Response INVALID_EMAIL_ID_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.INVALID_EMAIL_ID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static Response INVALID_CAPTCHA_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.INVALID_CAPTCHA_ERROR,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	/**
	 * Duplicate email during registration
	 */
	public static Response VALIDATION_EXCEPTION_FOR_USED_TOKEN_PASSWORD_RESET = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TOKEN_USED,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static Response VALIDATION_EXCEPTION_FOR_USED_TOKEN_EMAIL_ACTIVATION = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TOKEN_USED,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static Response INVALID_TOKEN_EMAIL_ACTIVATION = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_ACTIVATION_TOKEN_INVALID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static final Response INVALID_UPLOAD_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_NO_VALID_UPLOAD_FOUND,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static final Response NO_FRAMES_AVAILABLE_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.NO_FRAME_COUNT_ERROR,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_IMAGE_FILE_SIZE_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_IMAGE_FILE_SIZE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_VIDEO_FILE_SIZE_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_VIDEO_FILE_SIZE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_DOC_FILE_SIZE_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_DOC_FILE_SIZE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_IMAGE_FILE_ENTENSION_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_IMAGE_FILE_EXTENSION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_VIDEO_FILE_ENTENSION_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_VIDEO_FILE_EXTENSION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	public static final Response INVALID_DOC_FILE_ENTENSION_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_DOC_FILE_EXTENSION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static final Response EMAIL_ID_ALREADY_IN_USE_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_EMAIL_ID_IN_USE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);

	public static Response DUPLICATE_KEYNAME_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_DUPLICATE_EMAIL_ID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);


	public static Response CARD_DECLINE_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_CARD_DECLINE,
			ApiErrorConstants.ERROR_MESSAGE_CARD_DECLINE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	
	public static Response CARD_INVALID_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_CARD_INVALID,
			ApiErrorConstants.ERROR_MESSAGE_CARD_INVALID,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	
	public static Response CARD_INVALID_DATE_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_CARD_INVALID_DATE,
			ApiErrorConstants.ERROR_MESSAGE_CARD_INVALID_DATE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	
	public static Response CARD_EXCEPTION_RESPONSE = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_CARD_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_CARD_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	
	
	public static Response INVALID_CART_QUANTITY_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_CART_INVALID_COUNT_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_CODE_CART_INVALID_COUNT_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);
	
	public static Response INVALID_ADDRESS_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_INAVLID_ADDRESS_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_CODE_INAVLID_ADDRESS_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);

	public static Response invalidImageSizeError =new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_INVALID_IMAG_EXCEPTION,
			ApiErrorConstants.ERROR_MESSAGE_IMAGE_SIZE,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_VALIDATION_EXCEPTION);
	
	
	public static Response INVALID_TRANSACTION_ID_ERROR = new Response(
			ApiConstants.STATUS_FAILURE,
			ApiErrorConstants.ERROR_CODE_INVALID_TXN_ID_EXCEPTION,
			ApiErrorConstants.ERROR_CODE_INVALID_TXN_REQUEST,
			ApiErrorConstants.ERROR_MESSAGE_TYPE_UNKNOWN_EXCEPTION);

	
	/**
	 * @param obj
	 * @return Response
	 */
	public static Response buildResponse(Object obj) {
		return new Response(obj);
	}
	
	
	public static Response buildExceptionResponse(Exception e) {

		Response res = new Response(ApiConstants.STATUS_FAILURE,
				ApiErrorConstants.ERROR_CODE_UNKNOWN_EXCEPTION, e.getMessage(),
				ApiErrorConstants.ERROR_CODE_UNKNOWN_EXCEPTION);

		// TODO: based on type of exception , send corresponding error
		// codes/messages
		return res;

	}

	/**
	 * @param obj
	 * @return Response
	 */
	public static Response buildIdResponse(Object id) {
		Response res = new Response();
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(ApiConstants.ID_NAME, id.toString());
		res.setResult(properties);
		return res;

	}

	/**
	 * @return Response
	 */
	public static Response buildResponse() {
		return new Response();

	}

	public static Response buildSuccessReesponse(String successMessage) {
		return new Response(successMessage);

	}
	public static Response buildFailureResponse(String errorResponse) {
		return new Response(ApiConstants.STATUS_FAILURE, ApiErrorConstants.ERROR_CODE_FAILURE, errorResponse);

	}
	
	public static Response buildErrorResponse(String errorMessage) {
		return new Response(errorMessage);

	}
}