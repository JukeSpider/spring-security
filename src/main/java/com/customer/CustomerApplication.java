package com.customer;

import com.customer.entity.CustomerEntity;
import com.customer.entity.RoleEntity;
import com.customer.service.impl.CustomerServiceImpl;
import com.customer.service.impl.RoleServiceImpl;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CommandLineRunner runner(
      CustomerServiceImpl customerService,
      RoleServiceImpl roleService
  ) {
    return args -> {
      RoleEntity admin = roleService.save(RoleEntity.builder().title("ROLE_ADMIN").build());
      RoleEntity user = roleService.save(RoleEntity.builder().title("ROLE_USER").build());

      customerService.save(CustomerEntity.builder()
          .username("admin")
          .password("admin")
          .roles(List.of(admin, user))
          .build());
    };
  }
}