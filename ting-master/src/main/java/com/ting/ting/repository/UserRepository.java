package com.ting.ting.repository;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    Page<User> findByMbti(MBTIType mbtiType, Pageable pageable);
    Page<User> findByNickname(String nickname, Pageable pageable);
}
