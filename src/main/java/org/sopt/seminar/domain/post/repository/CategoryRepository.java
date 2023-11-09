package org.sopt.seminar.domain.post.repository;

import org.sopt.seminar.domain.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
