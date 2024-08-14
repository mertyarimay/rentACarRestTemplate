package com.rest_template.rentACar.exception;

public class BusinessException extends RuntimeException{
   public BusinessException(String message){
       super(message);
   }
}
