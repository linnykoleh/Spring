package com.ps.bk.hotel.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ps.bk.hotel.customer.exception.CustomerSerivceClientException;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> findAllCustomers(){
		return ResponseEntity.ok(service.findAllCustomers());
	}
	
	@GetMapping("/search/byFNameLName")
	public ResponseEntity<?> findCustomersByFNameLName(@RequestParam("fName") String fName,@RequestParam("lName") String lName){
		ResponseEntity<?> response; 
		try {
			response = ResponseEntity.ok(service.findCustomersByFNameLName(fName, lName));
		} catch(CustomerSerivceClientException e) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return response;
	}
}
