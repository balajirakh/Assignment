package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.Customer;
import com.assignment.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
	}

	@PostMapping("/post")
	public Customer addCustomer(@RequestBody Customer customer) {

		if (customer.getName() == null || customer.getEmail() == null) {

			throw new IllegalArgumentException("Customer name and email must not be null");
		}

		return customerRepository.save(customer);
	}

}
