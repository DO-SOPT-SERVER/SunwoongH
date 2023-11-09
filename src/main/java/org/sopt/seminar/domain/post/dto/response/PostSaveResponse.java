package org.sopt.seminar.domain.post.dto.response;

import org.sopt.seminar.domain.post.domain.Post;

public record PostSaveResponse(
        Long postId
) {
    public static PostSaveResponse of(Post post) {
        return new PostSaveResponse(post.getId());
    }
}
