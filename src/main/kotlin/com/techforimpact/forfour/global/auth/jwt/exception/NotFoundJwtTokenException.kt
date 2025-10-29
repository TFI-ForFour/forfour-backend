package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class NotFoundJwtTokenException(): BaseException(
    JwtErrorMessage.NOT_FOUND_JWT_TOKEN.status,
    JwtErrorMessage.NOT_FOUND_JWT_TOKEN.message
)