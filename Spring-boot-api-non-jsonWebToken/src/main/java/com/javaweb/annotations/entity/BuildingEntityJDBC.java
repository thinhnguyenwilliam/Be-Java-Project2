package com.javaweb.annotations.entity;



import com.javaweb.annotations.customannotation.ColumnJDBC;
import com.javaweb.annotations.customannotation.EntityJDBC;
import com.javaweb.annotations.customannotation.TableJDBC;


import lombok.Getter;
import lombok.Setter;

@EntityJDBC
@TableJDBC(name = "buidling")
@Getter
@Setter
public class BuildingEntityJDBC 
{
//	@ColumnJDBC(name = "id", type = "INT", nullable = false)
//    private int id;

    @ColumnJDBC(name = "name", type = "VARCHAR", length = 50)
    private String name;
    
    @ColumnJDBC(name = "ward", type = "VARCHAR", length = 50)
    private String ward;
    
    @ColumnJDBC(name = "street", type = "VARCHAR", length = 50)
    private String street;
    
	@ColumnJDBC(name = "rentprice", type = "INT")
	private int rentPrice;

//    @ColumnJDBC(name = "email", type = "VARCHAR", length = 100, nullable = false)
//    private String email;
}
