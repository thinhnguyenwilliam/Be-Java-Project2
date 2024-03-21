package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class BuildingRepositoyImpl implements BuildingRepository {

    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> typeCode) {
        List<BuildingEntity> result = new ArrayList<>();

        boolean nameFlag = false;
        boolean floorAreaFlag = false;
        String sql = null;

        if (params.containsKey("ten")) {
            String name = (String) params.get("ten");
            if (name != null && !name.equals("")) {
                sql = "SELECT building.*, GROUP_CONCAT(DISTINCT rentarea.value) AS combined_values \n"
                        + "FROM building \n" + "JOIN rentarea ON building.id = rentarea.buildingid \n"
                        + "WHERE building.name LIKE ? \n" + "GROUP BY building.id \n";
                nameFlag = true;
            }
        }

        if (params.containsKey("dienTichSan")) {
        	Integer floorArea = Integer.valueOf((String) params.get("dienTichSan"));
            if (floorArea != null) {
                sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values \n" + "FROM building \n"
                        + "JOIN rentarea ON building.id = rentarea.buildingid \n" + "WHERE building.floorarea = ? \n"
                        + "GROUP BY building.id \n";
                floorAreaFlag = true;
            }
        }

        
        
        
        
        
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int paramIndex = 1; // Parameter index for PreparedStatement

            if (nameFlag) 
                pstmt.setString(paramIndex, "%" + params.get("ten") + "%");
            
            if (floorAreaFlag) 
                pstmt.setInt(paramIndex, Integer.valueOf((String) params.get("dienTichSan")));
            

            System.out.println("Connection to database successful.");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BuildingEntity building = new BuildingEntity();
                    // Populate building from result set
                    building.setName(rs.getString("name"));
                    building.setDistrictId(rs.getInt("districtid"));
                    building.setStreet(rs.getString("street"));
                    building.setWard(rs.getString("ward"));
                    building.setNumberOfBasement(rs.getInt("numberofbasement"));
                    building.setManagerName(rs.getString("managername"));
                    building.setManagerNamePhoneNumber(rs.getString("managerphonenumber"));
                    building.setFloorArea(rs.getInt("floorarea"));
                    building.setBrokeragefee(rs.getInt("brokeragefee"));
                    building.setServicefee(rs.getInt("servicefee"));
                    building.setRentprice(rs.getInt("rentprice"));

                    String combinedValuesStr = rs.getString("combined_values");
                    if (combinedValuesStr != null) {
                        String[] arr = combinedValuesStr.split("[\\s,]+");
                        List<Integer> combinedValuesList = new ArrayList<>();
                        for (String value : arr)
                            combinedValuesList.add(Integer.parseInt(value.trim()));

                        building.setValue(combinedValuesList);
                    }

                    result.add(building);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection database fail no no no bae come on");
        }

        return result;
    }
}
