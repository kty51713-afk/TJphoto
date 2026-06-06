package com.example.tjphoto.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhotoVO {
    private Long id;        // MySQL BIGINT NOT NULL AUTO_INCREMENT -> Long으로 변경
    private String title;   // VARCHAR(100) -> String 유지
    private String content; // TEXT -> String 유지
    private Long memberId;  // MySQL BIGINT -> Long으로 변경 및 카멜케이스 적용
    private LocalDateTime cratedAt;

}
