package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "building")
@Getter
@Setter
public class BuildingEntity {
	@Id // Indicates the primary key field of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "ward")
	private String ward;

	@Column(name = "street")
	private String street;

	@Column(name = "numberofbasement")
	private Integer numberOfBasement;

	@Column(name = "managername")
	private String managerName;

	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;

	@Column(name = "floorarea")
	private Integer floorArea;

	@Column(name = "brokeragefee")
	private Double brokerageFee;

	@Column(name = "servicefee")
	private String serviceFee;

	@Column(name = "rentprice")
	private Integer rentPrice;

	@Column(name = "direction")
	private String direction;

	@Column(name = "level")
	private String level;

	@OneToMany(mappedBy = "building") // default fetch = FetchType.LAZY
	private List<RentAreaEntity> rentAreas = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "districtid") // the foreign key column, default FetchType.EAGER
	private DistrictEntity district;

}
