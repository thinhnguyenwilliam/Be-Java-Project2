package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingReuestDTO;
import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.converters.BuidingSearchBuilderConverter;
import com.javaweb.converters.BuidlingConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service

public class BuildingServiceImpl implements BuildingService 
{
	@Autowired // vi la Bean va de inject cac dependency vao
	private ModelMapper modelMapper; // place under(position be carefully) 

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Autowired
	private BuidlingConverter buidlingConverter;

	@Autowired
	private BuidingSearchBuilderConverter buidingSearchBuilderConverter;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		BuidingSearchBuilder buidingSearchBuilder = buidingSearchBuilderConverter.toBuidingSearchBuilder(params,
				typeCode);

		// List<BuildingEntity> buildingEntities =
		// buildingRepository.findBuilding(buidingSearchBuilder);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buidingSearchBuilder);
		// BuildingEntity buildingEntity =
		// buildingRepository.findById(3).get();//findOne cung la tim theo ID
		//List<BuildingEntity> buildingEntities = buildingRepository.findByNameContainingAndWardContaining(buidingSearchBuilder.getName(), "Phường 6");
		
		
		
		
		List<BuildingDTO> result = new ArrayList<>();

		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = buidlingConverter.toBuildingDTO(item);// inject
			result.add(building);
		}
		return result;
	}

	@Override
	public void createBuilding(BuildingReuestDTO buildingReuestDTO) 
	{
		BuildingEntity buildingEntity = modelMapper.map(buildingReuestDTO, BuildingEntity.class);
		
		
		//This line saves the newly created BuildingEntity object into the database using a repository
		//.save: neu buildingEntity co ID thi se update(sua lai lun), nguoc lai se la them moi
		buildingRepository.save(buildingEntity);//luu y: building table run before rentArea table
		
		
		
		//List<RentAreaEntity> rentAreaEntities=new ArrayList<>();
		RentAreaEntity rentAreaEntity1=new RentAreaEntity();
		rentAreaEntity1.setBuilding(buildingEntity); // Set the buildingId directly
		rentAreaEntity1.setValue(200);
		
		
		RentAreaEntity rentAreaEntity2=new RentAreaEntity();
		rentAreaEntity2.setBuilding(buildingEntity); // Set the buildingId directly
		rentAreaEntity2.setValue(300);
		
		
		//rentAreaEntities.add(rentAreaEntity1);
		//rentAreaEntities.add(rentAreaEntity2);
		
		
		rentAreaRepository.save(rentAreaEntity1);
		rentAreaRepository.save(rentAreaEntity2);
		
		
		//  buildingEntity.setRentAreas(rentAreaEntities);
	}

	@Override
	public void deleteBuilding(List<Integer> ids) 
	{
//		for(Integer id:ids)
//			buildingRepository.deleteById(id);
		
		BuildingEntity buildingEntity=buildingRepository.findById(ids.get(0)).get();
		rentAreaRepository.deleteAllByBuilding(buildingEntity);
		buildingRepository.deleteByIdIn(ids);
	}

}
