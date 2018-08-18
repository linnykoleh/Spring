package com.ps.bk.hotel.customer.service;

import com.ps.bk.hotel.customer.exception.CustomerSerivceClientException;
import com.ps.bk.hotel.customer.model.Customer;

public interface CustomerService {
	Iterable<Customer> findAllCustomers();

	Iterable<Customer> findCustomersByFNameLName(String firstName, String lastName) throws CustomerSerivceClientException;
}
