package com.carmenguevara.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmenguevara.backend.models.Product;
import com.carmenguevara.backend.repositories.ProductRepository;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/allproduct")
	public List<Product> getAllProduct(){
		return productRepo.findAll();
		
	}
	

}
