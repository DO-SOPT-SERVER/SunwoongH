package org.sopt.seminar.domain.member.dto.response;

import org.sopt.seminar.domain.member.domain.Member;

public record MemberSaveResponse(
        Long memberId
) {
    public static MemberSaveResponse of(Member member) {
        return new MemberSaveResponse(member.getId());
    }
}
