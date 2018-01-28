package com.marketingsuite.repositories;

import com.marketingsuite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
    User findOneByEmail(String email);
}
