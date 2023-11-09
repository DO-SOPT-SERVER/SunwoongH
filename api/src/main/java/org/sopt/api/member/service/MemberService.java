package org.sopt.api.member.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.error.ConflictException;
import org.sopt.common.error.ErrorStatus;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.Sopt;
import org.sopt.api.member.dto.request.MemberSaveRequest;
import org.sopt.api.member.dto.request.MemberUpdateRequest;
import org.sopt.api.member.dto.response.MemberGetResponse;
import org.sopt.api.member.dto.response.MemberSaveResponse;
import org.sopt.domain.member.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.domain.member.domain.Member.createMember;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponse saveMember(MemberSaveRequest memberSaveRequest) {
        validateDuplicateMember(memberSaveRequest.nickname());
        Member member = createMember(memberSaveRequest.name(), memberSaveRequest.nickname(),
                memberSaveRequest.age(), memberSaveRequest.sopt());
        Member savedMember = memberRepository.save(member);
        return MemberSaveResponse.of(savedMember);
    }

    public MemberGetResponse getMember(Long memberId) {
        Member findMember = memberRepository.findByIdOrThrow(memberId);
        return MemberGetResponse.of(findMember);
    }

    public List<MemberGetResponse> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .stream()
                .map(MemberGetResponse::of)
                .toList();
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member findMember = memberRepository.findByIdOrThrow(memberId);
        Sopt sopt = Sopt.createSopt(memberUpdateRequest.generation(), memberUpdateRequest.part());
        findMember.updateSopt(sopt);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private void validateDuplicateMember(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ConflictException(ErrorStatus.DUPLICATE_MEMBER);
        }
    }
}
