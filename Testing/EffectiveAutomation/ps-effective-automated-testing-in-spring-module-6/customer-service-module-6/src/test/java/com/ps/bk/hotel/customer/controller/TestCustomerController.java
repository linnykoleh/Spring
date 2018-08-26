package com.ps.bk.hotel.customer.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ps.bk.hotel.customer.exception.CustomerSerivceClientException;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class, secure = false)
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
@DirtiesContext
public class TestCustomerController {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Test
	public void testSuccessfulFindAllCustomers() throws Exception {
		final Customer customer = new Customer(1L, "John", "Doe", "Middle", "Jr", dateFormat.parse("11/15/2017"));
		final Customer customer2 = new Customer(1L, "Jane", "Doe", "Middle", "", dateFormat.parse("10/11/2017"));

		when(service.findAllCustomers()).thenReturn(Arrays.asList(customer, customer2));

		mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(2)))
				.andDo(document("customers", responseFields(fieldWithPath("[].id").description("The customer's Id"),
						fieldWithPath("[].firstName").description("The customer's first name"),
						fieldWithPath("[].lastName").description("The customer's last name"),
						fieldWithPath("[].middleName").description("The customer's middle name"),
						fieldWithPath("[].suffix").description("The customer's suffix"),
						fieldWithPath("[].lastStayDate").description(
								"The date the customer last stayed at the hotel formatted as: yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))));
	}

	@Test
	public void testSuccessSearchCustomersByFNameLName() throws Exception {
		final Customer customer = new Customer(1L, "John", "Doe", "Middle", "Jr", dateFormat.parse("11/15/2017"));

		when(service.findCustomersByFNameLName("test", "test")).thenReturn(Arrays.asList(customer));

		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=test")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(1)))
				.andDo(document("customers-search-by-fname-lname",
						requestParameters(parameterWithName("fName").description("The customer's first name"),
								parameterWithName("lName").description("The customer's last name")),
						responseFields(fieldWithPath("[].id").description("The customer's Id"),
								fieldWithPath("[].firstName").description("The customer's first name"),
								fieldWithPath("[].lastName").description("The customer's last name"),
								fieldWithPath("[].middleName").description("The customer's middle name"),
								fieldWithPath("[].suffix").description("The customer's suffix"),
								fieldWithPath("[].lastStayDate").description(
										"The date the customer last stayed at the hotel formatted as: yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))));

	}

	@Test
	public void testMissingParamSearchCustomersByFNameLName() throws Exception {
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test")).andExpect(status().isBadRequest())
				.andExpect(content().string("")).andDo(document("customers",
						requestParameters(parameterWithName("fName").description("The customer's first name"))));

		verify(service, times(0)).findCustomersByFNameLName(any(), any());
	}

	@Test
	public void testInvalidParamSearchCustomersByFNameLName() throws Exception {
		when(service.findCustomersByFNameLName(any(), any()))
				.thenThrow(new CustomerSerivceClientException("Invalid parameter passed in!"));

		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=invalid"))
				.andExpect(status().isBadRequest()).andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Invalid parameter passed in!"));
	}

}