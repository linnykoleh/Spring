package com.learning.linnyk.pluralsight.xml.repository;

import java.util.List;

import com.learning.linnyk.pluralsight.xml.domain.Customer;

/**
 * @author LinnykOleh
 */
public interface CustomRepository {

	List<Customer> findAll();
}
