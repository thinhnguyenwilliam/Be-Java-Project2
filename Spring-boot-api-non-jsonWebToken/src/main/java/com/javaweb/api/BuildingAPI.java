package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequireException;
import com.javaweb.service.BuildingService;

@RestController
//this is place receive request


@PropertySource("classpath:application.properties")

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
}
