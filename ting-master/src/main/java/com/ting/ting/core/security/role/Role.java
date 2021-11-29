package com.ting.ting.core.security.role;

import lombok.Getter;

import java.util.Arrays;

//@Getter 썼으니까 getCode(), getDescription() 따로 작성 안해도 됨
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN","관리자권한"),
    USER("ROLE_USER","사용자권한"),
    UNKNOWN("UNKNOWN","알수없는 권한");

    private String code;
    private String description;

    Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

//    public static Role of(String code) {
//        return Arrays.stream(Role.values())
//                .filter(r -> r.getCode().equals(code))
//                .findAny()
//                .orElse(UNKNOWN);
//    }
}
