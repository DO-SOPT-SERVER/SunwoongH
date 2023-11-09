package org.sopt.member.repository;

import org.sopt.error.EntityNotFoundException;
import org.sopt.error.ErrorStatus;
import org.sopt.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);

    default Member findByIdOrThrow(Long memberId) {
        return findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
    }
}