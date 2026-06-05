package com.example.tjphoto.vo;

import lombok.Data;

@Data // Getter, Setter, ToString 등을 롬복이 자동으로 생성해 줍니다.
public class MemberVO {
    private Long id;           // DB의 id (BIGINT)와 매핑, 가입 시에는 비어있음
    private String username;   // HTML의 name="username"과 매핑
    private String password;   // HTML의 name="password"과 매핑
    private String name;       // HTML의 name="name"과 매핑
    private String role;       // DB의 role과 매핑 (기본값 'USER'로 들어감)
}