package com.javaweb.controlleradvise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequireException;

@ControllerAdvice
public class ControllerAdvisor 
{
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
    							  ArithmeticException ex, WebRequest request) 
	{
		ErrorResponseDTO erd=new ErrorResponseDTO();
		erd.setError(ex.getMessage());
		List<String> listError=new ArrayList<>();
		listError.add("so nguyen khong chia duoc cho 0");
		erd.setDetail(listError);

        return new ResponseEntity<>(erd, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	
	@ExceptionHandler(FieldRequireException.class)
    public ResponseEntity<Object> handleFieldRequireException(
    							  FieldRequireException e) 
	{
		ErrorResponseDTO erd=new ErrorResponseDTO();
		erd.setError(e.getMessage());
		List<String> listError=new ArrayList<>();
		listError.add("ten hoac quan chua duoc gui ve");
		erd.setDetail(listError);

        return new ResponseEntity<>(erd, HttpStatus.BAD_REQUEST);
    }
}
