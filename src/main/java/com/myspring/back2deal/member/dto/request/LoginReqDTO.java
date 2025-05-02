package com.myspring.back2deal.member.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDTO {
    private String memId;
    private String pwd;
}
