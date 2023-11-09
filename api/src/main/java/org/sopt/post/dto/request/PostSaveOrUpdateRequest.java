package org.sopt.post.dto.request;

public record PostSaveOrUpdateRequest(
        String title,
        String postContent,
        String categoryContent
) {
}
