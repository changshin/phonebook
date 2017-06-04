package com.chang.ng.phone.data.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chang.ng.phone.data.model.Token;


public interface TokenRepository extends JpaRepository<Token, Serializable> {
	@Query("select t from Token t where t.token=?1 order by t.id desc")
	public Token getByToken(String tokenName);

}
