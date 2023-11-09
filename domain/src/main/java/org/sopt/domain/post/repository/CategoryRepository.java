package org.sopt.domain.post.repository;

import org.sopt.common.error.EntityNotFoundException;
import org.sopt.common.error.ErrorStatus;
import org.sopt.domain.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    Optional<Category> findByContent(String content);

    default Category findByContentOrThrow(String content) {
        return findByContent(content)
                .orElseThrow(() -> new EntityNotFoundException(ErrorStatus.CATEGORY_NOT_FOUND));
    }
}
