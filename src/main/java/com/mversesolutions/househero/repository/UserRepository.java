package com.mversesolutions.househero.repository;

import com.mversesolutions.househero.entitiy.userdetails.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final String FIND_BY_EMAIL = """
        SELECT email, password, enabled FROM users WHERE email = :email
        """;

    private final NamedParameterJdbcTemplate jdbc;

    public UserRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<UserRecord> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);

        List<UserRecord> users = jdbc.query(FIND_BY_EMAIL, params, (rs, rowNum) ->
          new UserRecord(
            rs.getString("email"),
            rs.getString("password"),
            rs.getBoolean("enabled")
          )
        );

        return users.stream().findFirst();
    }

    public record UserRecord(String email, String password, boolean enabled) {}
}
