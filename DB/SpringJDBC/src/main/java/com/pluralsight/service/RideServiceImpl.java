package com.pluralsight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;

@Service
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;
	
	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}

	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}

	@Override
	public Ride getRide(Integer id) {
		return rideRepository.getRide(id);
	}

	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}

	@Override
	@Transactional
	public List<Ride> batchUpdate() {
		final List<Ride> rides = rideRepository.getRides();

		final List<Object[]> pairs = new ArrayList<>();

		for(Ride ride : rides){
			pairs.add(new Object []{new Date(), ride.getId()});
		}

		rideRepository.updateRides(pairs);

		//Should not commit the update due to exception
		throw new DataAccessException("Testing Exception Handling"){};
	}

	@Override
	public void deleteRide(Integer id) {
		rideRepository.deleteRide(id);
	}
}
