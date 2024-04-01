package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository 
{
	
	List<BuildingEntity> findBuilding(BuidingSearchBuilder builder);
	
}
