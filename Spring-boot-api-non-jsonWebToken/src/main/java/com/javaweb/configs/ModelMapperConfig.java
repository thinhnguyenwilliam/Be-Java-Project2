package com.javaweb.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




//the modelMapper() method is a simple bean creation method that returns a new instance of ModelMapper
@Configuration
public class ModelMapperConfig 
{
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
