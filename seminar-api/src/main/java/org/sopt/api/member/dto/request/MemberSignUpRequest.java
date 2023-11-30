package org.sopt.api.member.dto.request;

import org.sopt.domain.member.domain.Sopt;

public record MemberSignUpRequest(
        String name,
        String nickname,
        String password,
        int age,
        Sopt sopt) {
}
