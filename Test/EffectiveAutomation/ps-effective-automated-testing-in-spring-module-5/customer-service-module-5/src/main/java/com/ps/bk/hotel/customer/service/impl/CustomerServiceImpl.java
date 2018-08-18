package com.ps.bk.hotel.customer.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ps.bk.hotel.customer.exception.CustomerSerivceClientException;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.customer.repo.CustomerRepo;
import com.ps.bk.hotel.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepo repo;
	
	public CustomerServiceImpl(CustomerRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<Customer> findAllCustomers() {
		return repo.findAll();
	}

	@Override
	public Iterable<Customer> findCustomersByFNameLName(String firstName, String lastName) throws CustomerSerivceClientException {
		if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
			throw new CustomerSerivceClientException("Missing required parameter!");
		} else if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
			throw new CustomerSerivceClientException("First and/or last name is not in proper format!");
		}
		return repo.findCustomersByFirstNameAndLastName(firstName, lastName);
	}

}
