package com.pluralsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;
import com.pluralsight.util.ServiceError;

@Controller
public class RideController {

	@Autowired
	private RideService rideService;
	
	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}

	@RequestMapping(value = "/ride", method = RequestMethod.POST)
	public @ResponseBody Ride getRides(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}

	@RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
	public @ResponseBody Ride getRide(@PathVariable(value = "id") Integer id) {
		return rideService.getRide(id);
	}

	@RequestMapping(value = "/ride", method = RequestMethod.PUT)
	public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
		return rideService.updateRide(ride);
	}

	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	public @ResponseBody List<Ride> batchUpdate() {
		return rideService.batchUpdate();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Integer id) {
		rideService.deleteRide(id);
	}

	@RequestMapping(value = "/test_exception", method = RequestMethod.GET)
	public void testExceptionHandling() {
		throw new DataAccessException("Testing exception throwing"){};
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceError> handle(RuntimeException ex){
		System.out.println("Handled error: " + ex.getClass().getName());
		final ServiceError error = new ServiceError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


}
