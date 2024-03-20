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
	private BuildingService buildingService;
	@GetMapping(value = "/api/Building")
	
	
	
	/*
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			                           @RequestParam(value = "maLoai", required = false) List<String> typeCode,
			                           @RequestParam(value="ten", required = false) String name,
			                           @RequestParam(value="soTangHam", required = false) Integer numberOfBasement) {
		List<BuildingDTO> result=buildingService.findAll(name, numberOfBasement);
		return result;
	}
	*/
	
	
	/*
	 * em bat dau lam tu day
	 */
	public List<BuildingDTO> findByNameBuilding(@RequestParam Map<String, Object> params,
												@RequestParam(value = "maLoai", required = false) List<String> typeCode) 
	{
		if (params.containsKey("ten"))
		{
			String name = (String) params.get("ten");
			List<BuildingDTO> result=buildingService.findByName(name);
			return result;
		}
		
		if (params.containsKey("dienTichSan"))
		{
			Integer floorArea = Integer.valueOf((String) params.get("dienTichSan"));
		    List<BuildingDTO> result = buildingService.findByFloorArea(floorArea);
		    return result;
		}	
		
		if(params.containsKey("quan"))
		{
			Integer district = Integer.valueOf((String) params.get("quan"));
		    List<BuildingDTO> result = buildingService.findByDistrict(district);
		    return result;
		}
		
		if(params.containsKey("phuong"))
		{
			Integer ward = Integer.valueOf((String) params.get("phuong"));
		    List<BuildingDTO> result = buildingService.findByWard(ward);
		    return result;
		}
		
		if(params.containsKey("duong"))
		{
			String street = (String) params.get("duong");
		    List<BuildingDTO> result = buildingService.findByStreet(street);
		    return result;
		}
		
		if(params.containsKey("soTangHam"))
		{
			Integer numberOfBasement = Integer.valueOf((String) params.get("soTangHam"));
		    List<BuildingDTO> result = buildingService.findByNumberOfBasement(numberOfBasement);
		    return result;
		}
		
		if(params.containsKey("huong"))
		{
			String direction = (String) params.get("huong");
			List<BuildingDTO> result=buildingService.findByDirection(direction);
			return result;
		}
		
		if(params.containsKey("hang"))
		{
			String level = (String) params.get("hang");
			List<BuildingDTO> result=buildingService.findByLevel(level);
			return result;
		}
		
		if(params.containsKey("dienTichTu"))
		{
			Integer areaFrom = Integer.valueOf((String) params.get("dienTichTu"));
		    List<BuildingDTO> result = buildingService.findByAreaFrom(areaFrom);
		    return result;
		}
		
		if(params.containsKey("dienTichDen"))
		{
			Integer areaTo = Integer.valueOf((String) params.get("dienTichDen"));
		    List<BuildingDTO> result = buildingService.findByAreaTo(areaTo);
		    return result;
		}
		
		if(params.containsKey("giaThueTu"))
		{
			Integer rentPriceFrom = Integer.valueOf((String) params.get("giaThueTu"));
		    List<BuildingDTO> result = buildingService.findByRentPriceFrom(rentPriceFrom);
		    return result;
		}
		
		if(params.containsKey("giaThueDen"))
		{
			Integer rentPriceTo = Integer.valueOf((String) params.get("giaThueDen"));
		    List<BuildingDTO> result = buildingService.findByRentPriceTo(rentPriceTo);
		    return result;
		}
		
		if(params.containsKey("tenQuanLy"))
		{
			String managerName = (String) params.get("tenQuanLy");
			List<BuildingDTO> result=buildingService.findByManagerName(managerName);
			return result;
		}
		
		if(params.containsKey("sdtQuanLy"))
		{
			String managerPhone = (String) params.get("sdtQuanLy");
			List<BuildingDTO> result=buildingService.findByManagerPhone(managerPhone);
			return result;
		}
		
		if(params.containsKey("maNhanVien"))
		{
			Integer staffId = Integer.valueOf((String) params.get("maNhanVien"));
		    List<BuildingDTO> result = buildingService.findByStaffId(staffId);
		    return result;
		}
		
		if(params.containsKey("maLoai"))
		{
		    List<BuildingDTO> result = buildingService.findByTypeCode(typeCode);
		    return result;
		}
		
		return null;
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
