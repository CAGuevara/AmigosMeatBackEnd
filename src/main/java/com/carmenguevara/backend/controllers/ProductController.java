package com.carmenguevara.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

//	Display all the products
	@GetMapping("/allproducts")
	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}
	
//	Display products by code
	@GetMapping("product/{code}")
		public ResponseEntity<Product> getProductByCode(@PathVariable String code){
			Product product = productRepo.findById(code)
							.orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
					return ResponseEntity.ok(product);
	}
	
//	Find product by any word in the Description
	
	@GetMapping("allproducts/{description}")
	public List<Product> getProductsByDescription(@PathVariable String description){
		List<Product> products = productRepo.findAll();
		List<Product> foundProducts = new ArrayList<>();
		
		for (Product product : products) {
			if(product.getDescription().contains(description)) {
				foundProducts.add(product);
			}
		} 
		return foundProducts;
	}
	
	
//	Add Products to the database 
	@PostMapping("addproduct")
	public Product newProduct (@RequestBody Product product) {
		return productRepo.save(product);
	}
	
//  Delete Product to the database 
	@DeleteMapping("product/{code}")
	public ResponseEntity<String> deleteProduct(@PathVariable String code) {
		productRepo.findById(code)
			.orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
		String message = "Product has been deleted.";
		productRepo.deleteById(code);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
// Updating Products 
	@PutMapping("product/{code}")
	public ResponseEntity<Product> updateProduct(@PathVariable String code, @RequestBody Product newProductInfo){
		Product foundProduct = productRepo.findById(code)
				.orElseThrow(()-> new ResourceNotFoundException("Product Not Found."));
		foundProduct.setCode(newProductInfo.getCode());
		foundProduct.setDescription(newProductInfo.getDescription());
		foundProduct.setCost(newProductInfo.getCost());
		foundProduct.setQty(newProductInfo.getQty());
		Product updatedProduct = productRepo.save(foundProduct); 
		return ResponseEntity.ok(updatedProduct);
	}
}
