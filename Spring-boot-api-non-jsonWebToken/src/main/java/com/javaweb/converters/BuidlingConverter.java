package com.javaweb.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;




@Component // This annotation is used for automatic java bean detection, no constructor like Interface
public class BuidlingConverter 
{
	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired //vi la Bean va de inject cac dependency vao
	private ModelMapper modelMapper;
	
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) 
	{
		//BuildingDTO.class : generic class
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);


		DistrictEntity district = districtRepository.findById(item.getDistrictId());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district.getName());


		// Retrieve values from the rent area repository
		List<String> rentAres = rentAreaRepository.findValues(item.getId());
		building.setRentArea(String.join(", ", rentAres));


		
		return building;
	}
}
