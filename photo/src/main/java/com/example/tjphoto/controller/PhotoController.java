package com.example.tjphoto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.tjphoto.service.PhotoService;
import com.example.tjphoto.vo.PhotoVO;





@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/photos") // Get 방식으로 요청 처리
    public String showUploagPage() {

        return "photo/photoUpload"; // templates 폴더에 있는 photoUpload.html을 찾아라...
    }

    @PostMapping("/photos/upload")
    public String uploadPhoto(
        PhotoVO photovo,
        @RequestParam("files") List<MultipartFile> files
    ) {
       try {
            // 3. 서비스야, 방금 클라이언트가 보낸 정보랑 파일들 줄 테니까 등록(파일저장+DB저장)해줘!
            photoService.registerPhoto(photovo, files);
        } catch (Exception e) {
            e.printStackTrace();
            // 에러가 나면 에러를 콘솔에 찍고, 일단 업로드 페이지에 머물게 합니다.
            return "photo/photoUpload"; 
        }

        // 4. 성공하면 사진 목록 페이지로 새로고침(이동) 시킵니다.
        return "redirect:/photo/list";
    }
    
    @GetMapping("/gallery")
    public String showListPage(Model model) {
        // 1. DB로부터 사진 게시글 전체 리스트를 호출하여 자바 변수에 저장
        List<PhotoVO> photoList = photoService.getPhotoList();
    
        // 2. 타임리프 HTML에서 사용할 수 있도록 "photoList"라는 식별자로 객체 바인딩
        model.addAttribute("photoList", photoList);
    
        // 3. src/main/resources/templates/photo/list.html 경로의 화면을 렌더링
        return "photo/list";
    }
    
    

}

