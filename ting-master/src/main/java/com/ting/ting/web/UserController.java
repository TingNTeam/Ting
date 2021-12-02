package com.ting.ting.web;

import com.ting.ting.entity.User;
import com.ting.ting.exception.errors.CustomJwtRuntimeException;
import com.ting.ting.exception.errors.LoginFailedException;
import com.ting.ting.exception.errors.RegisterFailedException;
import com.ting.ting.provider.security.JwtAuthToken;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    //회원가입
    @PostMapping("/user/register")
    public ResponseEntity<CommonResponse> requestRegister(@Valid @RequestBody RequestUser.Register registerDto){
        //userService로 넘겨주기(선언 안해주면 Service단으로 안넘어간다)
        userService.register(registerDto);

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

    //MBTI 등록 및 업데이트
    @PostMapping("/user/mbti/update")
    public ResponseEntity<CommonResponse> mbtiUpdate(HttpServletRequest request, @Valid @RequestBody RequestUser.MbtiUpdate mbti){
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String email = null;
        if(token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getData().getSubject();
        }
        userService.mbtiupdate(mbti.getMbti(), email);

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}