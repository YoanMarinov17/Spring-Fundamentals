package com.example.shopping.repository;

import com.example.shopping.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);
}