package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom
{
	@PersistenceContext
	private EntityManager entityManager;

	static void queryJoin(BuidingSearchBuilder builder, StringBuilder sql) 
	{
		Integer staffId = builder.getStaffId();
		if (staffId != null)
			sql.append("JOIN assignmentbuilding asm ON b.id = asm.buildingid\n");

		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty())
			sql.append("JOIN buildingrenttype brt ON b.id = brt.buildingid \n"
					+ "JOIN renttype rt ON rt.id = brt.renttypeid \n");
	}

	// Normal: LIKE, =
	// Special:<=, >=, 'In'(OR multiple times) in SQL, need to join
	//Reflection
	static void queryWhereNormal(BuidingSearchBuilder builder, StringBuilder whereClause) 
	{
//		for(Map.Entry<String, Object> entry:params.entrySet())
//		{
//			//cau if duoi day la cho hieu thoi cho minh thay no chua dung cho startwith vi cung co nhieu cai khac cung start with
//			if(!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode")
//					&& !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice"))
//			{
//				String data=entry.getValue().toString();
//				if(StringUtil.checkString(data)) 
//				{
//					if(NumberUtil.checkNumber(data))//if it is Number, "12344545" it can parseInt but "123sfd" i can't
//						whereClause.append("AND b." +entry.getKey().toLowerCase()+ " = "+data +"\n");
//					else//if it is String
//						whereClause.append("AND b." +entry.getKey().toLowerCase()+ " LIKE '%"+data +"%'\n");
//					
//				}
//			}
//		}

		try {
			Field[] fields = BuidingSearchBuilder.class.getDeclaredFields();

			for (Field field : fields) 
			{
				field.setAccessible(true); // Allow access to private fields
				Object value = field.get(builder); // Get the value of the field for the given builder object

				// Skip certain fields
				String fieldName = field.getName();
				if (fieldName.equals("typeCode") || fieldName.equals("staffId") || fieldName.startsWith("area")
						|| fieldName.startsWith("rentPrice")) 
				{
					continue;
				}

				// Skip null or empty values
				if (value == null || value.toString().isEmpty()) 
				{
					continue;
				}

				// Append the condition to the WHERE clause
				whereClause.append("AND b.").append(fieldName.toLowerCase());

				// Append the appropriate condition based on the value type
				if (value instanceof Number) 
				{
					whereClause.append(" = ").append(value);
				} 
				else 
				{
					whereClause.append(" LIKE '%").append(value).append("%'");
				}    

				whereClause.append("\n");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	static void queryWhereSpecial(BuidingSearchBuilder builder, StringBuilder whereClause) 
	{
	    try {
	        // Using reflection to get the field values from the builder object
	        Field[] fields = BuidingSearchBuilder.class.getDeclaredFields();

	        // Retrieve field values from the builder object
	        Integer rentAreaFrom = builder.getAreaFrom();
	        Integer rentAreaTo = builder.getAreaTo();
	        Integer rentPriceFrom = builder.getRentPriceFrom();
	        Integer rentPriceTo = builder.getRentPriceTo();
	        Integer staffId = builder.getStaffId();
	        List<String> typeCode = builder.getTypeCode();

	        // Check if staffId is not null and append to the WHERE clause
	        if (staffId != null)
	            whereClause.append("AND asm.staffid = ").append(staffId).append('\n');

	        // Append conditions for rent area if applicable
	        if (rentAreaFrom != null || rentAreaTo != null) 
	        {
	            whereClause.append("AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");

	            if (rentAreaTo != null)
	                whereClause.append("AND r.value <= ").append(rentAreaTo).append('\n');

	            if (rentAreaFrom != null)//true then go if
	                whereClause.append("AND r.value >= ").append(rentAreaFrom).append(")\n");
	            else//false then go else
	                whereClause.append(")\n");
	        }

	        // Append conditions for rent price if applicable
	        if (rentPriceFrom != null)
	            whereClause.append("AND b.rentprice >= ").append(rentPriceFrom).append('\n');

	        if (rentPriceTo != null)
	            whereClause.append("AND b.rentprice <= ").append(rentPriceTo).append('\n');

	        // Append conditions for typeCode using Java 8 Stream API
	        if (typeCode != null && !typeCode.isEmpty()) {
	            whereClause.append("AND "); // Appending "AND " to the WHERE clause

	            // Using Stream API to process each type code and construct SQL conditions
	            String sqlJoin = typeCode.stream().map(item -> "rt.code LIKE '%" + item + "%'")
	                    .collect(Collectors.joining(" OR ")); // Joining the conditions with " OR "

	            whereClause.append(sqlJoin).append(" \n"); // Appending the constructed conditions to the WHERE clause
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public List<BuildingEntity> findAll(BuidingSearchBuilder builder) 
	{
		StringBuilder sql = new StringBuilder(
				"SELECT  b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.managername, "
						+ "b.managerphonenumber, b.floorarea, b.rentprice, b.brokeragefee, b.servicefee, "
						+ "b.direction, b.level \n"
						+ "FROM building b \n");

		queryJoin(builder, sql);

		StringBuilder whereClause = new StringBuilder("WHERE 1=1\n");
		queryWhereNormal(builder, whereClause);
		queryWhereSpecial(builder, whereClause);

		sql.append(whereClause);
		sql.append("GROUP BY b.id");

	
		Query query=entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
		return query.getResultList();
	}
}
