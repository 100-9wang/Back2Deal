package com.myspring.back2deal.member.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.back2deal.jwt.JwtTokenProvider;
import com.myspring.back2deal.member.dto.request.KakaoSignupReqDTO;
import com.myspring.back2deal.member.dto.request.LoginReqDTO;
import com.myspring.back2deal.member.dto.request.SignupReqDTO;
import com.myspring.back2deal.member.dto.request.UpdateMemberReqDTO;
import com.myspring.back2deal.member.dto.response.MemberResDTO;
import com.myspring.back2deal.member.entity.MemberEntity;
import com.myspring.back2deal.member.repository.MemberRepository;
import com.myspring.back2deal.member.service.MemberService;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    // 회원가입
    @Override
    public MemberResDTO signup(SignupReqDTO dto) {

        if (memberRepository.existsByMemId(dto.getMemId())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        MemberEntity member = MemberEntity.builder()
                .memId(dto.getMemId())
                .email(dto.getEmail())
                .pwd(passwordEncoder.encode(dto.getPwd()))
                .name(dto.getName())
                .tel(dto.getTel())
                .birth(dto.getBirth())
                .build();

        memberRepository.save(member);

        return MemberResDTO.fromEntity(member);
    }



    // 로그인
    @Override
    public MemberResDTO login(LoginReqDTO dto) {

        // 아이디 존재 확인
        MemberEntity member = memberRepository.findByMemId(dto.getMemId());

        if (member == null) {
        	System.out.println("존재하지 않음");
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        // 비밀번호 일치 여부 체크
        if (!passwordEncoder.matches(dto.getPwd(), member.getPwd())) {
        	System.out.println("비밀번호 틀림");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        
        // 탈퇴 여부 체크 (y == 로그인 불가)
        if ("y".equals(member.getDelYn())) {
        	throw new IllegalArgumentException("회원탈퇴 처리 된 아이디 입니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(member.getMemId());
        
        return MemberResDTO.fromEntityWithToken(member, accessToken);
    }

    // 회원정보 조회
    @Override
    public MemberResDTO getMember(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return MemberResDTO.fromEntity(member);
    }
    
    
    // 회원정보 수정
    @Override
    public MemberResDTO updateMember(Long memberId, UpdateMemberReqDTO dto) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.updateInfo(dto.getEmail(), dto.getName(), dto.getTel());

        return MemberResDTO.fromEntity(member);
    }

    //회원탈퇴
    @Override
    public void deleteMember(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.delete();  // 탈퇴 처리 메서드 호출
    }
    
    // 소셜 로그인 Kakao
    @Override
    public MemberResDTO kakaoSignup(KakaoSignupReqDTO dto) {

        String memId = "kakao_" + dto.getSocialId();

        Optional<MemberEntity> findMember = memberRepository.findBySocialId(dto.getSocialId());

        MemberEntity member = findMember.orElseGet(() -> 
            memberRepository.save(MemberEntity.builder()
                .memId(memId)
                .socialId(dto.getSocialId())
                .email(dto.getEmail())
                .pwd(passwordEncoder.encode(UUID.randomUUID().toString()))
                .name(dto.getNickname())
                .tel(dto.getPhoneNumber())
                .birth(LocalDate.parse(dto.getBirth()))
                .build()
            )
        );

        return MemberResDTO.fromEntity(member);
    }
}
