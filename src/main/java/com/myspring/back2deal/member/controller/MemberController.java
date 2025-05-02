package com.myspring.back2deal.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.back2deal.member.dto.request.KakaoSignupReqDTO;
import com.myspring.back2deal.member.dto.request.LoginReqDTO;
import com.myspring.back2deal.member.dto.request.SignupReqDTO;
import com.myspring.back2deal.member.dto.request.UpdateMemberReqDTO;
import com.myspring.back2deal.member.dto.response.MemberResDTO;
import com.myspring.back2deal.member.service.MemberService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/members")
public class MemberController {

	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupReqDTO dto) {
	    System.out.println("email = " + dto.getEmail());
	    memberService.signup(dto);
	    return ResponseEntity.ok("회원가입 성공");
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<MemberResDTO> login(@RequestBody LoginReqDTO dto) {
		System.out.println("----------로그인 요청 들어왔음------------");
		System.out.println("dto == " + dto.getMemId() + ", " + dto.getPwd());
	    MemberResDTO result = memberService.login(dto);
	    return ResponseEntity.ok(result);
	}
	
	// 소셜 로그인 Kakao
	@PostMapping("/kakao/signup")
    public ResponseEntity<MemberResDTO> kakaoSignup(@RequestBody KakaoSignupReqDTO dto) {
        MemberResDTO result = memberService.kakaoSignup(dto);
        return ResponseEntity.ok(result);
    }
	
	// 회원정보 조회
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberResDTO> getMember(@PathVariable Long memberId) {
	    MemberResDTO result = memberService.getMember(memberId);
	    return ResponseEntity.ok(result);
	}
	
	// 회원정보 수정
	@PutMapping("/{memberId}")
	public ResponseEntity<MemberResDTO> updateMember(
	        @PathVariable Long memberId,
	        @RequestBody UpdateMemberReqDTO dto) {

	    MemberResDTO result = memberService.updateMember(memberId, dto);
	    return ResponseEntity.ok(result);
	}
	
	// 회원탈퇴
	@PutMapping("/{memberId}/delete")
	public ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
	    memberService.deleteMember(memberId);
	    return ResponseEntity.ok("회원 탈퇴 처리 완료");
	}

	

}
