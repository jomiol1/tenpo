package com.tenpo.api.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenpo.api.dto.ErrorDTO;
import com.tenpo.api.handler.exception.GeneralException;


@RestControllerAdvice
public class ExceptionHandler {
	   
	  @ResponseStatus(
	           value = HttpStatus.INTERNAL_SERVER_ERROR)
	  @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class, GeneralException.class })
	   public ResponseEntity<ErrorDTO>
	   genericExceptionHandler(HttpServletRequest request, GeneralException e) {
		  ErrorDTO errorDTO = new ErrorDTO();
		  errorDTO.setCodigo(e.getCode());
		  errorDTO.setMensaje(e.getMessage() == null ? "Error General"
                  : e.getMessage());

	       return ResponseEntity.status(HttpStatus.ACCEPTED).body(errorDTO);
	   }
	  
	  
	  @ResponseStatus(
      value = HttpStatus.BAD_REQUEST)
		@org.springframework.web.bind.annotation.ExceptionHandler(value = {BindException.class })
		public ResponseEntity<List<String>>
		genericExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
		 List<String> errors = e.getBindingResult().getFieldErrors()
		           .stream().map( fieldError -> String.format("[%s]: %s", fieldError.getField(),
		        		   fieldError.getDefaultMessage())).collect(Collectors.toList());
		 
 

		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
	  

}
