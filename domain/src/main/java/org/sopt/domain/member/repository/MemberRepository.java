package org.sopt.domain.member.repository;

import org.sopt.common.error.EntityNotFoundException;
import org.sopt.common.error.ErrorStatus;
import org.sopt.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);

    default Member findByIdOrThrow(Long memberId) {
        return findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
    }
}