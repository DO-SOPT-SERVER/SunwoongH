package org.sopt.seminar.domain.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class Sopt {
    private Integer generation;
    @Enumerated(EnumType.STRING)
    private Part part;

    public static Sopt createSopt(final int generation, final Part part) {
        return Sopt.builder()
                .generation(generation)
                .part(part)
                .build();
    }
}