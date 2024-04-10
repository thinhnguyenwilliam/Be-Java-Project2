package com.javaweb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuidingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository



//2 class cung implement 1 class thi them primary de chay class do
// Added @Primary annotation to indicate that this implementation should be
// preferred when multiple beans of the same type are present.
@Primary
public class BuildingRepositoyImpl implements BuildingRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findBuilding(BuidingSearchBuilder builder) 
	{

		// JPA Query Language
		// Constructing JPQL query
		StringBuilder jpql = new StringBuilder("SELECT b FROM BuildingEntity b WHERE 1=1 \n"
				+ "AND b.name LIKE '%ACM%'");

		// Create query
		Query query = entityManager.createQuery(jpql.toString(), BuildingEntity.class);

		// Execute query and return result
		return query.getResultList();
	}

}
