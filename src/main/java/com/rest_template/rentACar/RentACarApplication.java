package com.rest_template.rentACar;

import com.rest_template.rentACar.exception.BusinessException;
import com.rest_template.rentACar.exception.ProblemDetails;
import com.rest_template.rentACar.exception.ValidationProblemsDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestControllerAdvice
@SpringBootApplication
public class RentACarApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBussınesException(BusinessException businessException){
		ProblemDetails problemDetails=new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}
	@ExceptionHandler
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)

       public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
		ValidationProblemsDetails validationProblemsDetails=new ValidationProblemsDetails();
		validationProblemsDetails.setMessage("Validation Exception");
		validationProblemsDetails.setValidationErors(new HashMap<String,String>());


		for(FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors()){
			validationProblemsDetails.getValidationErors().put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		return validationProblemsDetails;


	}

}
