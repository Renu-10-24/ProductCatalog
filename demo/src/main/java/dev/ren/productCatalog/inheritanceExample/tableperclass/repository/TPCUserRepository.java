package dev.ren.productCatalog.inheritanceExample.tableperclass.repository;


import dev.ren.productCatalog.inheritanceExample.tableperclass.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCUserRepository  extends JpaRepository<User, Long> {
    User save(User user);
}