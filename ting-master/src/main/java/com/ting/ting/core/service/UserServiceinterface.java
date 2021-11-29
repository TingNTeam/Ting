package com.ting.ting.core.service;

import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;

import java.util.Optional;

public interface UserServiceinterface {
    void register(RequestUser.Register registerDto);
    Optional<ResponseUser.Login> login(RequestUser.Login requestLoginDto);
    String createAccessToken(String id);
    String createRefreshToken(String id);
}
