package org.sopt.member.dto.response;

import org.sopt.member.domain.Member;

public record MemberSaveResponse(
        Long memberId
) {
    public static MemberSaveResponse of(Member member) {
        return new MemberSaveResponse(member.getId());
    }
}
