package com.learning.linnyk.pluralsight.xml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.linnyk.pluralsight.xml.domain.Customer;
import com.learning.linnyk.pluralsight.xml.repository.CustomRepository;

/**
 * @author LinnykOleh
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired /* member injection */
	private CustomRepository customRepository;

	@Autowired /* constructor injection */
	public CustomerServiceImpl(CustomRepository customRepository) {
		this.customRepository = customRepository;
	}

	@Autowired /* setter injection */
	public void setCustomRepository(CustomRepository customRepository) {
		this.customRepository = customRepository;
	}

	@Override
	public List<Customer> findAll() {
		return customRepository.findAll();
	}
}
