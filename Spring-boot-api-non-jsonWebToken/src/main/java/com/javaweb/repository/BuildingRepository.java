package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.BuildingEntity;


//this interface extends JpaRepository, indicating that it will handle CRUD operations for entities 
//of type BuildingEntity, which has a primary key of type Integer


public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer>
{
	List<BuildingEntity> findByNameContaining(String name);//Containing: gan dung
}
