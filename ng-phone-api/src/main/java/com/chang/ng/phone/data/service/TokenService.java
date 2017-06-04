package com.chang.ng.phone.data.service;

import com.chang.ng.phone.data.dao.TokenRepository;
import com.chang.ng.phone.data.model.Token;

public interface TokenService extends GenericService<Token, TokenRepository, Long> {

	
	public Token saveToken(Token token);

	public Token getToken(String tokenValue);

}
