package org.sopt.api.member.dto.request;

import org.sopt.domain.member.domain.Part;

public record MemberUpdateRequest(
        int generation,
        Part part) {
}
