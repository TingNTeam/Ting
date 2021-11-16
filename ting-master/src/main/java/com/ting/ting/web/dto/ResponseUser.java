package com.ting.ting.web.dto;

import lombok.Builder;
import lombok.Data;

public class ResponseUser {

    @Builder
    @Data
    public static class Login {
        private String accessToken;
        private String refreshToken;
    }
}
