package com.ting.ting.repository;

import com.ting.ting.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
    Manager findByEmailAndPassword(String email, String password);
}
