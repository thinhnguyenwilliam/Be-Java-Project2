package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequireException;
import com.javaweb.service.BuildingService;

@RestController
//this is place receive request


public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;

	
	@GetMapping(value = "/api/Building")

	
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
										  @RequestParam(value = "maLoai", required = false) List<String> typeCode) 
	{

		List<BuildingDTO> result = buildingService.findBuilding(params, typeCode);
		return result;

	}



}
