package com.techforimpact.forfour.global.auth.jwt.service

import com.techforimpact.forfour.domain.member.entity.Role
import com.techforimpact.forfour.global.auth.jwt.JwtTokenType
import com.techforimpact.forfour.global.auth.jwt.dto.JwtTokenClaimsDto
import com.techforimpact.forfour.global.auth.jwt.dto.JwtTokenResponseDto
import com.techforimpact.forfour.global.auth.jwt.exception.NotFoundJwtTokenException
import com.techforimpact.forfour.global.auth.jwt.utils.JwtExtractor
import com.techforimpact.forfour.global.auth.jwt.utils.JwtProvider
import com.techforimpact.forfour.global.auth.jwt.utils.JwtValidator
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class JwtService(
    private val jwtProvider: JwtProvider,
    private val jwtExtractor: JwtExtractor,
    private val jwtValidator: JwtValidator
) {

    fun generateJwtToken(memberId: Long, role: Role): JwtTokenResponseDto {
        val accessToken =
            jwtProvider.generateToken(JwtTokenType.ACCESS_TOKEN, memberId, role.name)

        return JwtTokenResponseDto(accessToken)
    }

    fun extractJwtToken(request: HttpServletRequest): String {
        return jwtExtractor.extractJwtToken(request) ?: throw NotFoundJwtTokenException()
    }

    fun isValidJwtToken(jwtToken: String) {
        jwtValidator.verifyAccessToken(jwtToken)
    }

    fun extractJwtToken(token: String): JwtTokenClaimsDto {
        val id = jwtExtractor.getId(token)
        val role = jwtExtractor.getRole(token)
        return JwtTokenClaimsDto(id, Role.valueOf(role))
    }
    
}