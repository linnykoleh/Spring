package com.pluralsight.repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.util.RideRowMapper;

@Repository
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {
		return jdbcTemplate.query("SELECT * FROM RIDE", new RideRowMapper());
	}

	/**
	 * JdbcTemplate#update(java.lang.String, java.lang.Object...)
	 * Uses for
	 * 	 - insert
	 * 	 - update
	 * 	 - delete
	 */
	@Override
	public Ride createRide(Ride ride) {
		jdbcTemplate.update(
				"insert INTO RIDE(NAME, DURATION) values (?, ?)",
				ride.getName(), ride.getDuration());

		return ride;
	}

	@Override
	public Ride getRide(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM RIDE WHERE ID = id", new RideRowMapper());
	}

	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("UPDATE RIDE set NAME = ?, DURATION = ? where id = ?",
				ride.getName(), ride.getDuration(), ride.getId());
		return ride;
	}

	@Override
	public List<Ride> updateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("UPDATE RIDE set RIDE_DATE = ? where id = ?", pairs);
		return getRides();
	}

	@Override
	public void deleteRide(Integer id) {
		jdbcTemplate.update("delete from RIDE where id = ?", id);
	}

	/**
	 * Alternative to RideRepositoryImpl#createRide(com.pluralsight.model.Ride)
	 */
	public Ride createRideSimpleJDBC(Ride ride) {
		final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		insert.setGeneratedKeyName("id");

		final Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		final List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);

		final Number id = insert.executeAndReturnKey(data);
		return getRide(id);
	}

	/**
	 * Alternative to RideRepositoryImpl#createRide(com.pluralsight.model.Ride)
	 */
	public Ride createRideKeyHolderJDBC(Ride ride){
		final KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert INTO RIDE(NAME, DURATION) values (?, ?)",
					new String[]{"id"});
			ps.setString(1, ride.getName());
			ps.setInt(2, ride.getDuration());
			return ps;
		}, keyHolder);

		return getRide(keyHolder.getKey());
	}

	/**
	 * Alternative to RideRepositoryImpl#deleteRide(java.lang.Integer)
	 */
	public void deleteRideNamedParameter(Integer id) {
		final NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		final Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("id", id);

		namedTemplate.update("delete from RIDE where id = :id", paramsMap);
	}

	private Ride getRide(Number key) {
		return jdbcTemplate.queryForObject("SELECT * FROM RIDE WHERE id = ?", new RideRowMapper(), key);
	}

}
