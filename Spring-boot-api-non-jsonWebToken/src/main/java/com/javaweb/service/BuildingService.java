package com.javaweb.service;

import java.util.List;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService 
{
	//List<BuildingDTO> findAll(String name, Integer numberOfBasement);
	
	List<BuildingDTO> findByName(String name);
	List<BuildingDTO> findByFloorArea(Integer floorArea);
}
