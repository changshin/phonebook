package com.chang.ng.phone.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chang.ng.phone.data.dao.TokenRepository;
import com.chang.ng.phone.data.model.Token;



@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TokenServiceImpl extends GenericServiceImpl<Token, TokenRepository, Long> implements
		TokenService {

	@Autowired
	private TokenRepository repository;

	@Override
	public Token saveToken(Token token) {
		try {
			return merge(token);
		} catch (Exception e) {
			throw new RuntimeException("Exception While Saving The User :: ", e);
		}
	}

	@Override
	public Token getToken(String tokenValue) {
		return repository.getByToken(tokenValue);
	}

	/**
	 * Constructor
	 * 
	 */
	public TokenServiceImpl() {
		super(Token.class);
	}

}
