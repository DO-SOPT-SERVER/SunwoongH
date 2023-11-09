package org.sopt.api.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.Sopt;

@Builder(access = AccessLevel.PRIVATE)
public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        Sopt sopt
) {
    public static MemberGetResponse of(Member member) {
        return builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .age(member.getAge())
                .sopt(member.getSopt())
                .build();
    }
}
