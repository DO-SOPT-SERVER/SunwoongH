package org.sopt.seminar.domain.post.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.seminar.domain.post.domain.Category;
import org.sopt.seminar.domain.post.domain.Post;

@Builder(access = AccessLevel.PRIVATE)
public record PostGetResponse(
        String title,
        String postContent,
        String categoryContent
) {
    public static PostGetResponse of(Post post) {
        Category category = post.getCategory();
        return PostGetResponse.builder()
                .title(post.getTitle())
                .postContent(post.getContent())
                .categoryContent(category.getContent())
                .build();
    }
}
