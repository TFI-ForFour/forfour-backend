package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomJwtUnknownException : BaseException(
    JwtErrorMessage.CAUSE_UNKNOWN.status,
    JwtErrorMessage.CAUSE_UNKNOWN.message
)