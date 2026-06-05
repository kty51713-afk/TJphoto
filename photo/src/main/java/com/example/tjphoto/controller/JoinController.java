package com.example.tjphoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tjphoto.service.MemberService;
import com.example.tjphoto.vo.MemberVO;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class JoinController {
    private final MemberService memberservice;

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO membervo) {
        memberservice.join(membervo);
        return "redirect:/";
    }
    
}
