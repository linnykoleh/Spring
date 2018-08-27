package com.ps;

import org.springframework.jdbc.core.JdbcTemplate;

public class CleanUp {

    private JdbcTemplate jdbcTemplate;

    public CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void destroy() {
        jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
    }

}