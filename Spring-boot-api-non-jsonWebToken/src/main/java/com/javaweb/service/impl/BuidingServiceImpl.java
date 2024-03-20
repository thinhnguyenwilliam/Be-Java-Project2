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
	private BuildingRepository buildingRepository;
	
	/*
	@Override
	public List<BuildingDTO> findAll(String name, Integer numberOfBasement) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findAll(name, numberOfBasement);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setManagerName(item.getManagerName());
			
			result.add(building);
		}
		return result;
	}
	*/

	@Override
	public List<BuildingDTO> findByName(String name) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByName(name);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByFloorArea(Integer floorArea) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByFloorArea(floorArea);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByDistrict(Integer district) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByDistrict(district);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByWard(Integer ward) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByWard(ward);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByStreet(String street) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByStreet(street);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByNumberOfBasement(Integer numberOfBasement) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findNumberOfBasement(numberOfBasement);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByDirection(String direction) {
		List<BuildingEntity> buildingEntities=buildingRepository.findByDirection(direction);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByLevel(String level) {
		List<BuildingEntity> buildingEntities=buildingRepository.findByLevel(level);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByAreaFrom(Integer areFrom) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByAreaFrom(areFrom);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByAreaTo(Integer areTo) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByAreaTo(areTo);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByRentPriceFrom(Integer rentPriceFrom) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByRentPriceFrom(rentPriceFrom);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByRentPriceTo(Integer rentPriceTo) {
		List<BuildingEntity> buildingEntities=buildingRepository.findByRentPriceTo(rentPriceTo);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByManagerName(String managerName) {
		List<BuildingEntity> buildingEntities=buildingRepository.findByManagerName(managerName);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByManagerPhone(String managerPhone) {
		List<BuildingEntity> buildingEntities=buildingRepository.findByManagerPhone(managerPhone);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByStaffId(Integer staffId) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByStaffId(staffId);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByTypeCode(List<String> typeCode) 
	{
		List<BuildingEntity> buildingEntities=buildingRepository.findByTypeCode(typeCode);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities)
		{
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() +", "+ item.getWard() +", "+ item.getDistrictId());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setEmptyArea(null);
			
			StringBuilder sb=new StringBuilder();
			for(Integer valueRentArea:item.getValue())
				sb.append(valueRentArea).append(", ");
			int length = sb.length();
			if (length > 0)
				sb.delete(length - 2, length);//xoa 2 ky tu cuoi
			building.setRentArea(sb.toString());	
			
			building.setBrokerFee(item.getBrokeragefee());
			building.setServiceFee(item.getServicefee());
			building.setRentprice(item.getRentprice());
			
			result.add(building);
		}
		return result;
	}
}
