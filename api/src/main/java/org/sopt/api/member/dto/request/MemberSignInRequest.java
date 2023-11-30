package org.sopt.api.member.dto.request;

public record MemberSignInRequest(
        String nickname,
        String password
) {
}
