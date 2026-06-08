package com.example.tjphoto.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.tjphoto.vo.PhotoVO;
import com.example.tjphoto.vo.FileVO;
import java.util.List;

@Mapper
public interface PhotoMapper {
    // 1. 게시글 데이터 삽입 (부모)
    int insertPhoto(PhotoVO photoVO);
    
    List<PhotoVO> selectPhotoList();
    
} 