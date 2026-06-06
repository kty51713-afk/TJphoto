package com.example.tjphoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PhotoController {
    @GetMapping("/photos") // Get 방식으로 요청 처리
    public String showUploagPage() {

        return "photo/photoUpload"; // templates 폴더에 있는 photoUpload.html을 찾아라...
    }

    @PostMapping("/photos/upload")
    public String postMethodName(
        @ModelAttribute PhotoVO photovo,
        @RequestParam("files") MultipartFile{} files
    ) {
        
        
        return entity;
    }
    
    

}

