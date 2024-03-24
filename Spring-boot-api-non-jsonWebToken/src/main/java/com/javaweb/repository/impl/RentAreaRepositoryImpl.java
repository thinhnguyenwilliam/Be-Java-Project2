package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository 
{

	@Override
	public List<Integer> findValues(Integer buildingId) 
	{
		StringBuilder sqlBuilder = new StringBuilder("SELECT * \n"
													+ "FROM rentarea \n"
													+ "WHERE rentarea.buildingid = " +buildingId);
		
		String sql = sqlBuilder.toString();
		//////////////////
	
		List<Integer> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
	             Statement stm = conn.createStatement();
	             ResultSet rs = stm.executeQuery(sql)) {

	            while (rs.next()) 
	                result.add(rs.getInt("value"));
	            
	            System.out.println("Connection to database successful.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Failed to connect to database.");
	        }
		
		
		return result;
	}

	
}
