package com.chang.ng.phone.data.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chang.ng.phone.data.model.User;

public interface UserRepository extends JpaRepository<User, Serializable>, JpaSpecificationExecutor<User>{
	
	
	@Query("select u from User u where email=?1 and password=?2")
	public User validateLogin(String email,String password)  ;
	
	@Query("select u from User u where u.id=?1")
	public User getUser(Long userId); 
	
	@Query("select u from User u where email=?1" )
	public User isEmailExist(String email);
	
	@Query("select u from User u where firstName=?1" )
	public List<User> getUsersByFirstname(String firstName);

	//@Query("select u from User u where groupId=?1" )
	//public List<User> getUsersByGroupId(Byte groupId);
	
	@Query(value="SELECT u.id,u.first_name,u.last_name,u.gender_id,u.email, u.created_date, u.updated_date"
		+ "	FROM user_groups ug"
		+ " join user u on ug.user_id = u.id"
		+ " join groups g on ug.groups_id = g.id"
		+ " where ug.groups_id=?1 and (ug.deleted is null or ug.deleted = 0) and (u.deleted is null or u.deleted = 0)"
		+ " group by ug.user_id" , nativeQuery = true)
	public List<Object[]> getUsersByGroupId(Integer groupsId);
	

	@Query("select u from User u where email=?1" )
	public User getUserByEmail(String email);

	@Query("select u from User u where u.deleted is null or u.deleted = 0")
	public List<User> getUsers();
	
	@Modifying
	@Query("Update User set deleted=1 where id=?1")
	public void delete(Long id);
	
	@Modifying
	@Query("Update User set deleted=1 where id in ?1")
	public void delete(List<Long> ids);
	
	
//	@Query("select u from User u where u.supervisor.id = ?1 and u.userFlag !=4 ORDER BY u.registeredOn DESC" )
//	public List<User> getUsersListForSupervisor(Long supervisLong userIdorID);
//	
//	@Query("select u from User u where (u.supervisor.id = ?1 or u.id =?1)  and u.userFlag !=4 ORDER BY u.registeredOn DESC" )
//	public List<User> getUsersListWithSupervisor(Long supervisorID);
//	
//	@Query("Update User set activeCampaigns=?1 where id=?2")
//	public void updateActiveFrames(Integer count, );
//
//	@Modifying
//	@Query("Update User u set u.subscriptionFromDate=?1, u.subscriptionToDate=?2 where u.supervisor.id = ?3 and u.role.id=4")
//	public void updateClientSubsacriptionDates(Date subscriptionFromDate, Date subscriptionToDate,Long supervisorID);
//
//	@Modifying
//	@Query("Update User set languageCode=?1 where id = ?2")
//	public void updateLanguageCode(String languageCode, Long userId);
//
//	@Modifying
//	@Query("Update User set userFlag=?1 where id = ?2")
//	public void updateUserFlag(Integer flag,Long userId);
//	
//	@Modifying
//	@Query("Update User set userStatus.id = ?1 where id = ?2")
//	public void updateStatus(Integer statusId,Long userId);
//	
//	@Modifying
//	@Query("Update User set domainId = ?1 where id = ?2")
//	public void updateDomain(Byte domainId,Long userId);
//	
//	@Modifying
//	@Query("Update User set nextFrameId = ?1 where id = ?2")
//	public void updateNextFrameId(Long nextFrameId,Long userId);
//	
//
//	@Query("select u from User u where u.role.id =:roleId and u.userFlag !=4 order by u.firstName, u.lastName ")
//	public List<User> getUserByRoleId(@Param("roleId") Integer roleId)  ;
//	
//	@Query("select u from User u where email=?1 and password=?2 and u.userFlag !=4")
//	public User validateConsumerLogin(String email,String password);
//
//	@Query("select u from User u where (userFlag = 2 or userFlag = 3) and supervisor is null ORDER BY u.createdOn DESC" )
//	public List<User> getNewUsers();
//
//	@Query("select u from User u where domainId=?1 and (userFlag = 2 or userFlag = 3) and supervisor is null ORDER BY u.createdOn DESC" )
//	public List<User> getNewUsers(byte domainId);
//
//	@Query("select u from User u where domainId=?1 and userFlag = 2 and supervisor is null ORDER BY u.createdOn DESC" )
//	public List<User> getDotComNewUsers(byte domainId);
//
//	@Query("select u from User u where u.role.id = ?1 and u.userFlag !=4 ")
//	public List<User> getSalesAssociates(Integer roleId);
//
//	@Query("select u from User u where u.supervisor.id in ?1 and u.role.id = ?2 and u.userStatus.id = ?3  and u.userFlag !=4 ")
//	public List<User> getActiveDirectAccounts(List<Long> userIds, Integer roleId, Integer statusId);
//	
//	@Query("select u from User u where u.supervisor.id in ?1 and u.role.id = ?2  and u.userFlag !=4 ")
//	public List<User> getDirectAccounts(List<Long> userIds, Integer roleId);
//
//	@Query("select u from User u where u.userFlag !=4 ORDER BY u.registeredOn DESC")
//	public List<User> findAllUsers();
//	
//	@Query(value="SELECT count(*) FROM user wher supervisor = b.id" , nativeQuery = true)
//	public List<Object[]> getActiveIndustries();
//	
//	@Query(value = "SELECT count(*) FROM user WHERE supervisor = ?1 and status = 2 and (user_flag = 2 or user_flag = 3)", nativeQuery = true)
//	public Integer getUserCountBySupervisorId(Integer supervisor); 
//	
//	@Query(value = "SELECT count(*) FROM user  WHERE subscription_plan_id=?1 and status=2 and subscription_to_date > now()", nativeQuery = true)
//	public Integer getUserCountBySubscriptionPlanId(Integer planId);
//
//	@Query("select u from User u where paymentProfileId=?1")
//	public User getUserByStripeId(String customerId); 

}
