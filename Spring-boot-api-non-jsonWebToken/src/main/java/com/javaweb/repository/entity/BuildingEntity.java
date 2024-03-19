package com.javaweb.repository.entity;

import java.util.List;

public class BuildingEntity 
{
	//trong table building
	private String name;
	private String ward;
	private String street;
	private Integer districtId;
	private Integer numberOfBasement;
	private String managerName;
	private String managerNamePhoneNumber;
	private Integer floorArea;
	private Integer brokeragefee;
	private Integer servicefee;
	private Integer rentprice;
	private Integer Id;
	
	//trong table rentArea
	private List<Integer> value;
		
		
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
		
	public Integer getRentprice() {
		return rentprice;
	}
	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}
	public Integer getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(Integer brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public Integer getServicefee() {
		return servicefee;
	}
	public void setServicefee(Integer servicefee) {
		this.servicefee = servicefee;
	}
	public List<Integer> getValue() {
		return value;
	}
	public void setValue(List<Integer> value) {
		this.value = value;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
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
