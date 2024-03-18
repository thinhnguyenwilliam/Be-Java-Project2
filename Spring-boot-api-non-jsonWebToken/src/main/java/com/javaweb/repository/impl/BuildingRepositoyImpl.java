package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;



@Repository
public class BuildingRepositoyImpl implements BuildingRepository 
{

	/*
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
		try(Connection conn=ConnectionUtil.getConnection();
			Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery(sql);
			){
			while(rs.next()) //di tung row trong table
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

	*/
	
	
	/*em bat dau lam o day a Manh oi*/
	@Override
	public List<BuildingEntity> find(String name) 
	{
		String sql="SELECT * FROM building\n";
		sql+="JOIN rentarea ON building.id=rentarea.buildingid\n";
		StringBuilder whereClause = new StringBuilder();
		if(name!=null && !name.equals(""))
			whereClause.append("WHERE name LIKE '%" +name+ "%';");
		else
			return Collections.emptyList();
		sql+=whereClause.toString();
		
		
		
		
		List<BuildingEntity> result=new ArrayList<>();
		try(Connection conn=ConnectionUtil.getConnection();
			Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery(sql);
			){
			BuildingEntity building=new BuildingEntity();
			List<Integer> valuesRentArea=new ArrayList<>();
			while(rs.next()) //di tung row trong table
			{
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
						
				valuesRentArea.add(rs.getInt("value"));
			}
			building.setValue(valuesRentArea);
			result.add(building);
			System.out.print("Connection database ok con ga quay");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}   
	    return result;	
	}
	
}
