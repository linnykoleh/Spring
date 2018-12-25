package com.pluralsight;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesterExceptionTests {

	@Test
	public void testException() {
		Assertions.assertThrows(Exception.class, () -> {
			throw new Exception("Test exceptions");
	    });
	}
	
	@Test
	public void timeout() {
	    Assertions.assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(5));
	}

}
