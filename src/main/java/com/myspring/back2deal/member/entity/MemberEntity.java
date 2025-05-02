package com.myspring.back2deal.member.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "mem_id", nullable = false, unique = true, length = 50)
    private String memId;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "pwd", nullable = false, length = 100)
    private String pwd;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "tel", nullable = false, length = 20)
    private String tel;
    
    @Column(name = "social_id", length = 100)
    private String socialId;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "grade", length = 20)
    private String grade;

    @Column(name = "email_sts", length = 1)
    private String emailSts;

    @Column(name = "tel_sts", length = 1)
    private String telSts;

    @Column(name = "del_yn", length = 1)
    private String delYn;

    @Column(name = "del_date")
    private LocalDate delDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.grade = this.grade == null ? "normal" : this.grade;
        this.emailSts = this.emailSts == null ? "n" : this.emailSts;
        this.telSts = this.telSts == null ? "n" : this.telSts;
        this.delYn = this.delYn == null ? "n" : this.delYn;
        this.createdAt = LocalDateTime.now();
    }
    
    //회원정보 수정 메소드
    public void updateInfo(String email, String name, String tel) {
        this.email = email;
        this.name = name;
        this.tel = tel;
    }

    //회원탈퇴
    public void delete() {
        this.delYn = "y";
        this.delDate = LocalDate.now();
    }

}
