/**
 * 
 */
package com.chang.ng.phone.utils;

import java.util.UUID;

public class AuthTokenConfig {
	
	/**
	 * Generate UUID value as token and store into server memory as map
	 * 
	 * @return
	 */
	public static String generateToken(long userId) {
		String token = null;
		UUID tokenUUID = UUID.randomUUID();
		token = tokenUUID.toString();
	
		return token;
	}


	

}
