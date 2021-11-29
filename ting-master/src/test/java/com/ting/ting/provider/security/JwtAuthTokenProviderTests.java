//package com.ting.ting.provider.security;
//
//import com.ting.ting.core.security.role.Role;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//@SpringBootTest
//@ActiveProfiles("local")
//@WebAppConfiguration
//public class JwtAuthTokenProviderTests {
//    @Autowired
//    JwtAuthTokenProvider jwtAuthTokenProvider;
//
//    @DisplayName("jwt 토큰 발급 테스트")
//    @Test
//    void createTokenTest() {
//        //given
//        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant()); // 토큰은 2분만 유지되도록 설정, 2분 후 refresh token
//        //when
//        JwtAuthToken accessToken = jwtAuthTokenProvider.createAuthToken("helloId", Role.ADMIN.getCode(), expiredDate);  //토큰 발급
//        //then
//        System.out.println(accessToken.getToken());
//    }
//
//}
