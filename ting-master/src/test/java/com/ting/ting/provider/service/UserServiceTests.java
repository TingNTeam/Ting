package com.ting.ting.provider.service;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("검색 기능 테스트")
    @Transactional
    void SearchTest(){
        //회원 등록
        RequestUser.Register dto1 = RequestUser.Register.builder()
                .email("1234")
                .password("1234")
                .name("name1")
                .nickname("nick1")
                .birth("1234")
                .build();
        userService.register(dto1);
        RequestUser.Register dto2 = RequestUser.Register.builder()
                .email("12345")
                .password("12345")
                .name("name2")
                .nickname("nick2")
                .birth("2345")
                .build();
        userService.register(dto2);

        Pageable pageable = PageRequest.of(0,2);
        //닉네임으로 검색
        Page<ResponseUser.UserSearch> list1 = userService.getUserSearch("user","nick2", pageable);
        for (ResponseUser.UserSearch searchlist1 : list1){
            System.out.println(searchlist1.getNickname()+"  "+ searchlist1.getBirth()+"  "+searchlist1.getMbti());
        }

        //MBTI로 검색
        Page<ResponseUser.UserSearch> list2 = userService.getUserSearch("mbti","estj",pageable);
        for (ResponseUser.UserSearch searchlist2 : list2){
            System.out.println(searchlist2.getNickname()+"  "+ searchlist2.getBirth()+"  "+searchlist2.getMbti());
        }

    }
}
