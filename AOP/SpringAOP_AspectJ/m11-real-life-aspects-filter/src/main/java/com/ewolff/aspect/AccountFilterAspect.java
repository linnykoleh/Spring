package com.ewolff.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ewolff.domain.Account;
import com.ewolff.domain.Customer;

@Aspect
@Component
public class AccountFilterAspect {

	private ThreadLocal<Customer> currentCustomer = new ThreadLocal<Customer>();

	private Customer getCurrentCustomer() {
		return currentCustomer.get();
	}

	public void setCurrentCustomer(Customer customer) {
		currentCustomer.set(customer);
	}

	public void clearCurrentCustomer() {
		currentCustomer.set(null);
	}

	@Around("execution(com.ewolff.domain.Account *(..))")
	public Object filterAccount(ProceedingJoinPoint joinPoint) throws Throwable {
		Customer customer = getCurrentCustomer();
		if (customer == null) {
			return null;
		}
		Account result = (Account) joinPoint.proceed();
		if (customer.getFirstname().equals(result.getFirstname())
				&& customer.getName().equals(result.getName())) {
			return result;
		} else {
			return null;
		}
	}
}
