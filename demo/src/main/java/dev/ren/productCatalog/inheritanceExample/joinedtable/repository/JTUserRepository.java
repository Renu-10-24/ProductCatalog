package dev.ren.productCatalog.inheritanceExample.joinedtable.repository;

import dev.ren.productCatalog.inheritanceExample.joinedtable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JTUserRepository  extends JpaRepository<User, Long> {
    User save(User user);
}
