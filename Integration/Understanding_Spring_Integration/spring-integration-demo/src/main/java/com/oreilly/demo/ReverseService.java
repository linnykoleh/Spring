package com.oreilly.demo;

public class ReverseService {

	public void reverse(String message){
		System.out.println(new StringBuilder(message).reverse().toString());
	}
}
