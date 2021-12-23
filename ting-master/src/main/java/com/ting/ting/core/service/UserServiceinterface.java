package com.ting.ting.core.service;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserServiceinterface {
    void register(RequestUser.Register registerDto);
    Optional<ResponseUser.Login> login(RequestUser.Login requestLoginDto);
    String createAccessToken(String id);
    String createRefreshToken(String id);
    void mbtiUpdate(MBTIType mbti,String email);
    Page<ResponseUser.UserSearch> getUserSearch(String type, String keyword, Pageable pageable);
}
