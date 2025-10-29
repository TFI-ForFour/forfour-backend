package com.techforimpact.forfour.global.auth.jwt.utils

import com.techforimpact.forfour.global.auth.constants.AUTH_HEADER
import com.techforimpact.forfour.global.auth.constants.AUTH_PREFIX
import com.techforimpact.forfour.global.auth.constants.ID_CLAIM
import com.techforimpact.forfour.global.auth.constants.ROLE_CLAIM
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import javax.crypto.SecretKey

@Component
class JwtExtractor(
    @Value("\${forfour.secretKey}")
    private val secretKey: String,
) {

    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun extractJwtToken(request: HttpServletRequest): String? {
        return request.getHeader(AUTH_HEADER)
            ?.takeIf { it.startsWith(AUTH_PREFIX) }
            ?.removePrefix(AUTH_PREFIX)
    }

    fun getId(token: String): Long {
        return getMemberIdClaim(token, ID_CLAIM)
    }

    fun getRole(token: String): String {
        return getStringClaim(token, ROLE_CLAIM)
    }

    private fun getMemberIdClaim(token: String, claimName: String): Long {
        val id = parseClaims(token).get(claimName, Number::class.java)
        return id.toLong()
    }

    private fun getStringClaim(token: String, claimName: String): String {
        val claims = parseClaims(token)
        return claims.get(claimName, String::class.java)
    }

    private fun parseClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(key as SecretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

}