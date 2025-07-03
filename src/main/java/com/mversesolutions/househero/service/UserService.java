package com.mversesolutions.househero.service;

import com.mversesolutions.househero.entitiy.userdetails.User;
import com.mversesolutions.househero.repository.UserRepository;
import com.mversesolutions.househero.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Load user info
        UserRepository.UserRecord userRecord = userRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Load authorities
        List<GrantedAuthority> authorities = authorityRepository.findAuthoritiesByEmail(email);

        return new User( userRecord.email(), userRecord.password(), authorities, userRecord.enabled());
    }
}
