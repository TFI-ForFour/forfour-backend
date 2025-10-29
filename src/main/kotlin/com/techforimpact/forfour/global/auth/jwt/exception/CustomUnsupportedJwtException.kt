package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomUnsupportedJwtException() : BaseException(
    JwtErrorMessage.UN_SUPPORTED_JWT.status,
    JwtErrorMessage.UN_SUPPORTED_JWT.message
)