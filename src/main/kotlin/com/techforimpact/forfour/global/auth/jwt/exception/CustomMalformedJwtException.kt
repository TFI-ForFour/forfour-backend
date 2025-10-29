package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomMalformedJwtException : BaseException(
    JwtErrorMessage.MALFORMED_JWT.status,
    JwtErrorMessage.MALFORMED_JWT.message
)