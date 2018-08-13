package com.learning.linnyk.pluralsight.java.repository;

import java.util.List;

import com.learning.linnyk.pluralsight.java.Customer;

/**
 * @author LinnykOleh
 */
public interface CustomRepository {

	List<Customer> findAll();
}
