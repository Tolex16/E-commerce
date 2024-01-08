package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Role;
import com.example.ecommerce.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
 Optional<UserEntity> findByEmail(String email);

 UserEntity findByRole(Role role);
 Boolean existsByEmail(String email);
}
