package com.myspring.back2deal.jwt;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshId;

    @Column(nullable = false, length = 50)
    private String memId;

    @Column(nullable = false, length = 255)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    public void updateToken(String token, LocalDateTime newExpire) {
        this.refreshToken = token;
        this.expireDate = newExpire;
    }
}
