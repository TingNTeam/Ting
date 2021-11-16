package com.ting.ting.web;

import com.ting.ting.entity.User;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.provider.service.UserService;
import com.ting.ting.repository.UserRepository;
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

    //    아이디 찾기
    @GetMapping("/find_id")
    public String find_id(){

        return "/find_id";
    }

    //    비밀번호변경
    @GetMapping("/changepassword")
    public String changepassword(){
        return "/changepassword";
    }

    @PostMapping("/changepassword/{username}")
    public String changepwd(){
        return "/changepassword/{username}";

    }

    //비밀번호 찾기
    @GetMapping("/findpassword")
    public String findpassword(){
        return "/findpassword";
    }

}