package com.ting.ting.provider.service;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.entity.User;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
public class MbtiUpdateTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("MBTI 업데이트 test")
    @Transactional
    void mbtiupdateTest() {
        //given
        RequestUser.Register dto = RequestUser.Register.builder()
                .email("hello")
                .password("itsmypassword")
                .name("haha")
                .nickname("narara")
                .birth("970312")
                .build();
        userService.register(dto);
        User user = userRepository.findByEmail(dto.getEmail());
        //then
        userService.mbtiupdate(MBTIType.ENFJ, user.getEmail());

        System.out.println(user.getMbti());
        System.out.println("dto.getMbti()");

    }
}
