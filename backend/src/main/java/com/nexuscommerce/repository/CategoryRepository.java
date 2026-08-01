package com.nexuscommerce.repository;

import com.nexuscommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findBySlug(String slug);

    List<Category> findByParentCategoryIsNullAndActiveTrueOrderByNameAsc();

    List<Category> findByActiveTrueOrderByNameAsc();

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    boolean existsByNameAndIdNot(String name, UUID id);
}
