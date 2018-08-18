package com.ps.bk.hotel.customer.repo;

import org.springframework.data.repository.CrudRepository;

import com.ps.bk.hotel.customer.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

	Iterable<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);

}
