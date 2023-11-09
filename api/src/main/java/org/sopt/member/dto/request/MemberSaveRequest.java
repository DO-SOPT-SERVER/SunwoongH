package org.sopt.member.dto.request;

import org.sopt.member.domain.Sopt;

public record MemberSaveRequest(
        String name,
        String nickname,
        int age,
        Sopt sopt) {
}
