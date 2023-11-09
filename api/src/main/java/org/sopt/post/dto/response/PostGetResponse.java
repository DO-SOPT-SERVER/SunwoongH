package org.sopt.post.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;

@Builder(access = AccessLevel.PRIVATE)
public record PostGetResponse(
        String title,
        String postContent,
        String categoryContent
) {
    public static PostGetResponse of(Post post) {
        Category category = post.getCategory();
        return builder()
                .title(post.getTitle())
                .postContent(post.getContent())
                .categoryContent(category.getContent())
                .build();
    }
}
