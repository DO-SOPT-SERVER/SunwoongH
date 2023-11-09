package org.sopt.member.api;

import lombok.RequiredArgsConstructor;
import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessStatus;
import org.sopt.member.dto.request.MemberSaveRequest;
import org.sopt.member.dto.request.MemberUpdateRequest;
import org.sopt.member.dto.response.MemberGetResponse;
import org.sopt.member.dto.response.MemberSaveResponse;
import org.sopt.member.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@Controller
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveMember(@RequestBody final MemberSaveRequest memberSaveRequest) {
        final MemberSaveResponse savedMember = memberService.saveMember(memberSaveRequest);
        return ApiResponse.success(SuccessStatus.CREATED, savedMember);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<?>> getMember(@PathVariable final Long memberId) {
        final MemberGetResponse findMember = memberService.getMember(memberId);
        return ApiResponse.success(SuccessStatus.OK, findMember);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getMembers(final Pageable pageable) {
        final List<MemberGetResponse> findMembers = memberService.getMembers(pageable);
        return ApiResponse.success(SuccessStatus.OK, findMembers);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResponse<?>> updateMember(@PathVariable final Long memberId,
                                                       @RequestBody final MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberId, memberUpdateRequest);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<?>> deleteMember(@PathVariable final Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.success(SuccessStatus.OK);
    }
}