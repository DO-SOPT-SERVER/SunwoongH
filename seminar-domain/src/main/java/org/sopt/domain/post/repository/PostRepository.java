package org.sopt.domain.post.repository;

import org.sopt.common.error.EntityNotFoundException;
import org.sopt.common.error.ErrorStatus;
import org.sopt.domain.post.domain.Post;
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
