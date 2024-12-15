package com.vato.ecommerce.repository;

import com.vato.ecommerce.model.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndParentCategoryName(
            @Param("name") String name,
            @Param("parentCategoryName") String parentCategoryName
    );
}
