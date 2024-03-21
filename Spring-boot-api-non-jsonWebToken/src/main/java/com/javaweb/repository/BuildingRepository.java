package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository 
{
	//List<BuildingEntity> findAll(String name, Integer numberOfBasement);
	//void delete(Integer[] ids);
	
	
	/*
	 * em bat dau lam tu day
	 */
	List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> typeCode);
	
}
