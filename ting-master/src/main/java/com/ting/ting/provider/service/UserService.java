package com.ting.ting.provider.service;

import com.ting.ting.core.service.UserServiceinterface;
import com.ting.ting.entity.Role;
import com.ting.ting.entity.User;
import com.ting.ting.exception.errors.LoginFailedException;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.util.SHA256Util;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceinterface{
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public void register(RequestUser.Register registerDto) {

    }


    @Transactional
    @Override
    public Optional<ResponseUser.Login> login(RequestUser.Login requestLoginDto){
        //이메일 user 꺼내기
        User user = userRepository.findByEmail(requestLoginDto.getEmail());
        //email에 맞는 user 없을 경우
        if (user == null){
            throw new LoginFailedException();
        }
            //salt 꺼내기
            String salt = user.getSalt();
            //salt와 requestLoginDto의 password랑 합치고 암호화
            String encryptedPassword = SHA256Util.getEncrypt(requestLoginDto.getPassword(), salt);
            //비교
            user = userRepository.findByEmailAndPassword(requestLoginDto.getEmail(), encryptedPassword);
            //비밀번호가 맞지 않을 경우
        if(user == null){
            throw new LoginFailedException();
        }
        else{
        }

    }

}
