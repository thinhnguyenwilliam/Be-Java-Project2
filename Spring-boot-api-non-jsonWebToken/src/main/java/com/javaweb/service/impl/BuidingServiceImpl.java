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
	

}
