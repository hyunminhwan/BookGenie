package com.hmh.moviegenie.backend.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException; // jjwt 사용 시
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtHttpOnlyFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JwtHttpOnlyFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = movieCookie(request);

        if (token != null) {
            try {
                // 만료되지 않았으면 SecurityContext에 인증 정보 저장
                if (!jwtUtil.isExpired(token)) {
                    String userId = jwtUtil.getUsername(token);
                    // 여기서 권한 정보를 넣고 싶다면 DB 조회나 토큰 payload에서 roles 추출 가능
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userId, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                // 만료되었으면 => doFilter로 통과하지만 authentication은 세팅 안 함
                // (Security 설정에서 인증 필요한 경로는 401/403 처리될 것임)
            } catch (ExpiredJwtException ex) {
                // 토큰 파싱 중 만료 예외
                // 로그 찍고 그냥 넘기는 예시
                // => SecurityContext에 아무것도 세팅 안 된 상태로 다음 필터 진행
                System.out.println("토큰만료: " + ex.getMessage());
            } catch (Exception ex) {
                // 토큰이 위/변조되었거나 파싱 에러 등
                System.out.println("토큰오류: " + ex.getMessage());
            }
        }

        // 인증이 있든 없든, 남은 필터들 & 컨트롤러 로직 진행
        filterChain.doFilter(request, response);
    }

    /**
     * 쿠키에서 "authToken" 값 추출
     */
    private String movieCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("authToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
