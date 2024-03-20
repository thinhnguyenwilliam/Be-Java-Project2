package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class BuildingRepositoyImpl implements BuildingRepository {

	/*
	 * @Override public List<BuildingEntity> findAll(String name, Integer
	 * numberOfBasement) {
	 * 
	 * String sql="SELECT * FROM building\n"; //String where=" WHERE 1=1";
	 * StringBuilder whereClause = new StringBuilder(); if(name!=null &&
	 * !name.equals("")) whereClause.append("WHERE name LIKE '%" +name+ "%'");
	 * if(numberOfBasement!=null) whereClause.append(" AND numberofbasement = "
	 * +numberOfBasement); sql+=whereClause.toString();
	 * 
	 * List<BuildingEntity> result=new ArrayList<>(); try(Connection
	 * conn=ConnectionUtil.getConnection(); Statement stm=conn.createStatement();
	 * ResultSet rs=stm.executeQuery(sql); ){ while(rs.next()) //di tung row trong
	 * table { BuildingEntity building=new BuildingEntity();
	 * building.setName(rs.getString("name"));
	 * building.setDistrictId(rs.getInt("districtid"));
	 * building.setStreet(rs.getString("street"));
	 * building.setWard(rs.getString("ward")); result.add(building); }
	 * System.out.print("Connection database ok con ga quay"); }catch (SQLException
	 * e) { e.printStackTrace();
	 * System.out.print("Connection database fail no no no bae come on"); }
	 * 
	 * 
	 * return result; }
	 * 
	 * 
	 * 
	 * @Override public void delete(Integer[] ids) {
	 * 
	 * 
	 * }
	 * 
	 */

	/* em bat dau lam o day a Manh oi */
	@Override
	public List<BuildingEntity> findByName(String name) {
		String sql = "SELECT * FROM building\n";
		sql += "JOIN rentarea ON building.id=rentarea.buildingid\n";
		StringBuilder whereClause = new StringBuilder();
		if (name != null && !name.equals(""))
			whereClause.append("WHERE name LIKE '%" + name + "%';");
		else
			return Collections.emptyList();
		sql += whereClause.toString();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);) {
			BuildingEntity building = new BuildingEntity();
			List<Integer> valuesRentArea = new ArrayList<>();
			while (rs.next()) // di tung row trong table
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
				building.setRentprice(rs.getInt("rentprice"));

				valuesRentArea.add(rs.getInt("value"));
			}
			building.setValue(valuesRentArea);
			result.add(building);
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByFloorArea(Integer floorArea) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.floorarea = %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (floorArea != null)
			finalSql = String.format(sql, floorArea);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByDistrict(Integer district) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.districtid = %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (district != null)
			finalSql = String.format(sql, district);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByWard(Integer ward) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE Right(building.ward,1) = %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (ward != null)
			finalSql = String.format(sql, ward);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByStreet(String street) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.street = ?\n"
				+ "GROUP BY building.id;";
		if (street != null && !street.equals("")) {
			List<BuildingEntity> result = new ArrayList<>();
			try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

				stm.setString(1, street); // Set street name as parameter

				try (ResultSet rs = stm.executeQuery()) {
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
					System.out.print("Connection database ok con ga quay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection database fail no no no bae come on");
			}
			return result;
		} else
			return Collections.emptyList();

	}

	@Override
	public List<BuildingEntity> findNumberOfBasement(Integer numberOfBasement) 
	{
		
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.numberofbasement = %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (numberOfBasement != null)
			finalSql = String.format(sql, numberOfBasement);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByDirection(String direction) 
	{
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.direction = ?\n"
				+ "GROUP BY building.id;";
		if (direction != null && !direction.equals("")) {
			List<BuildingEntity> result = new ArrayList<>();
			try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

				stm.setString(1, direction); // Set street name as parameter

				try (ResultSet rs = stm.executeQuery()) {
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
					System.out.print("Connection database ok con ga quay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection database fail no no no bae come on");
			}
			return result;
		} else
			return Collections.emptyList();
	}

	@Override
	public List<BuildingEntity> findByLevel(String level) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.level = ?\n"
				+ "GROUP BY building.id;";
		if (level != null && !level.equals("")) {
			List<BuildingEntity> result = new ArrayList<>();
			try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

				stm.setString(1, level); // Set street name as parameter

				try (ResultSet rs = stm.executeQuery()) {
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
					System.out.print("Connection database ok con ga quay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection database fail no no no bae come on");
			}
			return result;
		} else
			return Collections.emptyList();
	}

	@Override
	public List<BuildingEntity> findByAreaFrom(Integer areaFrom) 
	{
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE rentarea.value >= %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (areaFrom != null)
			finalSql = String.format(sql, areaFrom);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByAreaTo(Integer areaTo) 
	{
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE rentarea.value <= %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (areaTo != null)
			finalSql = String.format(sql, areaTo);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByRentPriceFrom(Integer rentPriceFrom) 
	{
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.rentprice >= %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (rentPriceFrom != null)
			finalSql = String.format(sql, rentPriceFrom);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByRentPriceTo(Integer rentPriceTo) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.rentprice <= %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (rentPriceTo != null)
			finalSql = String.format(sql, rentPriceTo);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByManagerName(String managerName) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.managername = ?\n"
				+ "GROUP BY building.id;";
		if (managerName != null && !managerName.equals("")) {
			List<BuildingEntity> result = new ArrayList<>();
			try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

				stm.setString(1, managerName); // Set street name as parameter

				try (ResultSet rs = stm.executeQuery()) {
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
					System.out.print("Connection database ok con ga quay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection database fail no no no bae come on");
			}
			return result;
		} else
			return Collections.emptyList();
	}

	@Override
	public List<BuildingEntity> findByManagerPhone(String managerPhone) {
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" + "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" + "WHERE building.managerphonenumber = ?\n"
				+ "GROUP BY building.id;";
		if (managerPhone != null && !managerPhone.equals("")) {
			List<BuildingEntity> result = new ArrayList<>();
			try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

				stm.setString(1, managerPhone); // Set street name as parameter

				try (ResultSet rs = stm.executeQuery()) {
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
					System.out.print("Connection database ok con ga quay");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection database fail no no no bae come on");
			}
			return result;
		} else
			return Collections.emptyList();
	}

	@Override
	public List<BuildingEntity> findByStaffId(Integer staffId) 
	{
		String sql = "SELECT building.*, GROUP_CONCAT(rentarea.value) AS combined_values\n" 
				+ "FROM building\n"
				+ "JOIN rentarea ON building.id = rentarea.buildingid\n" 
				+ "JOIN assignmentbuilding ON building.id = assignmentbuilding.buildingid\n" 
				+ "WHERE assignmentbuilding.staffid = %d\n"
				+ "GROUP BY building.id;";

		String finalSql = null;
		if (staffId != null)
			finalSql = String.format(sql, staffId);
		else
			return Collections.emptyList();

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(finalSql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity(); // Create a new BuildingEntity object for each row
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
					String[] arr = combinedValuesStr.split("[\\s,]+"); // Split the combined_values string
					List<Integer> combinedValuesList = new ArrayList<>();
					for (String value : arr)
						combinedValuesList.add(Integer.parseInt(value.trim())); // Convert each value to integer and add
																				// to the list

					building.setValue(combinedValuesList); // Set the combined values list
				}

				result.add(building);
			}
			System.out.print("Connection database ok con ga quay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connection database fail no no no bae come on");
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByTypeCode(List<String> typeCode) {
	    List<BuildingEntity> result = new ArrayList<>();
	    if (typeCode == null || typeCode.isEmpty()) {
	        return Collections.emptyList(); // Return empty list if typeCode is null or empty
	    }

	    StringBuilder sqlBuilder = new StringBuilder();
	    sqlBuilder.append("SELECT building.*, GROUP_CONCAT(DISTINCT rentarea.value) AS combined_values \n")
	              .append("FROM building \n")
	              .append("JOIN rentarea ON building.id = rentarea.buildingid \n")
	              .append("JOIN buildingrenttype ON building.id = buildingrenttype.buildingid \n")
	              .append("JOIN renttype ON renttype.id = buildingrenttype.renttypeid \n")
	              .append("WHERE renttype.code IN (");

	    // Add placeholders for type codes
	    for (int i = 0; i < typeCode.size(); i++) 
	    {
	        sqlBuilder.append("?");
	        if (i < typeCode.size() - 1)
	            sqlBuilder.append(", ");
	        
	    }
	    sqlBuilder.append(")\n")
	              .append("GROUP BY building.id");

	    String sql = sqlBuilder.toString();

	    try (Connection conn = ConnectionUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        // Set type codes as parameters
	        for (int i = 0; i < typeCode.size(); i++)
	            pstmt.setString(i + 1, typeCode.get(i));
	        

	        try (ResultSet rs = pstmt.executeQuery()) {
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
	            System.out.println("Connection to database successful.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Connection to database failed.");
	    }
	    return result;
	}

}


