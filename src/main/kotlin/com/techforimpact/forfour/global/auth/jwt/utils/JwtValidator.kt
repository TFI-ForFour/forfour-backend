package com.techforimpact.forfour.global.auth.jwt.utils

import com.techforimpact.forfour.global.auth.jwt.exception.*
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.WeakKeyException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import javax.crypto.SecretKey

@Component
class JwtValidator(
    @Value("\${forfour.secretKey}")
    private val secretKey: String,
) {

    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun verifyAccessToken(token: String) {
        try {
            Jwts.parser()
                .verifyWith(key as SecretKey)
                .build()
                .parseClaimsJws(token)
                .payload
        } catch (e: ExpiredJwtException) {
            throw CustomExpiredJwtException()
        } catch (e: UnsupportedJwtException) {
            throw CustomUnsupportedJwtException()
        } catch (e: MalformedJwtException) {
            throw CustomMalformedJwtException()
        } catch (e: SignatureException) {
            throw CustomSignatureException()
        } catch (e: IllegalArgumentException) {
            throw CustomIllegalArgumentException()
        } catch (e: WeakKeyException) {
            throw CustomWeakKeyException()
        } catch (e: Exception) {
            throw CustomJwtUnknownException()
        }
    }

}