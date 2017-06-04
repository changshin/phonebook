package com.chang.ng.phone.data.service;

import java.util.List;
import com.chang.ng.phone.data.model.Gender;
import com.chang.ng.phone.data.model.Groups;

public interface ReferenceService { 

	public List<Gender> getGenders();
	public List<Groups> getGroups();
	
}
