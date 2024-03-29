package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoyImpl implements BuildingRepository 
{

	static void queryJoin(Map<String, Object> params, List<String> typeCode, StringBuilder sql)
	{
		String areaFrom = (String) params.get("areaFrom");
		String areaTo = (String) params.get("areaTo");
		if(StringUtil.checkString(areaFrom) || StringUtil.checkString(areaTo))
			sql.append("JOIN rentarea r ON r.buildingid = b.id\n");
		
		String staffId = (String) params.get("staffId");
		if(StringUtil.checkString(staffId))
			sql.append("JOIN assignmentbuilding asm ON b.id = asm.buildingid\n");
		
		if (typeCode != null && !typeCode.isEmpty())
			sql.append("JOIN buildingrenttype brt ON b.id = brt.buildingid \n"
						+ "JOIN renttype rt ON rt.id = brt.renttypeid \n");
		
	}
	
	
	//Normal: LIKE, =
	//Special:<=, >=, 'In'(OR multiple times) in SQL, need to join
	static void queryWhereNormal(Map<String, Object> params, StringBuilder whereClause)
	{
		for(Map.Entry<String, Object> entry:params.entrySet())
		{
			//cau if duoi day la cho hieu thoi cho minh thay no chua dung cho startwith vi cung co nhieu cai khac cung start with
			if(!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode")
					&& !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice"))
			{
				String data=entry.getValue().toString();
				if(StringUtil.checkString(data)) 
				{
					if(NumberUtil.checkNumber(data))//if it is Number, "12344545" it can parseInt but "123sfd" i can't
						whereClause.append("AND b." +entry.getKey().toLowerCase()+ " = "+data +"\n");
					else//if it is String
						whereClause.append("AND b." +entry.getKey().toLowerCase()+ " LIKE '%"+data +"%'\n");
					
				}
			}
		}
	}
	
	static void queryWhereSpecial(Map<String, Object> params, List<String> typeCode, StringBuilder whereClause)
	{
		String rentAreaFrom = (String) params.get("areaFrom");
		String rentAreaTo = (String) params.get("areaTo");
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		String rentPriceTo = (String) params.get("rentPriceTo");
		String staffId = (String) params.get("staffId");
		if(StringUtil.checkString(staffId)) 
			whereClause.append("AND asm.staffid = "+staffId +'\n');
		
		if(StringUtil.checkString(rentAreaFrom)) 
			whereClause.append("AND r.value >= "+rentAreaFrom +'\n');
		if(StringUtil.checkString(rentAreaTo)) 
			whereClause.append("AND r.value <= "+rentAreaTo +'\n');
		
		if(StringUtil.checkString(rentPriceFrom)) 
			whereClause.append("AND b.rentprice >= "+rentAreaFrom +'\n');
		if(StringUtil.checkString(rentPriceTo)) 
			whereClause.append("AND b.rentprice <= "+rentAreaTo +'\n');
		
		//Java 7
		if(typeCode!=null && !typeCode.isEmpty())
		{
			whereClause.append(" AND renttype.code IN (");
			for (int i = 0; i < typeCode.size(); i++) 
			{
				whereClause.append("'" + typeCode.get(i) + "'");
				if (i < typeCode.size() - 1) {
					whereClause.append(", ");
				}
			}
			whereClause.append(")\n");
		}
	}
	
	
	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> typeCode) 
	{
		StringBuilder sql =new StringBuilder("SELECT  b.id, b.name, b.districtid, "
				+ "b.street, b.ward, b.numberofbasement, b.managername, "
				+ "b.managerphonenumber, b.floorarea, b.rentprice, b.brokeragefee, b.servicefee \n" 
				+ "FROM building b \n");
		
		queryJoin(params, typeCode, sql);
		
		StringBuilder whereClause = new StringBuilder("WHERE 1=1\n");
		queryWhereNormal(params, whereClause);
		queryWhereSpecial(params, typeCode, whereClause);
		
		sql.append(whereClause);
		sql.append("GROUP BY b.id");
		
		///////////////
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql.toString())) {	

			while (rs.next()) {
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
				building.setId(rs.getInt("id"));
				result.add(building);
			}
			System.out.println("Connection to database successful table building.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database table building.");
		}

		return result;
	}
}
