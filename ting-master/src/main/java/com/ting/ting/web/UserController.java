package com.ting.ting.web;

import com.ting.ting.entity.User;
import com.ting.ting.exception.errors.LoginFailedException;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.web.dto.CommonResponse;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import com.ting.ting.web.dto.CommonResponse;
import com.ting.ting.web.dto.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    //회원가입
    @PostMapping("/user/register")
    public ResponseEntity<CommonResponse> requestRegister(@Valid @RequestBody RequestUser.Register registerDto){

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //로그인
    @PostMapping("/user/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody RequestUser.Login requestLoginDto) {
        //service만들고 예외처리하기
        ResponseUser.Login token = userService.login(requestLoginDto).orElseThrow(()-> new LoginFailedException());

        //내부에 키와 값을 저장하는 자료 구조
        HashMap<String, Object> map = new HashMap<>();
        map.put("accessToken", token.getAccessToken());
        map.put("refreshToken", token.getRefreshToken());

                //  .status(HttpStatus.OK.value()) 이거 없어도 되는지 확인하기
                CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("성공")
                .list(map)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}