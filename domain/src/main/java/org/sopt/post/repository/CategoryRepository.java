package org.sopt.post.repository;

import org.sopt.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
