package org.sopt.seminar.domain.member.dto.request;

import org.sopt.seminar.domain.member.domain.Part;

public record MemberUpdateRequest(
        int generation,
        Part part) {
}
