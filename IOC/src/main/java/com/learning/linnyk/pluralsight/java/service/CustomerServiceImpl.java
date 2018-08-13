package com.learning.linnyk.pluralsight.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.learning.linnyk.pluralsight.java.Customer;
import com.learning.linnyk.pluralsight.java.repository.CustomRepository;

/**
 * @author LinnykOleh
 */
@Service
@Scope("singleton")
public class CustomerServiceImpl implements CustomerService {

	@Value("${timeout}")
	private int timeout;

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

	public CustomerServiceImpl() {
	}

	@Override
	public List<Customer> findAll() {
		return customRepository.findAll();
	}

	@Override
	public CustomRepository getCustomRepository() {
		return customRepository;
	}

	public int getTimeout() {
		return timeout;
	}
}
