package org.sopt.post.dto.response;

import org.sopt.post.domain.Post;

public record PostSaveResponse(
        Long postId
) {
    public static PostSaveResponse of(Post post) {
        return new PostSaveResponse(post.getId());
    }
}
