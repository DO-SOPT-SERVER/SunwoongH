package org.sopt.api.member.api;

import lombok.RequiredArgsConstructor;
import org.sopt.api.auth.MemberId;
import org.sopt.api.auth.jwt.Token;
import org.sopt.api.common.ApiResponse;
import org.sopt.api.common.Constants;
import org.sopt.api.common.SuccessStatus;
import org.sopt.api.member.dto.request.MemberSignInRequest;
import org.sopt.api.member.dto.request.MemberSignUpRequest;
import org.sopt.api.member.dto.request.MemberUpdateRequest;
import org.sopt.api.member.dto.response.MemberGetResponse;
import org.sopt.api.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@Controller
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signUp(@RequestBody final MemberSignUpRequest request) {
        final Token response = memberService.signUp(request);
        return ApiResponse.success(SuccessStatus.CREATED, response);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> signIn(@RequestBody final MemberSignInRequest request) {
        final Token response = memberService.signIn(request);
        return ApiResponse.success(SuccessStatus.OK, response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<?>> reissue(@RequestHeader(Constants.AUTHORIZATION) final String refreshToken) {
        final Token response = memberService.reissue(refreshToken);
        return ApiResponse.success(SuccessStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getMember(@MemberId final Long memberId) {
        final MemberGetResponse response = memberService.getMember(memberId);
        return ApiResponse.success(SuccessStatus.OK, response);
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<?>> updateMember(@MemberId final Long memberId,
                                                       @RequestBody final MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteMember(@MemberId final Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.success(SuccessStatus.OK);
    }
}