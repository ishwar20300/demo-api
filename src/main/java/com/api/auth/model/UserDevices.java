package com.api.auth.model;

import java.io.Serializable;
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
 * @since 26-08-2021
 * 
 */

@Entity
@Table(name = "auth_user_devices")
public class UserDevices implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_devices_id")
	private Long userDevicesId;

	@Column(name = "fcm")
	private String fcm;

	@Column(name = "platform")
	private String platform;

	@Column(name = "version")
	private String version;

	@Column(name = "manufacturer")
	private String manufacturer;

	@Column(name = "model")
	private String model;

	@Column(name = "unique_device_id")
	private String uniqueDeviceId;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	public Long getUserDevicesId() {
		return userDevicesId;
	}

	public void setUserDevicesId(Long userDevicesId) {
		this.userDevicesId = userDevicesId;
	}

	public String getFcm() {
		return fcm;
	}

	public void setFcm(String fcm) {
		this.fcm = fcm;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUniqueDeviceId() {
		return uniqueDeviceId;
	}

	public void setUniqueDeviceId(String uniqueDeviceId) {
		this.uniqueDeviceId = uniqueDeviceId;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	
}
