package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository 
{
	//List<BuildingEntity> findAll(String name, Integer numberOfBasement);
	//void delete(Integer[] ids);
	
	
	/*
	 * em bat dau lam tu day
	 */
	List<BuildingEntity> find(String name);
}
