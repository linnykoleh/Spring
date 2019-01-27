package com.example.bootcamp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UuidServiceTest {

	@Autowired
	private UuidService uuidService;

	@Test
	public void buildUuid() throws Exception {
		Assert.assertNotEquals(this.uuidService.buildUuid(),
				this.uuidService.buildUuid());
	}

}