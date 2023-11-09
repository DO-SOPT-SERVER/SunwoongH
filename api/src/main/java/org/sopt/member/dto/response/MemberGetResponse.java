package org.sopt.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.member.domain.Member;
import org.sopt.member.domain.Sopt;

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
