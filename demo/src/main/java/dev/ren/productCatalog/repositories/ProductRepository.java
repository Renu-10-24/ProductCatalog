package dev.ren.productCatalog.repositories;


import dev.ren.productCatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Product save(Product product);

    //findById from JpaRepository<Product, UUID> using the default provided by JpaRepository, dont overwrite
    public Optional<Product> findById(UUID uuid);

    public Optional<Product> findByName(String name);

}
