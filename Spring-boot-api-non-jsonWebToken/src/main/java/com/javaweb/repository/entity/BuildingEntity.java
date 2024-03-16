package com.javaweb.repository.entity;

public class BuildingEntity 
{
	private String name;
	private String ward;
	private String street;
	private Integer districtId;
	private Integer numberOfBasement;
	private String managerName;
	private String managerNamePhoneNumber;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerNamePhoneNumber() {
		return managerNamePhoneNumber;
	}
	public void setManagerNamePhoneNumber(String managerNamePhoneNumber) {
		this.managerNamePhoneNumber = managerNamePhoneNumber;
	}
	
	

}
