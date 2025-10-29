package com.techforimpact.forfour.global.auth.jwt.exception

import com.techforimpact.forfour.global.common.exception.BaseException

class CustomSignatureException : BaseException(
    JwtErrorMessage.SIGNATURE_JWT.status,
    JwtErrorMessage.SIGNATURE_JWT.message
)