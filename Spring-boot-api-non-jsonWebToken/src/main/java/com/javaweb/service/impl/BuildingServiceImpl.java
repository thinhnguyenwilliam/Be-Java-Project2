package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    
    @Autowired
    private RentAreaRepository rentAreaRepository;
    
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<BuildingDTO> findBuilding(Map<String, Object> params, List<String> typeCode) {
        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(params, typeCode);
        List<BuildingDTO> result = new ArrayList<>();

        for (BuildingEntity item : buildingEntities) 
        {
            BuildingDTO building = new BuildingDTO();
            building.setName(item.getName());
            
            
            
            // Retrieve district name using DistrictRepository
            String nameDistrict = districtRepository.findName(item.getDistrictId());           
            // Construct address string
            building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + nameDistrict);
            
            
            
            building.setNumberOfBasement(item.getNumberOfBasement());
            building.setManagerName(item.getManagerName());
            building.setManagerPhoneNumber(item.getManagerNamePhoneNumber());
            building.setFloorArea(item.getFloorArea());
            
            
            
            // Retrieve values from the rent area repository
            List<Integer> valuesFromTableRentArea = rentAreaRepository.findValues(item.getId());
            StringBuilder rentAreaStringBuilder = new StringBuilder();
            for (int i = 0; i < valuesFromTableRentArea.size(); i++) 
            {
                rentAreaStringBuilder.append(valuesFromTableRentArea.get(i));
                if (i < valuesFromTableRentArea.size() - 1) 
                    rentAreaStringBuilder.append(", ");   
            }
            building.setRentArea(rentAreaStringBuilder.toString());
            //////////////
            
            
            building.setBrokerFee(item.getBrokeragefee());
            building.setServiceFee(item.getServicefee());
            building.setRentprice(item.getRentprice());

            result.add(building);
        }
        return result;
    }
}
