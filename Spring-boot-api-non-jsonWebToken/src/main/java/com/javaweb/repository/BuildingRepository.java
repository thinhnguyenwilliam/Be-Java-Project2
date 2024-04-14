package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;


//this interface extends JpaRepository, indicating that it will handle CRUD operations for entities 
//of type BuildingEntity, which has a primary key of type Integer


public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer>, BuildingRepositoryCustom
{
	List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);//Containing: gan dung, lIKE '%__%'
	void deleteByIdIn(List<Integer> ids); // Modified to accept List<Integer>
}
