package com.javaweb.converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;




@Component // This annotation is used for automatic java bean detection, no constructor like Interface
public class BuidlingConverter 
{
	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	
	
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) 
	{
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());

		DistrictEntity district = districtRepository.findById(item.getDistrictId());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district.getName());

		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
		building.setFloorArea(item.getFloorArea());

		// Retrieve values from the rent area repository
		List<String> rentAres = rentAreaRepository.findValues(item.getId());
		building.setRentArea(String.join(", ", rentAres));

		building.setBrokerFee(item.getBrokeragefee());
		building.setServiceFee(item.getServicefee());
		building.setRentprice(item.getRentprice());
		
		return building;
	}
}
