package org.sopt.api.post.dto.request;

public record PostSaveOrUpdateRequest(
        String title,
        String postContent,
        String categoryContent
) {
}
