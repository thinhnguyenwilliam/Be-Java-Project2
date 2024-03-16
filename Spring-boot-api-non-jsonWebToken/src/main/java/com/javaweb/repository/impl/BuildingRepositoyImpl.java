package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;



@Repository
public class BuildingRepositoyImpl implements BuildingRepository 
{

	@Override
	public List<BuildingEntity> findAll(String name, Integer numberOfBasement) 
	{
		
		String sql="SELECT * FROM building\n";
		//String where=" WHERE 1=1";
		StringBuilder whereClause = new StringBuilder();
		if(name!=null && !name.equals(""))
			whereClause.append("WHERE name LIKE '%" +name+ "%'");
		if(numberOfBasement!=null)
			whereClause.append(" AND numberofbasement = " +numberOfBasement);
		sql+=whereClause.toString();
		
		List<BuildingEntity> result=new ArrayList<>();
		try(Connection conn=ConnectionUtil .getConnection();
			Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery(sql);
			){
			while(rs.next()) 
			{
				BuildingEntity building=new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setDistrictId(rs.getInt("districtid"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}   
		

	    return result;
	}



	@Override
	public void delete(Integer[] ids) 
	{
		
		
	}
	
}
