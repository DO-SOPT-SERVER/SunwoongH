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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.domain.post.domain.Category.createCategory;
import static org.sopt.domain.post.domain.Post.createPost;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public PostSaveResponse savePost(Long memberId, PostSaveOrUpdateRequest postSaveOrUpdateRequest) {
        Member findMember = findMember(memberId);
        Category savedCategory = createCategoryAndGetSavedCategoryOrGetCategory(postSaveOrUpdateRequest);
        Post savedPost = createPostAndGetSavedPost(postSaveOrUpdateRequest, findMember, savedCategory);
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
    public void updatePost(Long postId, PostSaveOrUpdateRequest postSaveOrUpdateRequest) {
        Post findPost = findPost(postId);
        Category category = findPost.getCategory();
        updatePostAndCategory(postSaveOrUpdateRequest, findPost, category);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findByIdOrThrow(memberId);
    }

    private Category createCategoryAndGetSavedCategoryOrGetCategory(PostSaveOrUpdateRequest postSaveOrUpdateRequest) {
        try {
            return findCategory(postSaveOrUpdateRequest.categoryContent());
        } catch (EntityNotFoundException e) {
            Category category = createCategory(postSaveOrUpdateRequest.categoryContent());
            return categoryRepository.save(category);
        }
    }

    private Post createPostAndGetSavedPost(PostSaveOrUpdateRequest postSaveOrUpdateRequest, Member member, Category category) {
        Post post = createPost(postSaveOrUpdateRequest.title(), postSaveOrUpdateRequest.postContent(), category, member);
        return postRepository.save(post);
    }

    private Post findPost(Long postId) {
        return postRepository.findByIdOrThrow(postId);
    }

    private void updatePostAndCategory(PostSaveOrUpdateRequest postSaveOrUpdateRequest, Post post, Category category) {
        post.updateTitleAndContent(postSaveOrUpdateRequest.title(), postSaveOrUpdateRequest.postContent());
        category.updateContent(postSaveOrUpdateRequest.categoryContent());
    }

    private Category findCategory(String content) {
        return categoryRepository.findByContentOrThrow(content);
    }
}
