package com.ting.ting.provider.service;

import com.ting.ting.core.service.UserServiceinterface;
import com.ting.ting.entity.Role;
import com.ting.ting.entity.User;
import com.ting.ting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceinterface{
    private UserRepository userRepository;





}
