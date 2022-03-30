package com.customer.repository;

import com.customer.entity.CustomerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsById(Long id);

  void deleteByUsername(String username);
}