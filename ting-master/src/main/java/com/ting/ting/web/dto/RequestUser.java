package com.ting.ting.web.dto;

import com.ting.ting.core.security.role.Role;
import com.ting.ting.core.type.MBTIType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

public class RequestUser {

    @Builder
    @Data
    public static class Register {
        @NotEmpty(message = "이메일이 비어있음")
        private String email;
        @NotEmpty(message = "비밀번호 입력이 되어있지 않음")
        private String password;
        @NotEmpty(message = "이름을 입력해주세요")
        private String name;
        @NotEmpty(message = "닉네임을 입력해주세요")
        private String nickname;
        @NotEmpty(message = "생년월일을 입력해주세요")
        private String birth;

        private MBTIType mbti;
    }

    @Builder
    @Data
    public static class Login{
        @NotEmpty(message = "이메일을 입력하시오.")
        private String email;
        @NotEmpty(message = "비밀번호를 입력하시오.")
        private String password;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateMbti{
        private MBTIType mbti;
    }

    @Builder
    @Data
    public static class myinfo{
        @NotEmpty(message = "이메일이 비어있음")
        private String email;
        @NotEmpty(message = "비밀번호 입력이 되어있지 않음")
        private String password;
        @NotEmpty(message = "이름을 입력해주세요")
        private String name;
        @NotEmpty(message = "닉네임을 입력해주세요")
        private String nickname;
        @NotEmpty(message = "생년월일을 입력해주세요")
        private String birth;
        private MBTIType mbti;
    }

}
