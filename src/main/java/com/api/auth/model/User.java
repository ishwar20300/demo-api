package com.api.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ishwar Bathe
 */

@Entity
@Table(name = "auth_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "fullName")
	private String fullName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "mobile", unique = true)
	private String mobile;

	/**
	 * User Unique ID
	 */
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;

	@Column(name = "password")
	private String password;

	/**
	 * Ex. Admin,Employee etc
	 * 
	 */
	@Column(name = "role")
	private String role;

	@Column(name = "role_id")
	private Long roleId;

	/**
	 * Ex. 1] Admin_User 2] End_User
	 * 
	 */
	@Column(name = "type")
	private String type;

	/**
	 * Customer Unique Id
	 */
	@Column(name = "customer_no", unique = true)
	private String customerNo;
	
	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth")
	private String date_of_birth;

	@Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;


	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "created_by", nullable = true, updatable = false)
	private Long createdBy;

	@Column(name = "updated_by", nullable = true)
	private Long updatedBy;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	
	
	
	

}
