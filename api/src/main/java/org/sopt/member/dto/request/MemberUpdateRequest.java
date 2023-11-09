package org.sopt.member.dto.request;

import org.sopt.member.domain.Part;

public record MemberUpdateRequest(
        int generation,
        Part part) {
}
