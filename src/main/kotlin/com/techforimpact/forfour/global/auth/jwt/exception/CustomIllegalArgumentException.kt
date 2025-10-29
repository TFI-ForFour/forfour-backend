package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomIllegalArgumentException: BaseException(
    JwtErrorMessage.ILLEGAL_ARGUMENT.status,
    JwtErrorMessage.ILLEGAL_ARGUMENT.message
)