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
import com.carmenguevara.backend.models.Cart;
import com.carmenguevara.backend.repositories.CartRepository;

@RestController
//@CrossOrigin
@RequestMapping("/api/v1/")
public class CartController {

	@Autowired
	private CartRepository cartRepo;

// Display all items in the Cart
	@GetMapping("/cart/allitems")
	public List<Cart> getAllitems() {
		return cartRepo.findAll();
	}

//	Display products in cart by Product Id

	@GetMapping("cart/{productid}")
	public List<Cart> getItemByProductid(@PathVariable String productid) {
		List<Cart> items = cartRepo.findAll();
		List<Cart> foundItems = new ArrayList<>();

		for (Cart item : items) {
			if (item.getProductid().contains(productid)) {
				foundItems.add(item);
			}
		}
		return foundItems;
	}

//	List all items by User Id
	@GetMapping("cartitems/{userid}")
	public List<Cart> getItemByUserid(@PathVariable String userid) {
		List<Cart> users = cartRepo.findAll();
		List<Cart> foundUsers = new ArrayList<>();

		for (Cart user : users) {
			if (user.getUserid().contains(userid)) {
				foundUsers.add(user);
			}
		}
		return foundUsers;
	}

//	Add Items to the Cart 
	@PostMapping("/cart/additem")
	public Cart newItem(@RequestBody Cart cart) {
		return cartRepo.save(cart);
	}

// Delete Items from the cart
	@DeleteMapping("cart/{cartid}")
	public ResponseEntity<String> deleteCartItem(@PathVariable int cartid) {
		cartRepo.findById(cartid).orElseThrow(() -> new ResourceNotFoundException("Item not Found"));
		String message = "Item has been deleted from Cart.";
		cartRepo.deleteById(cartid);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

// Update Items in Cart
	@PutMapping("cart/update/{cartid}")
	public ResponseEntity<Cart> updateCart(@PathVariable int cartid, @RequestBody Cart newCartInfo){
		Cart foundItem = cartRepo.findById(cartid)
				.orElseThrow(()-> new ResourceNotFoundException("Item not Found in Cart"));
		
		foundItem.setProductid(newCartInfo.getProductid());
		foundItem.setUserid(newCartInfo.getUserid());
		foundItem.setQty(newCartInfo.getQty());
		
		Cart updatedCart = cartRepo.save(foundItem);
		return ResponseEntity.ok(updatedCart);
	}
	
// Update Items by Product Id ---> No Funciona
	@PutMapping("cart/updateproduct/{productid}")
	public ResponseEntity<Cart> updateCartByProductid(@PathVariable String productid, @RequestBody Cart newCartInfo){
		Cart foundItem = cartRepo.findByProductid(productid);
		System.out.println(foundItem);
		foundItem.setProductid(newCartInfo.getProductid());
		foundItem.setUserid(newCartInfo.getUserid());
		foundItem.setQty(newCartInfo.getQty());
		
		Cart updatedCart = cartRepo.save(foundItem);
		
		return ResponseEntity.ok(updatedCart);
	}	
}
