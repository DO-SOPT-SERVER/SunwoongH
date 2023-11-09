package org.sopt.post.repository;

import org.sopt.error.EntityNotFoundException;
import org.sopt.error.ErrorStatus;
import org.sopt.post.domain.Post;
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
