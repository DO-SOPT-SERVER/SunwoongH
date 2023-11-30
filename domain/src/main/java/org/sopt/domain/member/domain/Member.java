package org.sopt.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.sopt.domain.common.BaseTimeEntity;
import org.sopt.domain.post.domain.Post;

import java.util.ArrayList;
import java.util.List;

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
    private String password;
    private Integer age;
    @Embedded
    private Sopt sopt;
    private String refreshToken;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    public static Member createMember(final String name, final String nickname, final String password, final Integer age, final Sopt sopt) {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .age(age)
                .sopt(sopt)
                .build();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void updateSopt(Sopt sopt) {
        this.sopt = sopt;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
