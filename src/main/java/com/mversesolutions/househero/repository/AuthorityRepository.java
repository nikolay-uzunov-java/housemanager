package com.mversesolutions.househero.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AuthorityRepository {
    private static final String FIND_AUTHORITIES_BY_EMAIL = """
        SELECT authority FROM authorities WHERE email = :email
        """;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<GrantedAuthority> findAuthoritiesByEmail(String email) {
        return namedParameterJdbcTemplate.query(FIND_AUTHORITIES_BY_EMAIL, Map.of("email", email), (rs, i) ->
          new SimpleGrantedAuthority(rs.getString("authority"))
        );
    }
}
