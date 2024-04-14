package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom 
{
    List<BuildingEntity> findAll(BuidingSearchBuilder builder);
}


