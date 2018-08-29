package com.pluralsight.util;

import com.pluralsight.model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapper implements RowMapper<Ride> {

    @Override
    public Ride mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Ride(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("duration")
        );
    }
}
