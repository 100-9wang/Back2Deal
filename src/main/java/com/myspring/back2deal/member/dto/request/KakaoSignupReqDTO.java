package com.myspring.back2deal.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSignupReqDTO {
    private String socialId;      // 카카오 고유 ID
    private String email;
    private String nickname;
    private String phoneNumber;
    private String birth;         // "yyyy-MM-dd" 형태
}
