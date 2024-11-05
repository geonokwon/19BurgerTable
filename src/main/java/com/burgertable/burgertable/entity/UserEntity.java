package com.burgertable.burgertable.entity;

import com.burgertable.burgertable.domain.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    //유저 아이디
    @Column(nullable = false, unique = true)
    private String userId;

    //유저 비밀번호
    @Column(nullable = false)
    private String userPass;

    //유저 이름
    @Column(nullable = false)
    private String name;

    //전화번호
    @Column(nullable = false, unique = true)
    private String userPhone;

    //유저 권한
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    //SalesEntity 와의 OneToMany 관계
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private List<SalesEntity> salesList;



}

