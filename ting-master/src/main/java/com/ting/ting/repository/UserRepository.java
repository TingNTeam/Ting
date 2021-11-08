package com.ting.ting.repository;

import com.ting.ting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
    User findByUsername(String username);
    User findByNameAndNumberAndSchool(String name,Long number,String school);
}
