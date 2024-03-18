package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	/*
	@Override
	public List<BuildingDTO> findAll(String name, Integer numberOfBasement) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findAll(name, numberOfBasement);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setManagerName(item.getManagerName());
			
			result.add(building);
		}
		return result;
	}
	*/

	@Override
	public List<BuildingDTO> find(String name) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.find(name);
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
			sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());
			
			
			result.add(building);
		}
		return result;
	}
	

}
