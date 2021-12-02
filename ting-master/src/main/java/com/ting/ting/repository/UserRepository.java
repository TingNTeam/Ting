package com.ting.ting.repository;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    Page<User> findByMbti(MBTIType mbti, Pageable pageable);
    Page<User> findByNickname(String nickname, Pageable pageable);
}
