package com.customer.controller;

import com.customer.dto.CustomerDto;
import com.customer.entity.CustomerEntity;
import com.customer.mapper.ICustomerMapper;
import com.customer.security.JwtTokenProvider;
import com.customer.service.impl.CustomerServiceImpl;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

  private final ICustomerMapper mapper;
  private final AuthenticationManager manager;
  private final JwtTokenProvider provider;
  private final CustomerServiceImpl service;

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody CustomerDto dto) {

    manager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
    );

    CustomerEntity entity = service.findByUsername(dto.getUsername());
    String token = provider.createToken(dto.getUsername(), entity.getRoles());

    Map<String, String> response = Map.of(
        "username", dto.getUsername(),
        "token", token
    );

    return ResponseEntity.ok(response);
  }

  @PostMapping("/registration")
  public ResponseEntity<CustomerDto> registration(@RequestBody CustomerDto dto) {
    CustomerEntity entity = mapper.toEntity(dto);

    if (service.save(entity) == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(service.save(entity)));
  }
}