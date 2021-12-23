package com.ting.ting.web.dto;

import com.ting.ting.core.type.MBTIType;
import com.ting.ting.entity.User;
import com.ting.ting.provider.service.UserService;
import lombok.Builder;
import lombok.Data;

public class ResponseUser {

    @Builder
    @Data
    public static class Login {
        private String accessToken;
        private String refreshToken;
    }

    @Builder
    @Data
    public static class UserSearch {
        private String nickname;
        private MBTIType mbti;
        private String birth;

        public static UserSearch of(User user){
            return UserSearch.builder()
                    .nickname(user.getNickname())
                    .mbti(user.getMbti())
                    .birth(user.getBirth())
                    .build();
        }
    }

    @Builder
    @Data
    public static class MyinfoList {
        private String email;
        private String name;
        private String nickname;
        private MBTIType mbti;
        private String birth;
    }

}
