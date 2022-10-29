package com.bm.world.repository;

import com.bm.world.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String username);
    Boolean existsByEmailId(String emailId);
}
