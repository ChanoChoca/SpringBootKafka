package com.chanochoca.app.web;

import com.chanochoca.app.entity.Customer;
import com.chanochoca.app.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping
	public Customer save(@RequestBody Customer customer) {
		return this.customerService.save(customer);
	}
	

}
