package org.sopt.api.post.api;

import lombok.RequiredArgsConstructor;
import org.sopt.api.auth.MemberId;
import org.sopt.api.common.ApiResponse;
import org.sopt.api.common.SuccessStatus;
import org.sopt.api.post.dto.request.PostSaveOrUpdateRequest;
import org.sopt.api.post.dto.response.PostGetResponse;
import org.sopt.api.post.dto.response.PostSaveResponse;
import org.sopt.api.post.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@Controller
public class PostApiController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> savePost(@MemberId final Long memberId,
                                                   @RequestPart final MultipartFile image,
                                                   @RequestPart final PostSaveOrUpdateRequest request) {
        final PostSaveResponse response = postService.savePost(memberId, image, request);
        return ApiResponse.success(SuccessStatus.CREATED, response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> getPost(@PathVariable final Long postId) {
        final PostGetResponse response = postService.getPost(postId);
        return ApiResponse.success(SuccessStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPosts(@MemberId final Long memberId,
                                                   final Pageable pageable) {
        final List<PostGetResponse> response = postService.getPosts(memberId, pageable);
        return ApiResponse.success(SuccessStatus.OK, response);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable final Long postId,
                                                     @RequestBody final PostSaveOrUpdateRequest request) {
        postService.updatePost(postId, request);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return ApiResponse.success(SuccessStatus.OK);
    }
}
