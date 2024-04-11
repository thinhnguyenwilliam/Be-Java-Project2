package com.javaweb.DTO;

import java.util.List;

import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingReuestDTO 
{
	//ten cac bien phai y chang tren postman (tinh lun in hoa in thuong)
	private Integer Id;
	private String name;
	private String ward;
	private String street;
	private Integer districtId;
	private Integer numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	private Integer floorArea;
	private Integer emptyArea;
	private Integer brokerageFee;
	private Integer serviceFee;
	private Integer rentPrice;
	private String direction;
	private String level;

}
