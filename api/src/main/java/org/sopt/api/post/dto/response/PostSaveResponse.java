package org.sopt.api.post.dto.response;

import org.sopt.domain.post.domain.Post;

public record PostSaveResponse(
        Long postId
) {
    public static PostSaveResponse of(Post post) {
        return new PostSaveResponse(post.getId());
    }
}
