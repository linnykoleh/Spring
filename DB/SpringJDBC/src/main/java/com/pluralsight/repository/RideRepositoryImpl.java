package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {
		final Ride ride = new Ride();
		ride.setName("Corner Canyon");
		ride.setDuration(120);
		List<Ride> rides = new ArrayList<>();
		rides.add(ride);
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		// jdbcTemplate.update - insert, update or delete statement
		jdbcTemplate.update(
				"insert INTO RIDE(NAME, DURATION) values (?, ?)",
				ride.getName(), ride.getDuration());

		return ride;
	}

}
