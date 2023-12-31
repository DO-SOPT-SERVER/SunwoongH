package org.sopt.domain.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Short id;
    private String content;
    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    public static Category createCategory(final String content) {
        return Category.builder()
                .content(content)
                .build();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
