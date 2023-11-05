package org.sopt.seminar.domain.member.dto.request;

import org.sopt.seminar.domain.member.domain.Sopt;

public record MemberSaveRequest(
        String name,
        String nickname,
        int age,
        Sopt sopt) {
}
