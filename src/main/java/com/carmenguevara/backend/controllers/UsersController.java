package com.carmenguevara.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmenguevara.backend.models.Users;
import com.carmenguevara.backend.repositories.UsersRepository;
import com.carmenguevara.backend.exceptions.ResourceNotFoundException;

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
		@GetMapping("/allUsers/{storename}")
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

}
