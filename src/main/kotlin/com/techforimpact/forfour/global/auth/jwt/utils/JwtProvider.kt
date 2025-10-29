package com.techforimpact.forfour.global.auth.jwt.utils

import com.techforimpact.forfour.global.auth.constants.ID_CLAIM
import com.techforimpact.forfour.global.auth.constants.ROLE_CLAIM
import com.techforimpact.forfour.global.auth.jwt.JwtTokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtProvider(
    @Value("\${forfour.secretKey}")
    private val secretKey: String,
    @Value("\${forfour.accessTokenExpiration}")
    private val accessTokenExpiration: Long
) {

    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun generateToken(jwtTokenType: JwtTokenType, memberId: Long, role: String): String {
        return Jwts.builder()
            .claim(ID_CLAIM, memberId)
            .claim(ROLE_CLAIM, role)
            .subject(memberId.toString())
            .issuedAt(Date())
            .expiration(setExpireTime(jwtTokenType))
            .signWith(key)
            .compact()
    }

    private fun setExpireTime(jwtTokenType: JwtTokenType): Date {
        return when (jwtTokenType) {
            JwtTokenType.ACCESS_TOKEN -> Date(System.currentTimeMillis() + accessTokenExpiration)
        }
    }

}