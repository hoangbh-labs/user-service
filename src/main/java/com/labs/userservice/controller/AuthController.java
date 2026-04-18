package com.labs.userservice.controller;

import com.labs.userservice.entity.UserDetailsImpl;
import com.labs.userservice.payload.base.ErrorResponse;
import com.labs.userservice.payload.base.SuccessResponse;
import com.labs.userservice.payload.request.LoginRequest;
import com.labs.userservice.payload.response.LoginResponse;
import com.labs.userservice.security.JwtService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtService jwtService;

  public AuthController(
      AuthenticationManager authManager,
      JwtService jwtService
  ) {
    this.authManager = authManager;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
    try {
      Authentication authentication = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      UserDetailsImpl userInfo = (UserDetailsImpl) authentication.getPrincipal();
      String token = jwtService.generateToken(userInfo.getUsername());

      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, jwtService.toString())
          .body(new SuccessResponse<>(
              HttpStatus.OK.value(),
              HttpStatus.OK,
              "You've been login successfully!",
              List.of(new LoginResponse(request.getUsername(),token))
          ));

    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new ErrorResponse(
              HttpStatus.UNAUTHORIZED.value(),
              HttpStatus.UNAUTHORIZED,
              "Invalid username or password!"
          ) {});
    }
  }
}
