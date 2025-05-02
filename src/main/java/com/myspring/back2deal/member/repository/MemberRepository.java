package com.myspring.back2deal.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspring.back2deal.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	// 아이디로 회원 조회 (일반 로그인)
	MemberEntity findByMemId(String memId);
	
	// 이메일로 회원 조회 (소셜 로그인) Kakao
	MemberEntity findByEmail(String email);
	
	// 아이디 중복 체크
	boolean existsByMemId(String memId);
	
	// 이메일 중복 체크
	boolean existsByEmail(String email);
	
	// 소셜 로그인 Kakao
	Optional<MemberEntity> findBySocialId(String socialId);
}
