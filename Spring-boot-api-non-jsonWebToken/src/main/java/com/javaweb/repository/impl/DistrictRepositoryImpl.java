package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity findById(Integer districtId) {
		String sql = "SELECT * \n" + "FROM district \n" + "WHERE district.id = " + districtId;

		DistrictEntity districtEntity = new DistrictEntity();

		try (Connection conn = ConnectionUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next())
				districtEntity.setName(rs.getString("name"));

			System.out.println("Connection to database successful table district.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database table district.");
		}

		return districtEntity;
	}

}
