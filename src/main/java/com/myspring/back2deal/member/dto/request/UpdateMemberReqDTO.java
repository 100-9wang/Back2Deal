package com.myspring.back2deal.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberReqDTO {

	private String email;
	private String name;
	private String tel;
}
