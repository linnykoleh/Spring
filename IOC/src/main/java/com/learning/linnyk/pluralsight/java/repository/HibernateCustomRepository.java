package com.learning.linnyk.pluralsight.java.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.learning.linnyk.pluralsight.java.Customer;

/**
 * @author LinnykOleh
 */
@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HibernateCustomRepository implements CustomRepository {

	@Value("${dbUsername}")
	private String dbUsername;

	@Override
	public List<Customer> findAll() {
		final List<Customer> customers = new ArrayList<>();

		final Customer customer = new Customer();

		customer.setFirstName("Bryan");
		customer.setLastName("Hansen");

		customers.add(customer);

		return customers;
	}

	public String getDbUsername() {
		return dbUsername;
	}
}
