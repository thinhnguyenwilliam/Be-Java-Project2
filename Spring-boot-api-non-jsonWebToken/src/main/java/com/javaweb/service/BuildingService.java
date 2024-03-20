package com.javaweb.service;

import java.util.List;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService 
{
	//List<BuildingDTO> findAll(String name, Integer numberOfBasement);
	
	List<BuildingDTO> findByName(String name);
	List<BuildingDTO> findByFloorArea(Integer floorArea);
	List<BuildingDTO> findByDistrict(Integer district);
	List<BuildingDTO> findByWard(Integer ward);
	List<BuildingDTO> findByStreet(String street);
	List<BuildingDTO> findByNumberOfBasement(Integer numberOfBasement);
	List<BuildingDTO> findByDirection(String direction);
	List<BuildingDTO> findByLevel(String level);
	List<BuildingDTO> findByAreaFrom(Integer areFrom);
	List<BuildingDTO> findByAreaTo(Integer areTo);
	List<BuildingDTO> findByRentPriceFrom(Integer rentPriceFrom);
	List<BuildingDTO> findByRentPriceTo(Integer rentPriceTo);
	List<BuildingDTO> findByManagerName(String managerName);
	List<BuildingDTO> findByManagerPhone(String managerPhone);
	List<BuildingDTO> findByStaffId(Integer staffId);
	List<BuildingDTO> findByTypeCode(List<String> typeCode);
}
