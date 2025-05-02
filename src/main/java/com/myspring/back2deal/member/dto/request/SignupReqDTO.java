package com.myspring.back2deal.member.dto.request;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupReqDTO {
    private String memId;
    private String email;
    private String pwd;
    private String name;
    private String tel;
    private LocalDate birth;
}
