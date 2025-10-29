package com.techforimpact.forfour.global.auth.jwt.exception

import org.springframework.http.HttpStatus

enum class JwtErrorMessage(
    val status: HttpStatus,
    val message: String
) {
    UN_AUTHORIZED(HttpStatus.FORBIDDEN, "잘못된 권한 요청입니다."),
    NOT_EXIST_TOKEN(HttpStatus.UNAUTHORIZED, "JWT 토큰이 존재하지 않습니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    UN_SUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    MALFORMED_JWT(HttpStatus.UNAUTHORIZED, "잘못된 형식의 JWT 토큰입니다."),
    SIGNATURE_JWT(HttpStatus.UNAUTHORIZED, "JWT 서명 검증에 실패했습니다."),
    ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
    WEAK_KEY(HttpStatus.UNAUTHORIZED, "JWT 서명 KEY가 너무 약합니다."),
    CAUSE_UNKNOWN(HttpStatus.UNAUTHORIZED, "원인 미상의 오류가 발생했습니다."),

    NOT_FOUND_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "요청 헤더로부터 JWT 토큰을 찾을 수 없습니다.");
}