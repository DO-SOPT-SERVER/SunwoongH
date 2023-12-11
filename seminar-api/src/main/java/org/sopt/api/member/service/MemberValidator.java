package org.sopt.api.member.service;

import org.sopt.common.error.ConflictException;
import org.sopt.common.error.ErrorStatus;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator {
    public void validateDuplicateMember(boolean isExist) {
        if (isExist) {
            throw new ConflictException(ErrorStatus.DUPLICATE_MEMBER);
        }
    }
}
