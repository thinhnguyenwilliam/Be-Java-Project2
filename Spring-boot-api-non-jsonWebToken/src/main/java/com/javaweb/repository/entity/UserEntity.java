package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class UserEntity 
{
	@Id           // Indicates the primary key field of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;
	
	
	@Column(name="username", nullable=false,unique=true)
	private String userName;
	
	
	@Column(name="password", nullable=false)
    private String password;

    @Column(name="fullname")
    private String fullName;

    @Column(name="status", nullable=false)
    private Integer status;

    @Column(name="email")
    private String email;
    
    
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "userid", nullable=false),
        inverseJoinColumns = @JoinColumn(name = "roleid", nullable=false)
    )
    private List<RoleEntity> roles=new ArrayList<>();
    
//    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
//	private List<UserRoleEntity> userRoleEntities = new ArrayList<>();
}
