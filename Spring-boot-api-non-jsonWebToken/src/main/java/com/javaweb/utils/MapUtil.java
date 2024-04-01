package com.javaweb.utils;

import java.util.Map;


//generic class
public class MapUtil 
{   
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) 
    {
        Object obj = params.getOrDefault(key, null);

        if (obj != null) 
        {
            // Check if the retrieved object is an instance of the specified class
            if (tClass.equals(Integer.class)) 
            {
                // Convert the object to Integer if the specified class is java.lang.Integer
                obj = obj.toString().isEmpty() ? null : Integer.valueOf(obj.toString());
            } 
            else if (tClass.equals(Long.class)) 
            {
                // Convert the object to Long if the specified class is java.lang.Long
                obj = obj.toString().isEmpty() ? null : Long.valueOf(obj.toString());
            } 
            else if (tClass.equals(String.class)) 
            {
                // Convert the object to String if the specified class is java.lang.String
                obj = obj.toString();
            }
        }

        // Cast the object to the specified type
        return tClass.cast(obj);
    }
}
