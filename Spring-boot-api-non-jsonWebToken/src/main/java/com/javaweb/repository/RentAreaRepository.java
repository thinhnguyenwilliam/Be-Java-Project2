package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository
{
	List<Integer> findValues(Integer buildingId);
}
