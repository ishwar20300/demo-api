package com.api.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByMobile(String mobile);

	User findByEmail(String email);

	User findByuuid(String uuid);

	User findByUserId(Long userId);

	@Query(value = " SELECT u.full_name,u.mobile, u.email, u.created_date, r.role_name , "
			+ " CASE u.`status` WHEN 1 THEN 'Active' ELSE 'Inactive' END status FROM auth_user u "
			+ " INNER JOIN auth_role r  ON u.role_id = r.role_id WHERE u.type = 'Admin_User' ORDER BY u.full_name ", nativeQuery = true)
	List<List<String>> findAllObject();

}
