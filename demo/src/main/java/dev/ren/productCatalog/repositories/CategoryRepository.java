package dev.ren.productCatalog.repositories;


import dev.ren.productCatalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public Category save(Category category);
//    public  Optional<Category> findById(@Param("id") UUID id);

}