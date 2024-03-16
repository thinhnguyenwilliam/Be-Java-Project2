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

   
public class BuildingAPI 
{
	 
	@Autowired
	private BuildingService X;
	
	
	@GetMapping(value = "/api/Building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			                           @RequestParam(name = "typeCode", required = false) List<String> res3,
			                           @RequestParam(value="ten", required = false) String res1,
			                           @RequestParam(value="soTangHam", required = false) Integer res5) {
		List<BuildingDTO> res=X.findAll(res1, res5);
		return res;
	}


	
//	public void validateData(BuildingDTO b1)
//	{
//		if(b1.getName()==null || b1.getName().equals("") || b1.getDistrictId() ==null)
//			throw new FieldRequireException("ten hoac DistrictId bi NULL");
//	}
	
	@PostMapping(value = "/api/Building")
	public Object createBuilding(@RequestBody BuildingDTO res4) 
	{

		//validateData(res4);

		
		System.out.print("Ok kg gap loi");
		return res4;
	}     
	
	
	
	@DeleteMapping(value = "/api/Building/{IDs}/{NAMEs}/{HOME}")
	public void deleteBuilding(@PathVariable List<Long> IDs,
							   @PathVariable List<String> NAMEs,
							   @PathVariable String HOME) {
		System.out.print("Ok 3");
	}

}
