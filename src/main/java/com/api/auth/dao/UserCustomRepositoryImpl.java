package com.api.auth.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.auth.dto.Pagination;
import com.api.auth.dto.UserDto;
import com.api.auth.model.User;

@Repository(value = "userCustomRepository")
@Transactional
public class UserCustomRepositoryImpl implements UserCustomRepository {

	@Autowired
	EntityManager entityManager;

	@Override
	public UserDto customerList(Pagination pagination) {
		UserDto userDTO = new UserDto();
		List<User> userList = new ArrayList<User>();
		try {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> root = criteria.from(User.class);

			// ADD WHERE CONDITION
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.equal(root.get("type"), "End_User"));

			//SEARCH KEY
			if(pagination.getSearchKey() != null && !pagination.getSearchKey().isEmpty()){
				predicates.add(builder.like(root.get("fullName"), "%"+pagination.getSearchKey()+"%"));
			}
			
			criteria.orderBy(builder.desc(root.get("userId")));
			criteria.select(root).where(predicates.toArray(new Predicate[] {}));

			TypedQuery<User> query = entityManager.createQuery(criteria);

			// PAGINATION
			if (pagination.getPageNum() > 0) {
				query.setFirstResult((pagination.getPageNum()-1) * pagination.getNumPerPage());
				query.setMaxResults(pagination.getNumPerPage());
			}
			userList = query.getResultList();

			// GET ALL RECORD COUNT
			List<User> userListCount = null;
			TypedQuery<User> query1 = entityManager.createQuery(criteria);
			userListCount = query1.getResultList();

			userDTO.setUserList(userList);
			userDTO.setListCount(Long.parseLong(String.valueOf(userListCount.size())));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDTO;
	}

	@Override
	public UserDto admnUserList(Pagination pagination) {
		UserDto userDTO = new UserDto();
		List<User> userList = new ArrayList<User>();
		try {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> root = criteria.from(User.class);

			// ADD WHERE CONDITION
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.equal(root.get("type"), "Admin_User"));

			//SEARCH KEY
			if(pagination.getStatus() != null && !pagination.getStatus().isEmpty()){
				if(pagination.getStatus().equals("Active"))
					predicates.add(builder.equal(root.get("status"), true));
				if(pagination.getStatus().equals("InActive"))
					predicates.add(builder.equal(root.get("status"), false));
			}
			
			if(pagination.getRoleId() != null && pagination.getRoleId() > 0){
				predicates.add(builder.equal(root.get("roleId"), pagination.getRoleId()));
			}
			
			
			//SEARCH KEY
			if(pagination.getSearchKey() != null && !pagination.getSearchKey().isEmpty()){
				predicates.add(builder.like(root.get("fullName"), "%"+pagination.getSearchKey()+"%"));
			}
			
			criteria.orderBy(builder.desc(root.get("userId")));
			criteria.select(root).where(predicates.toArray(new Predicate[] {}));

			TypedQuery<User> query = entityManager.createQuery(criteria);

			// PAGINATION
			if (pagination.getPageNum() > 0) {
				query.setFirstResult((pagination.getPageNum()-1) * pagination.getNumPerPage());
				query.setMaxResults(pagination.getNumPerPage());
			}
			userList = query.getResultList();

			// GET ALL RECORD COUNT
			List<User> userListCount = null;
			TypedQuery<User> query1 = entityManager.createQuery(criteria);
			userListCount = query1.getResultList();

			userDTO.setUserList(userList);
			userDTO.setListCount(Long.parseLong(String.valueOf(userListCount.size())));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDTO;
	}
}
