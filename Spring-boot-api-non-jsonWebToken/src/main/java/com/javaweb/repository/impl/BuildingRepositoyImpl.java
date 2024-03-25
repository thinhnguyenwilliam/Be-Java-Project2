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



@Repository
public class BuildingRepositoyImpl implements BuildingRepository 
{

    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> typeCode) 
    {
    	String sql="SELECT DISTINCT building.* \n"
    				+"FROM building \n";
    	String joinClause="";
    	String whereClause="WHERE 1=1";
    	

        ///////////////////
        boolean flag=false;
        
        if (params.containsKey("ten")) 
        {
            String name = (String) params.get("ten");
            if (name != null && !name.equals("")) 
            {
                whereClause+=" AND building.name LIKE '%" + name + "%'";
                flag=true;
            }
           
        }
        
        if (params.containsKey("dienTichSan")) 
        {
            String floorAreaStr = (String) params.get("dienTichSan");
            if (!floorAreaStr.isEmpty()) 
            {
                Integer floorArea = Integer.valueOf(floorAreaStr);
                whereClause+=" AND building.floorarea = " + floorArea;
                flag = true;
            }
        }
        
        
        if (params.containsKey("quan")) 
        {
            String districtStr = (String) params.get("quan");
            if (!districtStr.isEmpty()) 
            {
                Integer district = Integer.valueOf(districtStr);
                whereClause+=" AND building.districtid = " + district;
                flag = true;
            }
        }
        
        
        if (params.containsKey("phuong")) 
        {
            String wardStr = (String) params.get("phuong");
            if (wardStr != null && !wardStr.equals("")) 
            {
            	whereClause+=" AND building.ward LIKE '%" + wardStr + "%'";
                flag = true;
            }
        }
        
        
        if (params.containsKey("duong")) 
        {
            String streetStr = (String) params.get("duong");
            if (streetStr != null && !streetStr.equals("")) 
            {
            	whereClause+=" AND building.street LIKE '%" + streetStr + "%'";
                flag = true;
            }
        }

        
        if (params.containsKey("soTangHam")) 
        {
            String numberOfBasementStr = (String) params.get("soTangHam");
            if (!numberOfBasementStr.isEmpty()) 
            {
                Integer numberOfBasement = Integer.valueOf(numberOfBasementStr);
                whereClause+=" AND building.numberofbasement = " + numberOfBasement;
                flag = true;
            }
        }
        
        
        if (params.containsKey("huong")) 
        {
            String directionStr = (String) params.get("huong");
            if (directionStr != null && !directionStr.equals("")) 
            {
            	whereClause+=" AND building.direction LIKE '%" + directionStr + "%'";
                flag = true;
            }
        }
        
        
        
        if (params.containsKey("hang")) 
        {
            String levelStr = (String) params.get("hang");
            if (levelStr != null && !levelStr.equals("")) 
            {
            	whereClause+=" AND building.level LIKE '%" + levelStr + "%'";
                flag = true;
            }
        }
        
        /////////////
        if (params.containsKey("dienTichTu"))
        {
            String areaFromStr = (String) params.get("dienTichTu");
            if (!areaFromStr.isEmpty()) 
            {
                Integer areaFrom = Integer.valueOf(areaFromStr);
                joinClause += "JOIN rentarea ON rentarea.buildingid = building.id\n";
                whereClause += " AND rentarea.value >= " + areaFrom;
                flag = true;
            }
        }
        
        
        if (params.containsKey("dienTichDen"))
        {
            String areaToStr = (String) params.get("dienTichDen");
            if (!areaToStr.isEmpty()) 
            {
                Integer areaTo = Integer.valueOf(areaToStr);
                joinClause += "JOIN rentarea ON rentarea.buildingid = building.id\n";
                whereClause += " AND rentarea.value <= " + areaTo;
                flag = true;
            }
        }
        ///////////
        
        
        
        if (params.containsKey("tenQuanLy")) 
        {
            String managerNameStr = (String) params.get("tenQuanLy");
            if (managerNameStr != null && !managerNameStr.equals("")) 
            {
            	whereClause+=" AND building.managername LIKE '%" + managerNameStr + "%'";
                flag = true;
            }
        }
        
        
        
        if (params.containsKey("sdtQuanLy")) 
        {
            String managerPhoneNumberStr = (String) params.get("sdtQuanLy");
            if (managerPhoneNumberStr != null && !managerPhoneNumberStr.equals("")) 
            {
            	whereClause+=" AND building.managerphonenumber LIKE '%" + managerPhoneNumberStr + "%'";
                flag = true;
            }
        }
        
        
        if (params.containsKey("maNhanVien"))
        {
            String staffIdStr = (String) params.get("maNhanVien");
            if (!staffIdStr.isEmpty()) 
            {
                Integer staffId = Integer.valueOf(staffIdStr);
                joinClause += "JOIN assignmentbuilding ON building.id = assignmentbuilding.buildingid\n";
                whereClause += " AND  assignmentbuilding.staffid = " + staffId;
                flag = true;
            }
        }
        
        
        if (params.containsKey("maLoai")) 
        {
            if (typeCode != null && !typeCode.isEmpty()) 
            {
                flag = true;
                joinClause += "JOIN buildingrenttype ON building.id = buildingrenttype.buildingid \n" +
                              "JOIN renttype ON renttype.id = buildingrenttype.renttypeid \n";
                whereClause += " AND renttype.code IN (";
                for (int i = 0; i < typeCode.size(); i++) 
                {
                    whereClause += "'" + typeCode.get(i) + "'";
                    if (i < typeCode.size() - 1) {
                        whereClause += ", ";
                    }
                }
                whereClause += ")\n";
            }
        }

        
        
        
        if(!flag)
        	return Collections.emptyList();;
        
        sql += joinClause + whereClause;
        ///////////////
        List<BuildingEntity> result = new ArrayList<>();
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
