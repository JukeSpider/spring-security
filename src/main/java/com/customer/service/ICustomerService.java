package com.customer.service;

import com.customer.entity.CustomerEntity;
import java.util.List;

public interface ICustomerService {

  CustomerEntity findById(Long id);

  CustomerEntity findByUsername(String login);

  List<CustomerEntity> findAll();

  CustomerEntity save(CustomerEntity dto);

  boolean existByUsername(String username);

  void deleteById(Long id);

  void deleteByUsername(String login);
}