package com.customer.service;

import com.customer.entity.RoleEntity;

public interface IRoleService {

  RoleEntity save(RoleEntity entity);

  RoleEntity finByTitle(String title);
}