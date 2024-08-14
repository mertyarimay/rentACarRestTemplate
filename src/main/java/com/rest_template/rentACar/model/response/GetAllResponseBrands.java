package com.rest_template.rentACar.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResponseBrands {
    private int id;
    private String name;

}
