package com.example.tjphoto.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.tjphoto.vo.MemberVO;

@Mapper
public interface MemberMapper {
    MemberVO findByUserName(String loginId);
    void insertMember(MemberVO membervo);
}
