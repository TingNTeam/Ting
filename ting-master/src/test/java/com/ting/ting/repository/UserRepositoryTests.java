//package com.ting.ting.repository;
//
//import com.ting.ting.entity.User;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//@ActiveProfiles("local")
//public class UserRepositoryTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @DisplayName("H2-JPA 연동 테스트")
//    @Test
//    @Transactional
//    public void saveUserTest() {
//        //given
//        User user = User.builder()
//                .email("user")
//                .password("1234")
//                .name("narahim")
//                .birth("123")
//                .nickname("haha")
//                .build();
//
//        user = userRepository.save(user);
//        //when
//        User findUser = userRepository.getById(user.getId());
//        //then
//
//        assertEquals("user", findUser.getEmail());
//        assertEquals("1234", findUser.getPassword());
//    }
//}