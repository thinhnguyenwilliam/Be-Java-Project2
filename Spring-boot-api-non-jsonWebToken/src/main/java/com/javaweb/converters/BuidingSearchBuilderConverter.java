package com.javaweb.converters;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuidingSearchBuilderConverter 
{
    public BuidingSearchBuilder toBuidingSearchBuilder(Map<String, Object> params, List<String> typeCode) 
    {
        BuidingSearchBuilder builder = new BuidingSearchBuilder.Builder()
						        		.setName(MapUtil.getObject(params, "name", String.class))
						                .setFloorArea(MapUtil.getObject(params, "floorArea", Integer.class))
						                .setWard(MapUtil.getObject(params, "ward", String.class))
						                .setStreet(MapUtil.getObject(params, "street", String.class))
						                .setDistrictId(MapUtil.getObject(params, "districtId", Integer.class))
						                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
						                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
						                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
						                .setDirection(MapUtil.getObject(params, "direction", String.class))
						                .setLevel(MapUtil.getObject(params, "level", String.class))
						                .setTypeCode(typeCode)
						                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
						                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Integer.class))
						                .setAreaFrom(MapUtil.getObject(params, "areaFrom", Integer.class))
						                .setAreaTo(MapUtil.getObject(params, "areaTo", Integer.class))
						                .setStaffId(MapUtil.getObject(params, "staffId", Integer.class))
        								.build();
       
        return builder;
    }
}
