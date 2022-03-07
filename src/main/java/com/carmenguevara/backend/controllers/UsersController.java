package com.carmenguevara.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmenguevara.backend.exceptions.ResourceNotFoundException;
import com.carmenguevara.backend.models.Users;
import com.carmenguevara.backend.repositories.UsersRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UsersController {

		@Autowired
		private UsersRepository usersRepo;
		
//		List all the users 
		@GetMapping("/allusers")
		public List<Users> getAllUsers(){
			return usersRepo.findAll();
		}
//		Display Users name by Phone Number
		@GetMapping("users/{phone}")
			public ResponseEntity<Users> getUsersByPhone(@PathVariable String phone){
			 Users users = usersRepo.findById(phone) 
					 .orElseThrow(()-> new ResourceNotFoundException("Store Not Found"));					
			return ResponseEntity.ok(users);
			}
		
		
//		find all the stores based on name 
		@GetMapping("/allusers/{storename}")
		public List<Users> getUsersByStorename(@PathVariable String storename) {

			List<Users> tempUsers = usersRepo.findAll();
			List<Users> foundUsers = new ArrayList<>();

			for (Users users1 : tempUsers) {
				if (users1.getStorename().contains(storename)) {
					foundUsers.add(users1);
				}
			}
			return foundUsers;
		}
		
// Add user
	@PostMapping("/adduser")
	public Users newUser(@RequestBody Users user) {
		return usersRepo.save(user);
	}
	
//	Delete Users 
	@DeleteMapping("users/{phone}")
	public ResponseEntity<String> deleteUser(@PathVariable String phone){
		usersRepo.findById(phone)
			.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		String message = "User has been Deleted.";
		usersRepo.deleteById(phone);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
// Updating User
	@PutMapping("user/{phone}")
	public ResponseEntity<Users> updateUser(@PathVariable String phone, @RequestBody Users newUserInfo){
		Users foundUser = usersRepo.findById(phone)
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		foundUser.setPhone(newUserInfo.getPhone());
		foundUser.setFirstname(newUserInfo.getFirstname());
		foundUser.setLastname(newUserInfo.getLastname());
		foundUser.setEmail(newUserInfo.getEmail());
		foundUser.setPwd(newUserInfo.getPwd());
		foundUser.setStorename(newUserInfo.getStorename());
		
		Users updatedUser = usersRepo.save(foundUser);
		return ResponseEntity.ok(updatedUser);	
	}
}
