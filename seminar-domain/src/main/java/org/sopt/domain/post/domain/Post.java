package org.sopt.domain.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.common.BaseTimeEntity;
import org.sopt.domain.member.domain.Member;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Post createPost(final String title, final String content, final String imageUrl, final Category category, final Member member) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
        post.changeMember(member);
        post.changeCategory(category);
        return post;
    }

    public void changeMember(Member member) {
        if (this.member != null) {
            this.member.removePost(this);
        }
        this.member = member;
        member.addPost(this);
    }

    public void changeCategory(Category category) {
        if (this.category != null) {
            this.category.removePost(this);
        }
        this.category = category;
        category.addPost(this);
    }

    public void updateTitleAndContent(String title, String content) {
        updateTitle(title);
        updateContent(content);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}