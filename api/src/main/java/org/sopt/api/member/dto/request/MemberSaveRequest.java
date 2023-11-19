package org.sopt.api.member.dto.request;

import org.sopt.domain.member.domain.Sopt;

public record MemberSaveRequest(
        String name,
        String nickname,
        int age,
        Sopt sopt) {
}
