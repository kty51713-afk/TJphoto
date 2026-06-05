package com.example.tjphoto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. 페이지별 접근 권한 설정
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login", "/join", "/css/**", "/js/**").permitAll() // 메인, 로그인, 가입, CSS는 로그인 없이 허용
                .anyRequest().authenticated() // 그 외 사진첩 같은 곳은 로그인 필요
            )
            // 2. 로그인 설정 (우리가 만든 화면으로 교체)
            .formLogin(form -> form
                .loginPage("/login") // 로그인 주소를 /login으로 지정
                .loginProcessingUrl("/login-process") // 로그인 제출 주소
                .defaultSuccessUrl("/", true) // 로그인 성공 시 메인으로 이동
                .permitAll()
            )
            // 3. 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
}
