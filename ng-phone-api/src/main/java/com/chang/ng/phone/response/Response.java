package com.chang.ng.phone.response;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.chang.ng.phone.utils.ApiConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_NULL)
public class Response {
	@JsonProperty("status")
	private String status;
	@JsonProperty("err_code")
	private String errorCode;
	@JsonProperty("err_msg")
	private String errorMessage;
	@JsonProperty("exception_type")
	private String exceptionType;
	@JsonProperty("result")
	private Object result;

	@JsonProperty("records")
	private Collection records;

	/**
	 * Constructor
	 * 
	 */
	public Response() {
		this.status = ApiConstants.STATUS_SUCCESS;
	}

	/**
	 * Constructor
	 * 
	 * @param result
	 */
	public Response(Object result) {
		this.status = ApiConstants.STATUS_SUCCESS;
		this.result = result;
	}

	/**
	 * Constructor
	 * 
	 * @param result
	 * @param status
	 */
	public Response(Object result, String status) {
		this.status = status;
		this.result = result;
	}

	/**
	 * Constructor
	 * 
	 * @param status
	 * @param errorCode
	 * @param errorMessage
	 * @param exceptionType
	 */
	public Response(String status, String errorCode, String errorMessage,
			String exceptionType) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exceptionType = exceptionType;
	}

	/**
	 * Constructor
	 * 
	 * @param status
	 * @param errorCode
	 * @param errorMessage
	 * @param exceptionType
	 */
	public Response(String status, String errorCode, String errorMessage) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;

	}

	/*
	 * * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getErrorCode() {
		return errorCode;
	}

	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}

	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	public String getExceptionType() {
		return exceptionType;
	}

	
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	
	public Object getResult() {
		return result;
	}

	
	public void setResult(Object result) {
		this.result = result;
	}

	public Collection getRecords() {
		return records;
	}

	public void setRecords(Collection records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
