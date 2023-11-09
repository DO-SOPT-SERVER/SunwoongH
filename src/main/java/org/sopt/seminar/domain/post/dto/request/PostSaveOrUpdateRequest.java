package org.sopt.seminar.domain.post.dto.request;

public record PostSaveOrUpdateRequest(
        String title,
        String postContent,
        String categoryContent
) {
}
