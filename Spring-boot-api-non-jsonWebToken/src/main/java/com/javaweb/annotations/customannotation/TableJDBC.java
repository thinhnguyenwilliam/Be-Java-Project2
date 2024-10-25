package com.javaweb.annotations.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // Retained at runtime for reflection
@Target(ElementType.TYPE)            // Can only be applied to classes or interfaces
public @interface TableJDBC {
    String name();  // Table name
}
