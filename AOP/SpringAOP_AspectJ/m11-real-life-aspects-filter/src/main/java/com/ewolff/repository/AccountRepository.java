package com.ewolff.repository;

import org.springframework.stereotype.Repository;

import com.ewolff.domain.Account;

@Repository
public class AccountRepository {

	public Account getAccount(int id) {
		if (id==42) {
			return new Account("Eberhard","Wolff",42);
		} else {
			return new Account("Juergen","Hoeller",id);
		}
	}

}
