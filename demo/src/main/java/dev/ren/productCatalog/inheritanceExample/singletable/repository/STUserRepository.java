package dev.ren.productCatalog.inheritanceExample.singletable.repository;


import dev.ren.productCatalog.inheritanceExample.singletable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface STUserRepository extends JpaRepository<User, Long> {
    User save(User user);
}