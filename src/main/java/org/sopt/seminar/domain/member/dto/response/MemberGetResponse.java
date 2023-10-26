package org.sopt.seminar.domain.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.seminar.domain.member.domain.Member;
import org.sopt.seminar.domain.member.domain.Sopt;

@Builder(access = AccessLevel.PRIVATE)
public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        Sopt sopt
) {
    public static MemberGetResponse of(Member member) {
        return MemberGetResponse.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .age(member.getAge())
                .sopt(member.getSopt())
                .build();
    }
}
