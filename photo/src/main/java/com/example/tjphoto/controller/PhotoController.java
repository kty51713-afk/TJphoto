package com.example.tjphoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PhotoController {
    @GetMapping("/photos") // Get 방식으로 요청 처리
    public String showUploagPage() {

        return "photo/photoUpload"; // templates 폴더에 있는 photoUpload.html을 찾아라...
    }
}
