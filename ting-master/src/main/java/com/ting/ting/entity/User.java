package com.ting.ting.entity;

import com.ting.ting.core.security.role.Role;
import com.ting.ting.core.type.MBTIType;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Table(name="User")
@Entity
@Getter
@NoArgsConstructor
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

    @Column(name="birth")
    private String birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti")
    private MBTIType mbti;

    @Column(name="role")
    private Role role;

    @Builder
    public User(String email, String password, String salt, String name, String nickname, String birth, MBTIType mbti){
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.mbti = mbti;
    }

    public void changeRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
