package com.example.bootcamp;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("uuid")
class UuidService {

	public String buildUuid() {
		return UUID.randomUUID().toString();
	}

}
