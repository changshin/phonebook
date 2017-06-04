package com.chang.ng.phone.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chang.ng.phone.data.model.Groups;
import com.chang.ng.phone.data.model.User;

public interface GroupsRepository extends JpaRepository<Groups, Serializable>, JpaSpecificationExecutor<Groups> {	

	@Query("select g from Groups g  where g.id=?1")
	public Groups getGroups(Long id); 
	
	@Query("select g from Groups g where UPPER(g.name) = UPPER(?1)")
	public Groups findByName(String groupName);
	
	@Query("select g from Groups g where (deleted is null or deleted =0 ) order by g.id")
	public List<Groups> getGroups();
	
	@Modifying
	@Query("Update Groups set deleted=1 where id=?1")
	public void delete(Long id);
	
	
	@Query(value="select g.id, g.name"
		+ ",sum( case when u.id is not null and (u.deleted is null or u.deleted = 0) then 1 else 0 end )"
		+ " from groups g left join user_groups u on g.id = u.groups_id"
		+ " where g.deleted is null or g.deleted = 0"
		+ " group by g.id order by g.id", nativeQuery = true)
	public List<Object[]> getGroupsWithUserCounts();
	
}