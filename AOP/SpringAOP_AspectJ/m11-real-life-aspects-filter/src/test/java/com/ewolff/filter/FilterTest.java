package com.ewolff.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.aspect.AccountFilterAspect;
import com.ewolff.domain.Account;
import com.ewolff.domain.Customer;
import com.ewolff.repository.AccountRepository;

import configuration.SystemConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class FilterTest {

	@Autowired
	private AccountFilterAspect accountFilterAspect;

	@Autowired
	private AccountRepository accountRepository;

	@Before
	public void setUp() {
		accountFilterAspect.clearCurrentCustomer();
	}

	@Test
	public void springBeanFiltersEverythingIfNoCustomerSet() {
		assertThat(accountRepository.getAccount(42), nullValue());
		assertThat(accountRepository.getAccount(1), nullValue());
	}

	@Test
	public void springBeanFiltersOtherAccountIfCustomerSet() {
		accountFilterAspect.setCurrentCustomer(new Customer("Eberhard", "Wolff"));
		assertThat(accountRepository.getAccount(42), equalTo(new Account(
				"Eberhard", "Wolff", 42)));
		assertThat(accountRepository.getAccount(1), nullValue());
	}

	@Test
	public void plainObjectWontFilter() {
		AccountRepository plainAccountRepository = new AccountRepository();
		assertThat(plainAccountRepository.getAccount(42), equalTo(new Account(
				"Eberhard", "Wolff", 42)));
		assertThat(plainAccountRepository.getAccount(1), equalTo(new Account(
				"Juergen", "Hoeller", 1)));
	}

}
