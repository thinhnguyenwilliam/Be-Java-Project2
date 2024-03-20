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
	List<BuildingEntity> findByName(String name);
	List<BuildingEntity> findByFloorArea(Integer floorArea);
	List<BuildingEntity> findByDistrict(Integer district);
	List<BuildingEntity> findByWard(Integer ward);
	List<BuildingEntity> findByStreet(String street);
	List<BuildingEntity> findNumberOfBasement(Integer numberOfBasement);
	List<BuildingEntity> findByDirection(String direction);
	List<BuildingEntity> findByLevel(String level);
	List<BuildingEntity> findByAreaFrom(Integer areaFrom);
	List<BuildingEntity> findByAreaTo(Integer areaTo);
	List<BuildingEntity> findByRentPriceFrom(Integer rentPriceFrom);
	List<BuildingEntity> findByRentPriceTo(Integer rentPriceTo);
	List<BuildingEntity> findByManagerName(String managerName);
	List<BuildingEntity> findByManagerPhone(String managerPhone);
	List<BuildingEntity> findByStaffId(Integer staffId);
	List<BuildingEntity> findByTypeCode(List<String> typeCode);
}
