package com.pluralsight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class TesterAssumptionTests {

	@Test
    public void testOnProd() {
        System.setProperty("ENV", "DEV");
        Assumptions.assumingThat("DEV".equals(System.getProperty("ENV")), () -> Assertions.assertTrue(false));
    }	
}
