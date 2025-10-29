package com.techforimpact.forfour.global.auth.jwt.dto

import com.techforimpact.forfour.domain.member.entity.Role

data class JwtTokenClaimsDto(
    val memberId: Long,
    val role: Role
)