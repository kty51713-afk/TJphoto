package com.example.tjphoto.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.tjphoto.mapper.MemberMapper;
import com.example.tjphoto.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. DB에서 회원 조회
        MemberVO member = memberMapper.findByUserName(username);
        
        // 2. 일치하는 아이디가 없으면 시큐리티 예외 던지기
        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다: " + username);
        }

        System.out.println("고객id 비밀번호 확인 : " + member.getPassword());
        System.out.println("고객 권한 확인 : " + member.getRole());

        // 안전하게 기본 권한 방어 코드 추가
        String userRole = member.getRole();
        if (userRole == null || userRole.isEmpty()) {
            userRole = "USER";
        }

        // 3. roles() 대신 authorities()를 사용하여 시큐리티 권한 규격 맞추기
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities("ROLE_" + userRole) // 스프링 시큐리티 표준인 ROLE_ 접두사를 수동으로 결합
                .build();
    }

}
