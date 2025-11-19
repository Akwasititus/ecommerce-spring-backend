package com.titi.ecommercebackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.titi.ecommercebackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
