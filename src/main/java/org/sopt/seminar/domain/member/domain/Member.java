package org.sopt.seminar.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.sopt.seminar.global.common.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String nickname;
    private Integer age;
    @Embedded
    private Sopt sopt;

    public static Member createMember(final String name, final String nickname, final Integer age, final Sopt sopt) {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .age(age)
                .sopt(sopt)
                .build();
    }

    public void updateSopt(Sopt sopt) {
        this.sopt = sopt;
    }
}
