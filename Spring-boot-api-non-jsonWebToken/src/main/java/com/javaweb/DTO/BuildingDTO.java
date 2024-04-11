package com.javaweb.DTO;

import java.util.List;

import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDTO 
{
	private String name;
	private String address;//combine street, ward, districtName
	private Integer numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	private Integer floorArea;
	private Integer emptyArea;
	private String rentArea;//gom nhieu so cach nhau dau ", "
	private Integer brokerFee;
	private Integer serviceFee;
	private Integer rentprice;
	
	
	
}
