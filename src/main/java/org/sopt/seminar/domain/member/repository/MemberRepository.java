package org.sopt.seminar.domain.member.repository;


import org.sopt.seminar.domain.member.domain.Member;
import org.sopt.seminar.global.error.EntityNotFoundException;
import org.sopt.seminar.global.error.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);

    default Member findByIdOrThrow(Long memberId) {
        return findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
    }
}