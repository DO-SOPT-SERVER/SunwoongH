package org.sopt.api.post.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.sopt.domain.post.domain.Category;
import org.sopt.domain.post.domain.Post;

@Builder(access = AccessLevel.PRIVATE)
public record PostGetResponse(
        String title,
        String postContent,
        String imageUrl,
        String categoryContent
) {
    public static PostGetResponse of(Post post) {
        Category category = post.getCategory();
        return builder()
                .title(post.getTitle())
                .postContent(post.getContent())
                .imageUrl(post.getImageUrl())
                .categoryContent(category.getContent())
                .build();
    }
}
