package com.chang.ng.phone.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chang.ng.phone.data.model.Gender;

public interface GenderRepository extends JpaRepository<Gender, Serializable> {
	@Query("select g from Gender g where UPPER(g.name) = UPPER(?1)")
	public Gender findByName(String genderName);
	
	@Query("select g from Gender g order by g.id")
	public List<Gender> getGenders();
	
}