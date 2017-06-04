package com.chang.ng.phone.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chang.ng.phone.data.model.UserGroups;

public interface UserGroupsRepository extends JpaRepository<UserGroups, Serializable>, JpaSpecificationExecutor<UserGroups> {	

	@Query("select g from UserGroups g where g.id=?1")
	public UserGroups getUserGroups(Long id); 
	
	@Query("select g from UserGroups g where g.userId=?1 and (g.deleted is not null or g.deleted = 0) order by g.id")
	public List<UserGroups> getUserGroupsByUserId(Long userId);
	
	@Query("select g from UserGroups g where g.userId=?1 order by g.id")
	public List<UserGroups> getUserGroupsAllByUserId(Long userId);
	
	@Query("select g from UserGroups g where g.groupsId=?1  and (g.deleted is not null or g.deleted = 0) order by g.id")
	public List<UserGroups> getUserGroupsByGroupsId(Integer groupsId);
	
	@Modifying
	@Query("Update UserGroups set deleted=1 where id=?1")
	public void delete(Long id);
	
	@Modifying
	@Query("Update UserGroups set deleted =1 where groupsId =?1 and userId in ?2")
	public void delete(Integer groupsId, List<Long> ids);

	
	@Query(value="SELECT u.id, group_concat(u.groups_id), group_concat(g.name) FROM user_groups u"
			+ " join groups g on u.groups_id = g.id where u.user_id=?1 and (u.deleted is null or u.deleted = 0)"
			+ " group by u.user_id " , nativeQuery = true)
	public List<Object[]> getUserGroupsObjectByUserId(Long userId);

	
}