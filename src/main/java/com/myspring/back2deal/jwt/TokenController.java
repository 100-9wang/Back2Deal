package com.myspring.back2deal.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * AccessToken 재발급 API
     * 클라이언트가 보낸 RefreshToken을 검증 후 AccessToken 발급
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Authorization") String refreshToken) {

        // Bearer 제거
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        // 1. RefreshToken 유효한지 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body("유효하지 않은 RefreshToken입니다.");
        }

        // 2. 토큰에서 memId 추출
        String memId = jwtTokenProvider.getMemIdFromToken(refreshToken);

        // 3. DB에서 저장된 RefreshToken과 비교
        RefreshTokenEntity savedToken = refreshTokenRepository.findByMemId(memId)
                .orElseThrow(() -> new IllegalArgumentException("RefreshToken이 존재하지 않습니다."));

        if (!savedToken.getRefreshToken().equals(refreshToken)) {
            return ResponseEntity.status(401).body("RefreshToken이 일치하지 않습니다.");
        }

        // 4. 만료시간도 확인
        if (savedToken.getExpireDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(401).body("RefreshToken이 만료되었습니다. 재로그인 해주세요.");
        }

        // 5. 새 AccessToken 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(memId);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}
