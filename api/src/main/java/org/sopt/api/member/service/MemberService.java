package org.sopt.api.member.service;

import lombok.RequiredArgsConstructor;
import org.sopt.api.auth.PasswordHandler;
import org.sopt.api.auth.jwt.JwtProvider;
import org.sopt.api.auth.jwt.JwtValidator;
import org.sopt.api.auth.jwt.Token;
import org.sopt.api.member.dto.request.MemberSignInRequest;
import org.sopt.api.member.dto.request.MemberSignUpRequest;
import org.sopt.api.member.dto.request.MemberUpdateRequest;
import org.sopt.api.member.dto.response.MemberGetResponse;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.domain.Sopt;
import org.sopt.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.domain.member.domain.Member.createMember;
import static org.sopt.domain.member.domain.Sopt.createSopt;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final PasswordHandler passwordHandler;
    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;
    private final MemberValidator memberValidator;
    private final MemberRepository memberRepository;

    @Transactional
    public Token signUp(MemberSignUpRequest request) {
        validateDuplicateMember(request.nickname());
        Member member = createMember(request.name(), request.nickname(),
                passwordHandler.encode(request.password()), request.age(), request.sopt());
        Member savedMember = memberRepository.save(member);
        Token token = jwtProvider.issueToken(savedMember.getId());
        savedMember.updateRefreshToken(token.refreshToken());
        return token;
    }

    @Transactional
    public Token signIn(MemberSignInRequest request) {
        Member findMember = memberRepository.findByNicknameOrThrow(request.nickname());
        passwordHandler.validatePassword(request.password(), findMember.getPassword());
        Token token = jwtProvider.issueToken(findMember.getId());
        findMember.updateRefreshToken(token.refreshToken());
        return token;
    }

    @Transactional
    public Token reissue(String refreshToken) {
        jwtValidator.validateRefreshToken(refreshToken);
        Long memberId = jwtProvider.getSubject(refreshToken);
        Member findMember = memberRepository.findByIdOrThrow(memberId);
        jwtValidator.equalsRefreshToken(refreshToken, findMember.getRefreshToken());
        Token token = jwtProvider.issueToken(memberId);
        findMember.updateRefreshToken(token.refreshToken());
        return token;
    }

    public MemberGetResponse getMember(Long memberId) {
        Member findMember = memberRepository.findByIdOrThrow(memberId);
        return MemberGetResponse.of(findMember);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest request) {
        Member findMember = memberRepository.findByIdOrThrow(memberId);
        Sopt sopt = createSopt(request.generation(), request.part());
        findMember.updateSopt(sopt);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private void validateDuplicateMember(String nickname) {
        memberValidator.validateDuplicateMember(memberRepository.existsByNickname(nickname));
    }
}
