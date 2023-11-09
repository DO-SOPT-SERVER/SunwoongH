package org.sopt.seminar.domain.post.repository;

import org.sopt.seminar.domain.post.domain.Post;
import org.sopt.seminar.global.error.EntityNotFoundException;
import org.sopt.seminar.global.error.ErrorStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    default Post findByIdOrThrow(Long postId) {
        return findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorStatus.POST_NOT_FOUND));
    }
}
