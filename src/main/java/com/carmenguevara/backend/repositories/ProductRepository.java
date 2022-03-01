package com.carmenguevara.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmenguevara.backend.models.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product,String> {

	List<Product> findByDescription(String description);
	
}
