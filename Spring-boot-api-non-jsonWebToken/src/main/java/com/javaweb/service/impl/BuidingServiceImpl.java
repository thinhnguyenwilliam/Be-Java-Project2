package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuidingServiceImpl implements BuildingService
{
	@Autowired
	private BuildingRepository A;
	
	
	@Override
	public List<BuildingDTO> findAll(String TimTheoTen, Integer TimTheoSoTangHam) 
	{
		List<BuildingEntity> a=A.findAll(TimTheoTen, TimTheoSoTangHam);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:a)
		{
			BuildingDTO b=new BuildingDTO();
			b.setName(item.getName());
			b.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			b.setManagerName(item.getManagerName());
			
			result.add(b);
		}
		return result;
	}
	

}
