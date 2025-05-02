package com.myspring.back2deal.member.service;

import com.myspring.back2deal.member.dto.request.KakaoSignupReqDTO;
import com.myspring.back2deal.member.dto.request.LoginReqDTO;
import com.myspring.back2deal.member.dto.request.SignupReqDTO;
import com.myspring.back2deal.member.dto.response.MemberResDTO;
import com.myspring.back2deal.member.dto.request.UpdateMemberReqDTO;

public interface MemberService {
	
	// 회원 가입
	MemberResDTO signup (SignupReqDTO dto);
	
	// 로그인
	MemberResDTO login (LoginReqDTO dto);
	
	// 회원정보 조회
	MemberResDTO getMember(Long memberId);
	
	// 회원정보 수정
	MemberResDTO updateMember(Long memberId, UpdateMemberReqDTO dto);
	
	// 회원 탈퇴
	public void deleteMember(Long memberId);
	
	//소셜 로그인 Kakao
	MemberResDTO kakaoSignup(KakaoSignupReqDTO dto);
}
