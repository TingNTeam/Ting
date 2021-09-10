package com.ting.ting.repository;

import com.ting.ting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByNameAndNumberAndSchool(String name,Long number,String school);
}
