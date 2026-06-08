package com.example.tjphoto.service;

import java.io.File;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.tjphoto.mapper.PhotoMapper;
import com.example.tjphoto.mapper.FileMapper;
import com.example.tjphoto.vo.PhotoVO;
import com.example.tjphoto.vo.FileVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor  
public class PhotoServiceImpl implements PhotoService {
   
    private final PhotoMapper photoMapper;
    private final FileMapper fileMapper; 

    @Override
    public void registerPhoto(PhotoVO photoVO, List<MultipartFile> files) throws Exception {
        
            // 1. title이 null이거나 비어있으면 기본값 주입
        if (photoVO.getTitle() == null || photoVO.getTitle().isEmpty()) {
            photoVO.setTitle("첨부파일 업로드");
        }

        // 2. [추가] content가 null이거나 비어있으면 기본값 주입 (이번 에러 해결 핵심!)
        if (photoVO.getContent() == null || photoVO.getContent().isEmpty()) {
            photoVO.setContent("등록된 내용이 없습니다."); 
        }

        // 3. memberId가 null이면 임시 값 주입
        if (photoVO.getMemberId() == null) {
            photoVO.setMemberId(1L); 
        }


        // 1. 게시글 본문을 DB에 선저장 (실행 후 XML 설정에 의해 photoVO 객체 내부 id 필드에 PK값이 채워짐)
        photoMapper.insertPhoto(photoVO);
        
        // 2. 전달된 파일들이 존재하는지 확인하는 로그 (디버깅용)
        if (files != null) {
            System.out.println("업로드된 파일 개수: " + files.size());
        }

        // 2. 외래키(FK)로 사용할 게시글 번호 추출
        Long photoId = photoVO.getId(); 
        
        // 파일을 물리적으로 저장할 서버 내 디렉토리 경로
        String uploadPath = "c:/upload";  
        
        // 3. 컨트롤러로부터 넘어온 파일 리스트 순회 처리
        for (MultipartFile file : files) {
            
            if (file.isEmpty()) {
                continue;
            }
            
            // 파일명 중복 방지 처리 (UUID 컴포지션)
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String savedFilename = uuid + "_" + originalFilename;
            
            // 컴퓨터 하드디스크에 파일 저장 실행
            File targetFile = new File(uploadPath, savedFilename);
            file.transferTo(targetFile);
            
            // 4. FileVO 인스턴스를 생성하여 확정된 필드명에 데이터 바인딩
            FileVO fileVO = new FileVO();
            fileVO.setPhotoId(photoId);                       
            fileVO.setOriginalName(originalFilename);         
            fileVO.setSavedName(savedFilename);               
            
            // 5. 파일 테이블 데이터 인서트 실행
            fileMapper.insertFiles(fileVO); 
        }
    }

    @Override
    public List<PhotoVO> getPhotoList() {
        return photoMapper.selectPhotoList();
    }
}