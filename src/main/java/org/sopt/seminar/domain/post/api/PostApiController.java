package org.sopt.seminar.domain.post.api;

import lombok.RequiredArgsConstructor;
import org.sopt.seminar.domain.post.dto.request.PostSaveOrUpdateRequest;
import org.sopt.seminar.domain.post.dto.response.PostGetResponse;
import org.sopt.seminar.domain.post.dto.response.PostSaveResponse;
import org.sopt.seminar.domain.post.service.PostService;
import org.sopt.seminar.global.common.ApiResponse;
import org.sopt.seminar.global.common.SuccessStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@Controller
public class PostApiController {
    private static final String CUSTOM_AUTHENTICATION = "X-Auth-Id";
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> savePost(@RequestHeader(CUSTOM_AUTHENTICATION) final Long memberId,
                                                   @RequestBody final PostSaveOrUpdateRequest postSaveOrUpdateRequest) {
        final PostSaveResponse postSaveResponse = postService.savePost(memberId, postSaveOrUpdateRequest);
        return ApiResponse.success(SuccessStatus.CREATED, postSaveResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> getPost(@PathVariable final Long postId) {
        final PostGetResponse post = postService.getPost(postId);
        return ApiResponse.success(SuccessStatus.OK, post);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPosts(@RequestHeader(CUSTOM_AUTHENTICATION) final Long memberId,
                                                   final Pageable pageable) {
        final List<PostGetResponse> posts = postService.getPosts(memberId, pageable);
        return ApiResponse.success(SuccessStatus.OK, posts);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable final Long postId,
                                                     @RequestBody final PostSaveOrUpdateRequest postSaveOrUpdateRequest) {
        postService.updatePost(postId, postSaveOrUpdateRequest);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return ApiResponse.success(SuccessStatus.OK);
    }
}
