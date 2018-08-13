package com.learning.linnyk.pluralsight.java.service;

import java.util.List;

import com.learning.linnyk.pluralsight.java.Customer;
import com.learning.linnyk.pluralsight.java.repository.CustomRepository;

/**
 * @author LinnykOleh
 */
public interface CustomerService {

	List<Customer> findAll();

	CustomRepository getCustomRepository();
}
