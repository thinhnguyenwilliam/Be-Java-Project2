package com.javaweb.annotations.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // Available at runtime via reflection
@Target(ElementType.FIELD)           // Applicable to fields (class attributes)
public @interface ColumnJDBC {
    String name();            // Column name in the database
    String type() default "";  // Data type (optional), e.g., "VARCHAR", "INT"
    int length() default 255;  // Optional length (default is 255 for strings)
    boolean nullable() default true;  // Whether the column can be NULL
}
