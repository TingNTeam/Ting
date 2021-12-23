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

    @Autowired
    private UserRepository userRepository;

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
        userService.Updatembti(MBTIType.ESTJ, dto1.getEmail());

        RequestUser.Register dto2 = RequestUser.Register.builder()
                .email("12345")
                .password("12345")
                .name("name2")
                .nickname("nick2")
                .birth("2345")
                .build();
        userService.register(dto2);
        userService.Updatembti(MBTIType.ESTJ, dto2.getEmail());
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

    @Test
    @DisplayName("내정보 수정")
    @Transactional
    void updatemyinfoTest(){
        //회원 등록
        RequestUser.Register dto1 = RequestUser.Register.builder()
                .email("1234")
                .password("1234")
                .name("name1")
                .nickname("nick1")
                .birth("1234")
                .build();
        userService.register(dto1);
        //로그인
        RequestUser.Login dto2 = RequestUser.Login.builder()
                .email("1234")
                .password("1234").build();
        userService.login(dto2);

        User user = userRepository.findByEmail(dto1.getEmail());
        System.out.println("변경 전");
        System.out.println(user.getEmail()+"  "+user.getPassword()+"  "+user.getNickname());
        //정보 수정
        userService.updatemyinfo(dto1.getEmail(),"123456",null);

        User user1 = userRepository.findByEmail(dto1.getEmail());
        System.out.println("변경된 후");
        System.out.println(user1.getEmail()+"  "+user1.getPassword()+"  "+user1.getNickname());
    }
}
