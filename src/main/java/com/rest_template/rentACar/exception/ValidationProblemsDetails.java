package com.rest_template.rentACar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemsDetails extends ProblemDetails{
    Map<String,String>validationErors;
}
