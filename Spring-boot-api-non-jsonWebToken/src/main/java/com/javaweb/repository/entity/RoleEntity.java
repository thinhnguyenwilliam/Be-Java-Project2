package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="role")
@Getter
@Setter
public class RoleEntity 
{
	@Id           // Indicates the primary key field of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;
	
	
	@Column(name="name", nullable=false)
	private String name;
	
	
	@Column(name="code",unique=true, nullable=false)
    private String code;
	
	
	@ManyToMany(mappedBy = "roles")
    private List<UserEntity> users=new ArrayList<>();
	
	
//	@OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//	private List<UserRoleEntity> userRoleEntities = new ArrayList<>();
}
