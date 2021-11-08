package com.ting.ting.entity;

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

    @Column(name="school", length =20)
    private String school;  //학교
    @Column(name="major", length =20)
    private String major;   //전공
    @Column(name="userEmail", length =30)
    private String userEmail;   //학교 이메일
    @Column(name="student_number", length =20)
    private Long student_number; //학번
    @Column(name="name", length =20)
    private String name;    //이름
    @Column(name="gender", length =4)
    private String gender;  //성별
    @Column(name="birth", length =20)
    private Long birth;     //생년월일
    @Column(name="username", length =20)
    private String username;    //닉네임
    @Column(name="password", length =20)
    private String password;    //비밀번호
    @Column(name="profile", length =20)
    private String profile;     //자기소개

    @Column(name="enabled", length=2)
    @NotNull
    private Boolean enabled;    //휴면여부


}
