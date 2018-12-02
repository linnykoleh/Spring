package com.learning.linnyk.initialization.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

	private Address address;
	private Item item;

	@Autowired
	public User(Address address) {
		this.address = address;
		System.out.println("#2 Constructor");
	}

	@Autowired
	public void setItem(Item item) {
		this.item = item;
		System.out.println("#3 Setter");
	}

	@PostConstruct
	public void init(){
		System.out.println("#5 PostConstruct");
	}

	@Override
	public String toString() {
		return "User{" +
				"address=" + address +
				", item=" + item +
				'}';
	}
}
