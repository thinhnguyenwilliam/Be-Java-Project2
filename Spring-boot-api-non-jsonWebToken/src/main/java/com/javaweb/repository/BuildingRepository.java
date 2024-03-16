package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository 
{
	List<BuildingEntity> findAll(String TimTheoTen, Integer TimTheoSoTangHam);
	
	
	void delete(Integer[] ids);
}
