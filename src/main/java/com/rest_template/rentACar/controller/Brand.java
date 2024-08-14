package com.rest_template.rentACar.controller;

import com.rest_template.rentACar.model.request.CreateBrandRequest;

import com.rest_template.rentACar.model.response.GetAllResponseBrands;
import com.rest_template.rentACar.model.response.GetByIdResponseBrand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/restClient")
@AllArgsConstructor

public class Brand {

    private final RestTemplate restTemplate;

    private static final String postUrl = "http://localhost:8080/api/brands/add";

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        restTemplate.postForEntity(postUrl, createBrandRequest, CreateBrandRequest.class); //gideceği url yazıp o url ne göndereceğimizi yazıp  o urlden ne döneceğini yazıyoruz
    }


    private static final String getUrl = "http://localhost:8080/api/brands/getAll";

    @GetMapping("/getAll")
    public List<GetAllResponseBrands> getAll() {
        List<GetAllResponseBrands> getAllResponseBrands = restTemplate.getForObject(getUrl, List.class); //gideceği url yazıp urlden ne dönüceğini yazıyoruz
        return getAllResponseBrands;

    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id) {
        final String getByIdUrl = "http://localhost:8080/api/brands/getById/" + id;
        GetByIdResponseBrand getByIdResponseBrand = restTemplate.getForObject(getByIdUrl, GetByIdResponseBrand.class);
        if (getByIdResponseBrand != null) {
            return ResponseEntity.ok(getByIdResponseBrand);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Böyle bir Id ye Ait Marka Kaydı mevcut değildir");
        }
    }

    @DeleteMapping("/remove/{id}")
    public void delete(@PathVariable("id") int id) {
        final String deleteUrl = "http://localhost:8080/api/brands/remove/" + id;
        restTemplate.delete(deleteUrl);
    }
}
