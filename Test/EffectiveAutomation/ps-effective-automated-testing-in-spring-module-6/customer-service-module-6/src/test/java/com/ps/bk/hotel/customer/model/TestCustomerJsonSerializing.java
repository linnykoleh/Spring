package com.ps.bk.hotel.customer.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCustomerJsonSerializing {

	private JacksonTester<Customer> json;
	OffsetDateTime dateTime;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private String jsonValidFullCustomer = "{\"firstName\" : \"John\",\"lastName\" : \"Doe\",\"middleName\" : \"Middle\",\"suffix\" : \"Jr.\",\"lastStayDate\" : \"2017-09-12T00:00:00.000Z\"}";
	private String jsonMissingFirstNameCustomer = "{\"lastName\" : \"Doe\",\"middleName\" : \"Middle\",\"suffix\" : \"Jr.\",\"lastStayDate\" : \"2017-09-12T00:00:00.000Z\"}";
	private String jsonCustomerExtraFields = "{\"firstName\" : \"John\",\"lastName\" : \"Doe\",\"middleName\" : \"Middle\",\"suffix\" : \"Jr.\",\"lastStayDate\" : \"2017-09-12T00:00:00.000Z\", \"ExtraField\" : \"extraValue\"}";

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void testValidFullCustomerParseJson() throws IOException, ParseException {
		Customer customer = new Customer.CustomerBuilder().firstName("John").lastName("Doe").middleName("Middle")
				.suffix("Jr.").id(0L).dateOfLastStay(df.parse("2017-09-12T00:00:00.000Z")).build();

		this.json.parse(jsonValidFullCustomer).assertThat().isEqualTo(customer);
	}
	
	@Test
	public void testValidFullCustomerMarshallObjectToJson() throws IOException, ParseException {
		Customer customer = new Customer.CustomerBuilder().firstName("John").lastName("Doe").middleName("Middle")
				.suffix("Jr.").id(0L).dateOfLastStay(df.parse("2017-09-12T00:00:00.000Z")).build();

		this.json.write(customer).assertThat().isEqualTo(jsonValidFullCustomer);
	}

	@Test
	public void testCustomerMissingRequiredField() throws IOException {
		try {
			this.json.parse(jsonMissingFirstNameCustomer);
			fail("Exception should had been thrown!");
		} catch (Exception e) {
			assertEquals(JsonMappingException.class, e.getClass());
		}
	}

	@Test
	public void testCustomerIgnoreExtraField() throws IOException {
		try {
			this.json.parse(jsonCustomerExtraFields);
		} catch (Exception e) {
			fail("Exception should not had been thrown!");
		}
	}
}
