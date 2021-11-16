package com.ting.ting.web;

import com.ting.ting.entity.User;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.web.dto.CommonResponse;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping("/user/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody RequestUser.Login requestLoginDto) {

        //  .status(HttpStatus.OK.value()) 이거 없어도 되는지 확인하기
        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public String register() {
        return "/user/register";
    }

}
