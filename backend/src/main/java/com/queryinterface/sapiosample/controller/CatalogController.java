package com.queryinterface.sapiosample.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.queryinterface.sapiosample.Product;

@RestController
@RequestMapping("/api")
public class CatalogController {

	public CatalogController() {
		//
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/products", produces = "application/json")
	public ResponseEntity<Iterable<Product>> getAllProducts() {
		Iterable<Product> products = new ArrayList<>();
		
		ODataS4Client client = new ODataS4Client();
		products = client.getProducts();
		
        return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
