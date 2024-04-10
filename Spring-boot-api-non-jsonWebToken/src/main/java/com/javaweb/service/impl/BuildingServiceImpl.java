package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.converters.BuidingSearchBuilderConverter;
import com.javaweb.converters.BuidlingConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuidlingConverter buidlingConverter;

	@Autowired
	private BuidingSearchBuilderConverter buidingSearchBuilderConverter;
	
	@Override
	public List<BuildingDTO> findBuilding(Map<String, Object> params, List<String> typeCode) 
	{
		BuidingSearchBuilder buidingSearchBuilder= buidingSearchBuilderConverter.toBuidingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buidingSearchBuilder);
		List<BuildingDTO> result = new ArrayList<>();

		for (BuildingEntity item : buildingEntities) 
		{
			BuildingDTO building = buidlingConverter.toBuildingDTO(item);//inject
			result.add(building);
		}
		return result;
	}
}
