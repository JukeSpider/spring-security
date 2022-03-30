package com.customer.controller;

import com.customer.dto.CustomerDto;
import com.customer.entity.CustomerEntity;
import com.customer.mapper.ICustomerMapper;
import com.customer.service.impl.CustomerServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

  private final CustomerServiceImpl service;
  private final ICustomerMapper mapper;

  @GetMapping("/users/{id}")
  public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {

    CustomerEntity entity = service.findById(id);

    if (entity == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
  }

  @GetMapping("/users/all")
  public ResponseEntity<List<CustomerDto>> findAll() {

    List<CustomerEntity> entityList = service.findAll();

    if (entityList == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(entityList));
  }
}