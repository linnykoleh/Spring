package com.ps.bk.hotel.booking;

import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.ps.bk.hotel.booking.controller.BookingController;
import com.ps.bk.hotel.booking.service.BookingService;

@SpringBootTest (classes = BookingServiceApplication.class)
@RunWith(SpringRunner.class )
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMessageVerifier
public class BaseClass {
	@Autowired
    private BookingController bookingController;

	@MockBean
	private BookingService bookingService;
	
    @Before
    public void before() throws Throwable {
    		Mockito.when(bookingService.addBooking(any())).thenReturn(1L);
        RestAssuredMockMvc.standaloneSetup(this.bookingController);
    }

}
