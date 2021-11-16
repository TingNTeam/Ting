package com.ting.ting.provider.service;

import com.ting.ting.core.service.UserServiceinterface;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.web.dto.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceinterface{
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public void register(RequestUser.Register registerDto) {

    }
}
