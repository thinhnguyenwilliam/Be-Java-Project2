package com.javaweb.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Component // This annotation is used for automatic java bean detection, no constructor
			// like Interface
public class BuidlingConverter 
{


	@Autowired // vi la Bean va de inject cac dependency vao
	private ModelMapper modelMapper;

	public BuildingDTO toBuildingDTO(BuildingEntity item) 
	{
		// BuildingDTO.class : generic class
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);

		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());

		// Corrected usage of map and collect for joining rent area values
        String rentAreas = item.getRentAreas().stream()
                            .map(x -> x.getValue().toString())
                            .collect(Collectors.joining(", "));
        building.setRentArea(rentAreas);

		return building;
	}
}
