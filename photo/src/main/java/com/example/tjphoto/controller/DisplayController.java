package com.example.tjphoto.controller;

import java.io.File;
import java.nio.file.Files;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;

@RestController
public class DisplayController {

    @Value("${file.upload-path}")
    private String uploadPath;

    // 2. HTML의 th:src="@{/display(filename=...)}" 요청을 정밀 매핑합니다.
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(@RequestParam("filename") String filename) {
        
        // 지정된 경로와 파일명을 결합하여 파일 객체를 생성합니다.
        File file = new File(uploadPath + filename);
        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            
            // 파일의 확장자를 분석하여 브라우저에게 이미지 종류(MIME 타입: image/png, image/jpeg 등)를 알려줍니다.
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            
            // 하드디스크의 물리 파일을 바이트 배열로 복사하여 HTTP 바디에 담아 브라우저로 전송합니다.
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            // 파일이 없거나 읽기 오류 발생 시 500 에러 코드를 반환합니다.
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return result;
    }
}