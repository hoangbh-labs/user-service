package com.labs.userservice.service;

import com.labs.userservice.entity.User;
import com.labs.userservice.payload.response.UserInfoResponse;
import com.labs.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository repo;

  public UserService(UserRepository repo) {
    this.repo = repo;
  }

  public UserInfoResponse getUser(Long id) {
    Optional<User> user = repo.findById(id);
    return user.map(u -> new UserInfoResponse(
        u.getUsername(),
        u.getEmail(),
        List.of(u.getRole())
    )).orElse(null);
  }
}
