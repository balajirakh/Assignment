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

	/**
	 * @author Balaji Rakh
	 * @apiNote To get All customer data from D/B
	 * @since 1.0
	 * @return  
	 */

	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To get customer data from D/B
	 * @since 1.0
	 * @param categoryId
	 * @return  
	 */

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To add new customer Details.
	 * @since 1.0
	 * @param Customer @return 
	 */
    @PostMapping("/post")
	public Customer addCustomer(@RequestBody Customer customer) {

		if (customer.getName() == null || customer.getEmail() == null) {

			throw new IllegalArgumentException("Customer name and email must not be null");
		}

		return customerRepository.save(customer);
	}

}
