package com.myspring.back2deal.member.dto.response;

import com.myspring.back2deal.member.entity.MemberEntity;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResDTO {
    private Long memberId;
    private String memId;
    private String email;
    private String name;
    private String tel;
    private LocalDate birth;
    private String grade;
    private String emailSts;
    private String telSts;
    private String delYn;
    private LocalDateTime createdAt;
    private String accessToken;

    public static MemberResDTO fromEntity(MemberEntity member) {
        return MemberResDTO.builder()
                .memberId(member.getMemberId())
                .memId(member.getMemId())
                .email(member.getEmail())
                .name(member.getName())
                .tel(member.getTel())
                .birth(member.getBirth())
                .grade(member.getGrade())
                .emailSts(member.getEmailSts())
                .telSts(member.getTelSts())
                .delYn(member.getDelYn())
                .createdAt(member.getCreatedAt())
                .build();
    }
    
    public static MemberResDTO fromEntityWithToken(MemberEntity member, String accessToken) {
        return MemberResDTO.builder()
                .memberId(member.getMemberId())
                .memId(member.getMemId())
                .email(member.getEmail())
                .name(member.getName())
                .tel(member.getTel())
                .birth(member.getBirth())
                .grade(member.getGrade())
                .emailSts(member.getEmailSts())
                .telSts(member.getTelSts())
                .delYn(member.getDelYn())
                .createdAt(member.getCreatedAt())
                .accessToken(accessToken)
                .build();
    }
}
