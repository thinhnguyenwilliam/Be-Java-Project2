package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuidingServiceImpl implements BuildingService
{
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findBuilding(Map<String, Object> params, List<String> typeCode) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findBuilding(params, typeCode);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}	
}
