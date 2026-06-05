package com.example.tjphoto.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tjphoto.mapper.MemberMapper;
import com.example.tjphoto.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper membermapper;
    private final PasswordEncoder passwordencoder; 

    @Override
    public void join(MemberVO membervo) {
        //DB저장할 때 비밀번호 암호화해버리기
        String encodedPassword = passwordencoder.encode(membervo.getPassword());
        membervo.setPassword(encodedPassword);
        membermapper.insertMember(membervo);
    }

    

}
