package com.customer.service.impl;

import com.customer.entity.RoleEntity;
import com.customer.repository.IRoleRepository;
import com.customer.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

  private final IRoleRepository repository;

  @Override
  public RoleEntity save(RoleEntity entity) {
    return repository.save(entity);
  }

  @Override
  public RoleEntity finByTitle(String title) {
    return repository.findByTitle(title);
  }
}