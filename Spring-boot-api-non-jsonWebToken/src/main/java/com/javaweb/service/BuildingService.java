package com.javaweb.service;

import java.util.List;
import java.util.Map;



import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingReuestDTO;

public interface BuildingService 
{
	List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode);
	void createBuilding(BuildingReuestDTO buildingReuestDTO);
	void deleteBuilding(List<Integer> ids);
}
