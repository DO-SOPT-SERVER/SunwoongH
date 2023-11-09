package org.sopt.domain.post.repository;

import org.sopt.domain.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    boolean existsByContent(String content);
}
