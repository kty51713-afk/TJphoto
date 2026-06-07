package com.example.tjphoto.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.tjphoto.vo.PhotoVO;

public interface PhotoService {
    
    void registerPhoto(PhotoVO photoVO, List<MultipartFile> files) throws Exception;

    List<PhotoVO> getPhotoList();
}
