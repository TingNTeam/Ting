package com.ting.ting.web.dto;

import com.ting.ting.core.type.MBTIType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class RequestUser {

    @Builder
    @Data
    public static class Login{
        @NotEmpty(message = "이메일을 입력하시오.")
        private String email;
        @NotEmpty(message = "비밀번호를 입력하시오.")
        private String password;
    }
}
