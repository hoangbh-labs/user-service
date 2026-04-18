package com.labs.userservice.service;

import com.labs.userservice.entity.UserDetailsImpl;
import com.labs.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repo;

  public UserDetailsServiceImpl(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    var user = repo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Not found"));

    return UserDetailsImpl.build(user);
  }
}
