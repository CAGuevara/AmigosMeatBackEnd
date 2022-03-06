package com.carmenguevara.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carmenguevara.backend.models.Cart;

public interface CartRepository extends JpaRepository <Cart,Integer> {

	Cart findByProductid(String productid);

	
	

}
