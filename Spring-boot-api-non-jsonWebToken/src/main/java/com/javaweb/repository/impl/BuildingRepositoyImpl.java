package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;



@Repository
public class BuildingRepositoyImpl implements BuildingRepository 
{

    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> typeCode) 
    {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * \n"
        											+ "FROM building \n"
        											+ "WHERE 1=1");

        if (params.containsKey("ten")) {
            String name = (String) params.get("ten");
            if (name != null && !name.equals("")) {
                sqlBuilder.append("\nAND building.name LIKE '%" + name + "%'");
            }
        }

        String sql = sqlBuilder.toString();

        try (Connection conn = ConnectionUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {

            while (rs.next()) 
            {
                BuildingEntity building = new BuildingEntity();
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

                result.add(building);
            }
            System.out.println("Connection to database successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        }

        return result;
    }
}
