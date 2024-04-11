package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingReuestDTO;
import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequireException;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@RestController
//this is place receive request

@PropertySource("classpath:application.properties")

@Transactional
//use function persist,... tru ham find thoi

public class BuildingAPI 
{
	@Value("${dev.Nguyen}")
	private String systemValue;

	@Autowired
	private BuildingService buildingService;

	@GetMapping(value = "/api/Building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typeCode", required = false) List<String> typeCode) 
	{
		System.out.println(systemValue);
		List<BuildingDTO> result = buildingService.findBuilding(params, typeCode);
		return result;

	}

/////////////////////////////////////////////////////////
//test case Brother Strong: persist(them moi), merge(update,sua thong tin),
//nen dung model mapper
	@PersistenceContext
	

	private EntityManager entityManager;
	
	@Autowired // vi la Bean va de inject cac dependency vao
	private ModelMapper modelMapper; // place under(position be carefully) 

	@PostMapping(value = "/api/Building")
	public void createBuilding(@RequestBody BuildingReuestDTO buildingReuestDTO) 
	{
		BuildingEntity buildingEntity = modelMapper.map(buildingReuestDTO, BuildingEntity.class);
		// Perform any additional operations and persist the entity

		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingReuestDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);

		//entityManager.persist(buildingEntity);
		entityManager.merge(buildingEntity);
	}
	
	///////////////////////////
	
// minh tu nghi ra
//	@DeleteMapping(value = "/api/Building/{ids}")
//	public void deleteBuildings(@PathVariable Integer[] ids) 
//	{
//	    for (Integer buildingId : ids) 
//	    {
//	        BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, buildingId);
//	        if (buildingEntity != null)
//	            entityManager.remove(buildingEntity);
//	    }
//	}

	
	
	///////////
	@DeleteMapping(value = "/api/Building/{id}")
	public void deleteBuilding(@PathVariable Integer id) 
	{
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
	}
}
