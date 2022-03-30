package com.customer.service.impl;

import com.customer.entity.CustomerEntity;
import com.customer.repository.ICustomerRepository;
import com.customer.repository.IRoleRepository;
import com.customer.service.ICustomerService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService {

  private final BCryptPasswordEncoder encoder;
  private final IRoleRepository roleRepository;
  private final ICustomerRepository customerRepository;

  @Override
  public CustomerEntity findById(Long id) {

    log.info("Search customer by id = {}...", id);
    return customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("User with id %d does not exist!", id)));
  }

  @Override
  public CustomerEntity findByUsername(String username) {

    log.info("Search customer by username: \"{}\"...", username);
    return customerRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
        String.format("User with username \"%s\" does not exist!", username)));
  }

  @Override
  public List<CustomerEntity> findAll() {

    log.info("Search all customer...");
    return customerRepository.findAll();
  }

  @Override
  public CustomerEntity save(CustomerEntity entity) {

    if (customerRepository.existsByUsername(entity.getUsername())) {
      log.error("Customer with username \"{}\" is already exist!", entity.getUsername());
      return null;
    }

    if (entity.getRoles() == null) {
      entity.setRoles(List.of(roleRepository.findByTitle("ROLE_USER")));
    }

    entity.setPassword(encoder.encode(entity.getPassword()));
    customerRepository.save(entity);
    log.info("Customer {} has been registered successfully!", entity.getUsername());
    return entity;
  }

  @Override
  public boolean existByUsername(String username) {
    return customerRepository.existsByUsername(username);
  }

  @Override
  public void deleteById(Long id) {

    log.info("Delete customer by id = {}...", id);
    if (customerRepository.existsById(id)) {
      log.error("Customer with id = {} does not exist!", id);
      return;
    }

    customerRepository.deleteById(id);
  }

  @Override
  public void deleteByUsername(String username) {

    log.info("Delete customer by username: \"{}\"...", username);
    if (customerRepository.existsByUsername(username)) {
      log.error("Customer with username \"{}\" does not exist!", username);
      return;
    }

    customerRepository.deleteByUsername(username);
  }
}