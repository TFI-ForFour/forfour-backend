package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomWeakKeyException : BaseException(
    JwtErrorMessage.WEAK_KEY.status,
    JwtErrorMessage.WEAK_KEY.message)