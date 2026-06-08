package com.example.tjphoto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.tjphoto.vo.FileVO;

@Mapper
public interface FileMapper {
      // 2. 다중 파일 데이터 일괄 삽입 (자식)
    void insertFiles(FileVO fileVO);
}
