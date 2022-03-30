package com.customer.security;

import com.customer.entity.CustomerEntity;
import com.customer.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final CustomerServiceImpl service;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomerEntity entity = service.findByUsername(username);
    return JwtCustomer.build(entity);
  }
}