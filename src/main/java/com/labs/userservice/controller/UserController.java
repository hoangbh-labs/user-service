package com.labs.userservice.controller;

import com.labs.userservice.payload.base.ErrorResponse;
import com.labs.userservice.payload.base.SuccessResponse;
import com.labs.userservice.payload.response.UserInfoResponse;
import com.labs.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
    UserInfoResponse userInfoResponse = service.getUser(id);
    if (userInfoResponse == null) {
      return ResponseEntity.badRequest().body(
          new ErrorResponse(
              HttpStatus.NOT_FOUND.value(),
              HttpStatus.NOT_FOUND,
              "User not found!"
          ));
    }
    return ResponseEntity.ok()
        .body(new SuccessResponse<>(
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "Query successfully!",
            List.of(userInfoResponse)
        ));
  }
}
