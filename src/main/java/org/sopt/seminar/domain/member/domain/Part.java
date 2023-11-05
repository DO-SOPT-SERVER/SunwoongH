package org.sopt.seminar.domain.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Part {
    ANDROID("ANDROID"),
    iOS("iOS"),
    SERVER("SERVER"),
    DESIGN("DESIGN"),
    PLAN("PLAN");

    private final String name;
}
