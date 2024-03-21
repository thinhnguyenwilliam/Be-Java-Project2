package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService 
{
	//List<BuildingDTO> findAll(String name, Integer numberOfBasement);
	
	List<BuildingDTO> findBuilding(Map<String, Object> params, List<String> typeCode);
	
}
