package com.ting.ting.entity;

import com.ting.ting.core.type.MBTIType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="salt")
    private String salt;

    @Column(name="refreshToken")
    private String refreshToken;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name="age")
    private String age;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti")
    private MBTIType mbti;

    @Builder
    public User(String email, String password, String salt) {
        this.email = email;
        this.password = password;
        this.salt = salt;
    }
}
