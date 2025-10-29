package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomExpiredJwtException : BaseException(
    JwtErrorMessage.EXPIRED_JWT.status,
    JwtErrorMessage.EXPIRED_JWT.message
)