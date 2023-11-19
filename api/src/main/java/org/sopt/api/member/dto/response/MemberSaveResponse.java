package org.sopt.api.member.dto.response;

import org.sopt.domain.member.domain.Member;

public record MemberSaveResponse(
        Long memberId
) {
    public static MemberSaveResponse of(Member member) {
        return new MemberSaveResponse(member.getId());
    }
}
