package org.sopt.api.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.api.post.dto.request.PostSaveOrUpdateRequest;
import org.sopt.api.post.dto.response.PostGetResponse;
import org.sopt.api.post.dto.response.PostSaveResponse;
import org.sopt.common.error.EntityNotFoundException;
import org.sopt.domain.member.domain.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.domain.post.domain.Category;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.repository.CategoryRepository;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.infra.s3.S3Handler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.sopt.domain.post.domain.Category.createCategory;
import static org.sopt.domain.post.domain.Post.createPost;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final S3Handler s3Handler;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public PostSaveResponse savePost(Long memberId, MultipartFile image, PostSaveOrUpdateRequest request) {
        Member findMember = findMember(memberId);
        Category savedCategory = createCategoryAndGetSavedCategoryOrGetCategory(request);
        String imageUrl = s3Handler.uploadImage(image);
        Post savedPost = createPostAndGetSavedPost(imageUrl, request, findMember, savedCategory);
        return PostSaveResponse.of(savedPost);
    }

    public PostGetResponse getPost(Long postId) {
        Post findPost = findPost(postId);
        return PostGetResponse.of(findPost);
    }

    public List<PostGetResponse> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable)
                .stream()
                .map(PostGetResponse::of)
                .toList();
    }

    @Transactional
    public void updatePost(Long postId, PostSaveOrUpdateRequest request) {
        Post findPost = findPost(postId);
        Category category = findPost.getCategory();
        updatePostAndCategory(request, findPost, category);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post findPost = findPost(postId);
        s3Handler.deleteImage(findPost.getImageUrl());
        postRepository.deleteById(postId);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findByIdOrThrow(memberId);
    }

    private Category createCategoryAndGetSavedCategoryOrGetCategory(PostSaveOrUpdateRequest request) {
        try {
            return findCategory(request.categoryContent());
        } catch (EntityNotFoundException e) {
            Category category = createCategory(request.categoryContent());
            return categoryRepository.save(category);
        }
    }

    private Post createPostAndGetSavedPost(String imageUrl, PostSaveOrUpdateRequest request, Member member, Category category) {
        Post post = createPost(request.title(), request.postContent(), imageUrl, category, member);
        return postRepository.save(post);
    }

    private Post findPost(Long postId) {
        return postRepository.findByIdOrThrow(postId);
    }

    private void updatePostAndCategory(PostSaveOrUpdateRequest request, Post post, Category category) {
        post.updateTitleAndContent(request.title(), request.postContent());
        category.updateContent(request.categoryContent());
    }

    private Category findCategory(String content) {
        return categoryRepository.findByContentOrThrow(content);
    }
}
