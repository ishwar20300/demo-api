package com.api.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_action")
public class AuthAction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_action")
	private Long authActionId;

	@Column(name = "action_name", unique = true, nullable = false, length = 255)
	private String actionName;

	@Column(name = "action_description", nullable = false, length = 255)
	private String actionDescription;

	public Long getAuthActionId() {
		return authActionId;
	}

	public void setAuthActionId(Long authActionId) {
		this.authActionId = authActionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	
	
	
	
}
