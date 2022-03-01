package com.carmenguevara.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carmenguevara.backend.exceptions.ResourceNotFoundException;
import com.carmenguevara.backend.models.Product;
import com.carmenguevara.backend.repositories.ProductRepository;

@RestController
//@CrossOrigin
@RequestMapping("/api/v1/")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/allproducts")
	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}
		
	@GetMapping("product/{code}")
		public ResponseEntity<Product> getProductByCode(@PathVariable String code){
			Product product = productRepo.findById(code)
									.orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
					return ResponseEntity.ok(product);
			
	}
	
	
	
	
	@PostMapping("addProduct")
	public Product newProduct (@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	
	
}
