package com.javaweb.repository.entity;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="rentarea")
@Getter
@Setter
public class RentAreaEntity 
{
	@Id           // Indicates the primary key field of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;
	

	@Column(name="value")
	private Integer value;
	
	
	
	@ManyToOne
    @JoinColumn(name = "buildingid") // Assuming "buildingId" is the foreign key column
    private BuildingEntity building;
	
	
}
