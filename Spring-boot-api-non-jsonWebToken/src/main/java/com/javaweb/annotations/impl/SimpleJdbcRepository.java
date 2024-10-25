package com.javaweb.annotations.impl;

import java.sql.*;
import java.util.*;

import com.javaweb.annotations.JdbcRepository;
import com.javaweb.annotations.customannotation.ColumnJDBC;
import com.javaweb.annotations.customannotation.TableJDBC;
import com.javaweb.utils.ConnectionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class SimpleJdbcRepository<T> implements JdbcRepository<T>
{
	
	private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public SimpleJdbcRepository() 
    {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType)
            this.entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        else
            throw new RuntimeException("Missing generic type parameter.");
        
    }
	
	
	@Override
	public void save(T entity) 
	{
		if (!entityClass.isAnnotationPresent(TableJDBC.class)) {
            throw new IllegalArgumentException("Entity must be annotated with @TableJDBC");
        }

        String tableName = entityClass.getAnnotation(TableJDBC.class).name();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Field field : entityClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ColumnJDBC.class)) {
                    field.setAccessible(true);
                    String columnName = field.getAnnotation(ColumnJDBC.class).name();
                    columns.append(columnName).append(",");
                    values.append("?").append(","); // Use placeholders for PreparedStatement
                }
            }

            // Remove trailing commas
            columns.setLength(columns.length() - 1);
            values.setLength(values.length() - 1);

            String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                int index = 1;
                for (Field field : entityClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(ColumnJDBC.class)) {
                        field.setAccessible(true);
                        statement.setObject(index++, field.get(entity));
                    }
                }
                statement.executeUpdate();
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public Optional<T> findById(Integer id) {
		if (!entityClass.isAnnotationPresent(TableJDBC.class)) {
            throw new IllegalArgumentException("Entity must be annotated with @TableJDBC");
        }

        String tableName = entityClass.getAnnotation(TableJDBC.class).name();
        T entity = null;

        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entity = entityClass.getDeclaredConstructor().newInstance();
                for (Field field : entityClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(ColumnJDBC.class)) {
                        field.setAccessible(true);
                        String columnName = field.getAnnotation(ColumnJDBC.class).name();
                        field.set(entity, resultSet.getObject(columnName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
	}

	@Override
	public List<T> findAll() 
	{
	    // Check if the class is annotated with @TableJDBC
	    if (!entityClass.isAnnotationPresent(TableJDBC.class)) 
	    {
	        throw new IllegalArgumentException("Entity must be annotated with @TableJDBC");
	    }

	    String tableName = entityClass.getAnnotation(TableJDBC.class).name();
	    List<T> entities = new ArrayList<>();

	    String sql = "SELECT * FROM " + tableName;

	    try (Connection connection = ConnectionUtil.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) 
	    {

	        while (resultSet.next()) 
	        {
	            T entity = (T) entityClass.getDeclaredConstructor().newInstance(); // Create a new instance of T
	            for (Field field : entityClass.getDeclaredFields()) 
	            {
	                if (field.isAnnotationPresent(ColumnJDBC.class)) 
	                {
	                    field.setAccessible(true); // Allow access to private fields
	                    String columnName = field.getAnnotation(ColumnJDBC.class).name();
	                    Object value = resultSet.getObject(columnName);
	                    field.set(entity, value); // Set the value from the result set
	                }
	            }
	            entities.add(entity); // Add the populated entity to the list
	        }
	    } catch (SQLException e) {
	        // Handle SQL exceptions specifically
	        System.err.println("SQL error while fetching entities: " + e.getMessage());
	    } catch (Exception e) {
	        // Catch any other exceptions (e.g., reflection related)
	        e.printStackTrace();
	    }
	    return entities; // Return the list of entities
	}


	@Override
	public void update(T entity) {
		if (!entityClass.isAnnotationPresent(TableJDBC.class)) {
            throw new IllegalArgumentException("Entity must be annotated with @TableJDBC");
        }

        String tableName = entityClass.getAnnotation(TableJDBC.class).name();
        StringBuilder setClause = new StringBuilder();
        Integer id = null; // Assume there's an ID field in your entity

        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Field field : entityClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ColumnJDBC.class)) {
                    field.setAccessible(true);
                    String columnName = field.getAnnotation(ColumnJDBC.class).name();
                    if (field.getName().equals("id")) { // Assuming the ID field is named 'id'
                        id = (Integer) field.get(entity);
                    } else {
                        setClause.append(columnName).append(" = ?,");
                    }
                }
            }

            // Remove trailing comma
            setClause.setLength(setClause.length() - 1);

            String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                int index = 1;
                for (Field field : entityClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(ColumnJDBC.class) && !field.getName().equals("id")) {
                        statement.setObject(index++, field.get(entity));
                    }
                }
                statement.setInt(index, id); // Set the ID in the last position
                statement.executeUpdate();
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteById(Integer id) {
		if (!entityClass.isAnnotationPresent(TableJDBC.class)) {
            throw new IllegalArgumentException("Entity must be annotated with @TableJDBC");
        }

        String tableName = entityClass.getAnnotation(TableJDBC.class).name();
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}
