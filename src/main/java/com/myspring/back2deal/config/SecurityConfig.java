package com.myspring.back2deal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()               // csrf 비활성화
            .formLogin().disable()          // 기본 로그인 폼 비활성화
            .httpBasic().disable()          // HTTP Basic 인증 비활성화
            .authorizeHttpRequests()
                .requestMatchers("/api/members/login", "/api/members/signup", "/api/members/kakao/**").permitAll() // 로그인, 회원가입 API는 인증 예외
                .anyRequest().authenticated();   // 나머지는 인증 필요

        return http.build();
    }
}
