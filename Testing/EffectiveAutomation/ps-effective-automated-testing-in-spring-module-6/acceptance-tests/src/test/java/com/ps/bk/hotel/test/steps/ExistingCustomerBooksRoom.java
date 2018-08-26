package com.ps.bk.hotel.test.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ps.bk.hotel.booking.model.Booking;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.room.model.Room;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExistingCustomerBooksRoom {

	private final RestTemplate rest = new RestTemplate();

	private Customer joeUser;
	private Room theRoom;
	private String bookingLocation;

	@Given("^The user is an existing customer \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_user_is_an_existing_customer(String arg1, String arg2) {
		final Map<String, String> params = new HashMap<>();
		params.put("fName", arg1);
		params.put("lName", arg2);

		final UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8081/customers/search/byFNameLName")
				.queryParam("fName", arg1)
				.queryParam("lName", arg2);

		final Customer[] customers = rest.getForObject(builder.toUriString(), Customer[].class);

		assertThat(customers).extracting("firstName", "lastName")
				.containsExactly(tuple("Joe", "User"));
		joeUser = customers[0];
	}

	@When("^The user books room (\\d+) for (\\d+) nights$")
	public void the_user_books_room_for_nights(int arg1, int arg2) {
		theRoom = rest.getForObject("http://localhost:8082/rooms/search/byRoomNumber?roomNumber=" + arg1, Room.class);

		final Booking booking = new Booking();
		booking.setCustomerId(joeUser.getId());
		booking.setRoomNumber(theRoom.getRoomNumber());

		final Date startDate = new Date(System.currentTimeMillis());
		final Calendar endDate = new GregorianCalendar();
		endDate.add(Calendar.DAY_OF_YEAR, 5);

		booking.setStartDate(startDate);
		booking.setEndDate(endDate.getTime());

		final ResponseEntity response = rest.postForEntity("http://localhost:8083/bookings", booking, null);

		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		bookingLocation = response.getHeaders().get("Location").get(0);
		assertThat(bookingLocation).startsWith("bookings/");
	}

	@Then("^The user should receive message confirming booking$")
	public void the_user_should_receive_message_confirming_booking() {
		final ResponseEntity<Booking> response = rest.getForEntity("http://localhost:8083/" + bookingLocation, Booking.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
